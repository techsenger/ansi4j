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

package com.techsenger.ansi4j.css.impl;

import com.techsenger.ansi4j.core.api.FunctionFragment;
import com.techsenger.ansi4j.core.api.function.Function;
import com.techsenger.ansi4j.css.api.ProcessorResult;

/**
 *
 * @author Pavel Castornii
 */
public interface FunctionEvaluator {

    /**
     * Resolves attributes on the base of function and modifies context.
     *
     * @param functionFragment
     * @return changes or empty list.
     */
    void evaluate(FunctionFragment functionFragment, ProcessorResult processorResult);

    /**
     * Returns function that this attribute resolver supports.
     *
     * @return
     */
    Function getFunction();
}
