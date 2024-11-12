/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
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
            terminatingTerminator = C1ControlFunction.ST_STRING_TERMINATOR.getPattern();
        } else if (this.getEnvironment() == Environment._8_BIT) {
            openingDelimiter = ((C1ControlFunction) function).get8BitPattern();
            terminatingTerminator = C1ControlFunction.ST_STRING_TERMINATOR.get8BitPattern();
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
