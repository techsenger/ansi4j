/*
 * Copyright 2024 Pavel Castornii.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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

    @Override
    public Fragment parse() {
        try {
            return this.doParse();
        } catch (Exception ex) {
            logger.error("Error parsing text", ex);
            return null;
        }
    }

    @Override
    protected boolean delayFunctionParsing(FailureReason reason) {
        return false;
    }
}
