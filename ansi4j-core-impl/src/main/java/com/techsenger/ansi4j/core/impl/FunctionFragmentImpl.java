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

import java.util.Collections;
import java.util.List;
import com.techsenger.ansi4j.core.api.FragmentType;
import com.techsenger.ansi4j.core.api.FunctionFragment;
import com.techsenger.ansi4j.core.api.function.Function;
import com.techsenger.ansi4j.core.api.function.FunctionArgument;

/**
 *
 * @author Pavel Castornii
 */
public class FunctionFragmentImpl extends AbstractFragment implements FunctionFragment {

    private final Function function;

    private final List<FunctionArgument> arguments;

    /**
     *
     * @param text
     * @param function
     * @param arguments modifiable collection.
     */
    public FunctionFragmentImpl(String text, int currentIndex, Function function, List<FunctionArgument> arguments) {
        super(FragmentType.FUNCTION, text, currentIndex);
        this.function = function;
        if (arguments != null) {
            this.arguments = Collections.unmodifiableList(arguments);
        } else {
            this.arguments = null;
        }
    }

    @Override
    public Function getFunction() {
        return this.function;
    }

    @Override
    public List<FunctionArgument> getArguments() {
        return this.arguments;
    }

    @Override
    public String toString() {
        return "FunctionFragment{" + "function=" + function + ", arguments=" + arguments + '}'
                + "->" + super.toString();
    }
}
