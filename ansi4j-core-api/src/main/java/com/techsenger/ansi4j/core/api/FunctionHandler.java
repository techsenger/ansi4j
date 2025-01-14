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
    FunctionType getFunctionType();

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
