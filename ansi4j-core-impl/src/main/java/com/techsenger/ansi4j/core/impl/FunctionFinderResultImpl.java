/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.impl;

import com.techsenger.ansi4j.core.api.function.FunctionType;
import com.techsenger.ansi4j.core.api.iso6429.ControlFunction;
import com.techsenger.ansi4j.core.api.FunctionFinderResult;

/**
 *
 * @author Pavel Castornii
 */
public class FunctionFinderResultImpl implements FunctionFinderResult {

    private final int functionIndex;

    private final FunctionType functionType;

    private final ControlFunction function;

    public FunctionFinderResultImpl(int functionIndex, FunctionType functionType, ControlFunction function) {
        this.functionIndex = functionIndex;
        this.functionType = functionType;
        this.function = function;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFunctionIndex() {
        return this.functionIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionType getFunctionType() {
        return this.functionType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlFunction getFunction() {
        return function;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "FunctionFinderResultImpl{" + "functionIndex=" + functionIndex + ", functionType=" + functionType
                + ", function=" + function + '}';
    }
}
