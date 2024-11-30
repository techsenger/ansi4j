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

package com.techsenger.ansi4j.core.impl.iso6429;

import java.util.ArrayList;
import java.util.Optional;
import com.techsenger.ansi4j.core.api.function.FunctionType;
import com.techsenger.ansi4j.core.api.iso6429.ControlFunctionType;
import com.techsenger.ansi4j.core.impl.FunctionFragmentImpl;
import com.techsenger.ansi4j.core.api.FunctionFailureReason;
import com.techsenger.ansi4j.core.api.iso6429.ControlFunction;
import com.techsenger.ansi4j.core.impl.FunctionHandlerResultImpl;
import com.techsenger.ansi4j.core.api.FunctionHandlerResult;

/**
 *
 * @author Pavel Castornii
 */
public class IndependentControlFunctionHandler extends AbstractFunctionHandler {

    @Override
    public FunctionType getFunctionType() {
        return ControlFunctionType.INDEPENDENT_FUNCTION;
    }

    @Override
    public FunctionHandlerResult handle(String text, ControlFunction function, int currentIndex) {
        var startIndex = 0;
        int endIndex = startIndex + 2;
        if (!isEndOfFunctionPresent(text, endIndex)) {
            return new FunctionHandlerResultImpl(Optional.empty(), FunctionFailureReason.NO_END_OF_FUNCTION);
        }
        var functionText = text.substring(startIndex, endIndex);
        return new FunctionHandlerResultImpl(Optional.of(
                new FunctionFragmentImpl(functionText, currentIndex, function, new ArrayList<>())), null);
    }
}
