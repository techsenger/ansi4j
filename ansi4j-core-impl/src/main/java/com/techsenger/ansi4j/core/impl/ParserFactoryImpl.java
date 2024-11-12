/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.impl;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import com.techsenger.ansi4j.core.api.Environment;
import com.techsenger.ansi4j.core.api.FunctionFinder;
import com.techsenger.ansi4j.core.api.ParserFactory;
import com.techsenger.ansi4j.core.api.StreamParser;
import com.techsenger.ansi4j.core.api.StringParser;
import com.techsenger.ansi4j.core.api.function.FunctionType;
import com.techsenger.ansi4j.core.api.FunctionHandler;
import com.techsenger.ansi4j.core.api.TextHandler;
import com.techsenger.ansi4j.core.api.iso6429.ControlFunctionType;
import com.techsenger.ansi4j.core.api.spi.ParserFactoryConfig;
import com.techsenger.ansi4j.core.impl.iso6429.C0ControlFunctionHandler;
import com.techsenger.ansi4j.core.impl.iso6429.C1ControlFunctionHandler;
import com.techsenger.ansi4j.core.impl.iso6429.ControlSequenceHandler;
import com.techsenger.ansi4j.core.impl.iso6429.ControlStringHandler;
import com.techsenger.ansi4j.core.impl.iso6429.IndependentControlFunctionHandler;

/**
 *
 * @author Pavel Castornii
 */
public final class ParserFactoryImpl implements ParserFactory {

    private final Environment environment;

    private final FunctionFinder functionFinder;

    private final Map<FunctionType, FunctionHandler> functionHandlersByType = new HashMap<>();

    private final TextHandler textHandler;

    public ParserFactoryImpl(ParserFactoryConfig config) {
        this.environment = config.getEnvironment();
        if (config.getFunctionFinder() != null) {
            this.functionFinder = config.getFunctionFinder();
        } else {
            this.functionFinder = new FunctionFinderImpl();
        }
        this.functionFinder.initialize(this.environment);

        if (config.getFunctionHandlers() != null) {
            for (var handler : config.getFunctionHandlers()) {
                this.functionHandlersByType.put(handler.getFunctionType(), handler);
            }
        }
        for (var type : config.getFunctionTypes()) {
            if (!this.functionHandlersByType.containsKey(type)) {
                var handler = createFunctionHandler(type);
                this.functionHandlersByType.put(handler.getFunctionType(), handler);
            }
        }
        this.functionHandlersByType.values().forEach(p -> p.initialize(this.environment));

        if (config.getTextHandler() != null) {
            this.textHandler = config.getTextHandler();
        } else {
            this.textHandler = new TextHandlerImpl();
        }
        this.textHandler.initialize(this.environment);
    }

    @Override
    public FunctionFinder getFunctionFinder() {
        return this.functionFinder;
    }

    @Override
    public Map<FunctionType, FunctionHandler> getFunctionHandlersByType() {
        return Collections.unmodifiableMap(this.functionHandlersByType);
    }

    @Override
    public TextHandler getTextHandler() {
        return this.textHandler;
    }

    @Override
    public StringParser createParser(String text) {
        if (text == null) {
            throw new IllegalArgumentException("No text provided");
        }
        return new StringParserImpl(text, this);
    }

    @Override
    public StreamParser createParser(InputStream stream, Charset encoding, int bufferSize) {
        if (stream == null) {
            throw new IllegalArgumentException("No stream provided");
        }
        if (encoding == null) {
            throw new IllegalArgumentException("No encoding provided");
        }
        if (bufferSize <= 0) {
            throw new IllegalArgumentException("Illegal value of buffer size: " + bufferSize);
        }
        return new StreamParserImpl(stream, encoding, bufferSize, this);
    }

    @Override
    public Environment getEnvironment() {
        return this.environment;
    }

    private FunctionHandler createFunctionHandler(ControlFunctionType type) {
        switch (type) {
            case C0_SET: return new C0ControlFunctionHandler();
            case C1_SET: return new C1ControlFunctionHandler();
            case CONTROL_SEQUENCE: return new ControlSequenceHandler();
            case INDEPENDENT_FUNCTION: return new IndependentControlFunctionHandler();
            case CONTROL_STRING: return new ControlStringHandler();
            default:
                throw new AssertionError();
        }
    }
}
