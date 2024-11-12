/*
 * Copyright 2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.impl;

import com.techsenger.ansi4j.core.api.ParserFactory;
import com.techsenger.ansi4j.core.api.spi.ParserFactoryConfig;
import com.techsenger.ansi4j.core.api.spi.ParserFactoryService;

/**
 *
 * @author Pavel Castornii
 */
public class ParserFactoryProvider implements ParserFactoryService {

    @Override
    public ParserFactory createFactory(ParserFactoryConfig config) {
        return new ParserFactoryImpl(config);
    }
}
