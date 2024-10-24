/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.iso6429;

import com.techsenger.ansi4j.core.api.Environment;
import com.techsenger.ansi4j.core.api.FunctionHandler;

/**
 *
 * @author Pavel Castornii
 */
public abstract class AbstractFunctionHandler implements FunctionHandler {

    private Environment environment;

    /**
     * Functions checks if whole function is present in text.
     * @param text
     * @param endIndex
     * @return
     */
    protected boolean isEndOfFunctionPresent(String text, int endIndex) {
        return !(endIndex > text.length());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(Environment environment) {
        this.environment = environment;
    }

    protected Environment getEnvironment() {
        return environment;
    }
}
