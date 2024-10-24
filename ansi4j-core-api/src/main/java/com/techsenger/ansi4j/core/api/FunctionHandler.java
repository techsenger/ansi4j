/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.api;

import javax.annotation.concurrent.ThreadSafe;
import com.techsenger.ansi4j.core.api.function.FunctionType;
import com.techsenger.ansi4j.core.api.iso6429.ControlFunction;

/**
 *
 * @author Pavel Castornii
 */
@ThreadSafe
public interface FunctionHandler extends FragmentHandler {

    /**
     * The type of the function this parser works with.
     *
     * @return
     */
    FunctionType getTargetFunctionType();

    /**
     * Parses function text.
     *
     * @param text is a piece of the whole text and starts with the function (first character is the beginning of the
     * function)
     * @param function function that must be parsed
     * @param currentIndex index in the whole text (is equal to parsed text length). This parameter is required for
     * calculating start and end index as they are relative to the whole text.
     *
     * @return fragment parser result
     */
    FunctionHandlerResult handle(String text, ControlFunction function, int currentIndex);
}
