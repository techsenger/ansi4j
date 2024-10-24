/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.function.impl;

import com.techsenger.ansi4j.core.api.function.FunctionArgument;

/**
 *
 * @author Pavel Castornii
 */
public class FunctionArgumentImpl implements FunctionArgument {

    private final Object value;

    private final boolean isDefault;

    public FunctionArgumentImpl(Object value, boolean isDefault) {
        this.value = value;
        this.isDefault = isDefault;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getValue() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDefault() {
        return this.isDefault;
    }

}
