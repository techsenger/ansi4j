/*
 * Copyright 2022-2024 Pavel Castornii.
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

package com.techsenger.ansi4j.core.impl.function;

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

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public boolean isDefault() {
        return this.isDefault;
    }

    @Override
    public String toString() {
        return "FunctionArgumentImpl{" + "value=" + value + ", isDefault=" + isDefault + '}';
    }
}
