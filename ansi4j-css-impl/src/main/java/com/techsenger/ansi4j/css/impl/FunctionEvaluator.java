/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
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
