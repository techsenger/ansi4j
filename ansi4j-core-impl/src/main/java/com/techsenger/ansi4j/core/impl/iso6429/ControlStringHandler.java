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

package com.techsenger.ansi4j.core.impl.iso6429;

import java.util.ArrayList;
import java.util.Optional;
import com.techsenger.ansi4j.core.api.Environment;
import com.techsenger.ansi4j.core.api.function.FunctionArgument;
import com.techsenger.ansi4j.core.api.function.FunctionType;
import com.techsenger.ansi4j.core.api.iso6429.C1ControlFunction;
import com.techsenger.ansi4j.core.api.iso6429.ControlFunctionType;
import com.techsenger.ansi4j.core.impl.function.FunctionArgumentImpl;
import com.techsenger.ansi4j.core.impl.FunctionFragmentImpl;
import com.techsenger.ansi4j.core.api.FunctionFailureReason;
import com.techsenger.ansi4j.core.api.iso6429.ControlFunction;
import com.techsenger.ansi4j.core.impl.FunctionHandlerResultImpl;
import com.techsenger.ansi4j.core.api.FunctionHandlerResult;


/**
 *
 * @author Pavel Castornii
 */
public class ControlStringHandler extends AbstractFunctionHandler {

    @Override
    public FunctionType getFunctionType() {
        return ControlFunctionType.CONTROL_STRING;
    }

    @Override
    public FunctionHandlerResult handle(String text, ControlFunction function, int currentIndex) {
        var startIndex = 0;
        String openingDelimiter = null;
        String terminatingTerminator = null;
        if (this.getEnvironment() == Environment._7_BIT) {
            openingDelimiter = ((C1ControlFunction) function).getPattern();
            terminatingTerminator = C1ControlFunction.ST.getPattern();
        } else if (this.getEnvironment() == Environment._8_BIT) {
            openingDelimiter = ((C1ControlFunction) function).get8BitPattern();
            terminatingTerminator = C1ControlFunction.ST.get8BitPattern();
        }
        int endIndex = text.indexOf(terminatingTerminator, startIndex);
        if (endIndex == -1) {
            return new FunctionHandlerResultImpl(Optional.empty(), FunctionFailureReason.NO_END_OF_FUNCTION);
        }
        endIndex += terminatingTerminator.length();
        var functionText = text.substring(startIndex, endIndex);
        var argumentString = text.substring(startIndex + openingDelimiter.length(), endIndex);
        var arguments = new ArrayList<FunctionArgument>();
        if (argumentString.indexOf(";") != -1) {
            var splits = argumentString.split(";");
            for (var split : splits) {
                var argument = new FunctionArgumentImpl(split, false);
                arguments.add(argument);
            }
        } else {
            var argument = new FunctionArgumentImpl(argumentString, false);
            arguments.add(argument);
        }
        return new FunctionHandlerResultImpl(Optional.of(
                new FunctionFragmentImpl(functionText, currentIndex, function, arguments)), null);
    }
}
