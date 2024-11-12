/*
 * Copyright 2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.impl;

import com.techsenger.ansi4j.css.api.spi.StyleProcessorConfig;
import com.techsenger.ansi4j.css.api.StyleProcessor;
import com.techsenger.ansi4j.css.api.spi.StyleProcessorService;

/**
 *
 * @author Pavel Castornii
 */
public class StyleProcessorProvider implements StyleProcessorService {

    @Override
    public StyleProcessor createProcessor(StyleProcessorConfig config) {
        return new StyleProcessorImpl(config);
    }

}
