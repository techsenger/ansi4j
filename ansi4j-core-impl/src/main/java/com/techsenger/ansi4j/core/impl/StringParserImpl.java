/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.techsenger.ansi4j.core.api.FailureReason;
import com.techsenger.ansi4j.core.api.Fragment;
import com.techsenger.ansi4j.core.api.ParserFactory;
import com.techsenger.ansi4j.core.api.StringParser;

/**
 *
 * @author Pavel Castornii
 */
public class StringParserImpl extends AbstractParser implements StringParser {

    private static final Logger logger = LoggerFactory.getLogger(StringParserImpl.class);

    public StringParserImpl(String text, ParserFactory factory) {
        super(text, factory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fragment parse() {
        try {
            return this.doParse();
        } catch (Exception ex) {
            logger.error("Error parsing text", ex);
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean delayFunctionParsing(FailureReason reason) {
        return false;
    }
}
