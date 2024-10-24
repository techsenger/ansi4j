/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.iso6429;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionType getTargetFunctionType() {
        return ControlFunctionType.INDEPENDENT_FUNCTION;
    }

    /**
     * {@inheritDoc}
     */
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
