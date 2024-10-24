/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.techsenger.ansi4j.core.api.FailureReason;
import com.techsenger.ansi4j.core.api.Fragment;
import com.techsenger.ansi4j.core.api.FunctionFailureReason;
import com.techsenger.ansi4j.core.api.ParserFactory;
import com.techsenger.ansi4j.core.api.StreamParser;

/**
 *
 * @author Pavel Castornii
 */
public class StreamParserImpl extends AbstractParser implements StreamParser {

    private static final Logger logger = LoggerFactory.getLogger(StreamParserImpl.class);

    private final InputStream stream;

    private final InputStreamReader inputStreamReader;

    private final BufferedReader bufferedReader;

    private final Charset encoding;

    private final int bufferSize;

    private final CharBuffer buffer;

    private final ParserFactory factory;

    /**
     * Surrogate that doesn't have pair.
     */
    private Character highSurrogate = null;

    public StreamParserImpl(InputStream stream, Charset encoding, int bufferSize, ParserFactory factory) {
        super("", factory);
        this.stream = stream;
        this.encoding = encoding;
        this.bufferSize = bufferSize;
        this.buffer = CharBuffer.allocate(bufferSize);
        this.factory = factory;
        inputStreamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        bufferedReader = new BufferedReader(inputStreamReader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fragment parse() {
        try {
            //there can text, saved function result etc
            var fragment = this.doParse();
            if (fragment != null) {
                return fragment;
            }
            while (true) {
                var count = this.readText();
                if (count == -1) {
                    return null;
                } else {
                    fragment = this.doParse();
                    if (fragment != null) {
                        return fragment;
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("Error parsing text", ex);
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws IOException {
        if (this.bufferedReader != null) {
            this.bufferedReader.close();
        }
        if (this.inputStreamReader != null) {
            this.inputStreamReader.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean delayFunctionParsing(FailureReason reason) {
        return reason == FunctionFailureReason.NO_END_OF_FUNCTION;
    }

    private int readText() {
        try {
            int count = bufferedReader.read(buffer);
            if (count != -1) {
                buffer.flip();
                //we can have high surrogate without low one
                var readString = buffer.toString();
                buffer.clear();
                if (highSurrogate != null) {
                    readString = highSurrogate + readString;
                    highSurrogate = null;
                }
                var lastChar = readString.charAt(readString.length() - 1);
                if (Character.isHighSurrogate(lastChar)) {
                    readString = readString.substring(0, readString.length() - 1);
                    highSurrogate = lastChar;
                }
                this.setText(getText() + readString);
            }
            return count;
        } catch (IOException ex) {
            logger.error("Error reading stream", ex);
            return -1;
        }
    }

}
