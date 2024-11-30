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

    @Override
    public int getFunctionIndex() {
        return this.functionIndex;
    }

    @Override
    public FunctionType getFunctionType() {
        return this.functionType;
    }

    @Override
    public ControlFunction getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return "FunctionFinderResultImpl{" + "functionIndex=" + functionIndex + ", functionType=" + functionType
                + ", function=" + function + '}';
    }
}
