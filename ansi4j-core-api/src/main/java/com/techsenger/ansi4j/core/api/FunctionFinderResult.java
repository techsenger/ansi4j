/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.api;

import javax.annotation.concurrent.Immutable;
import com.techsenger.ansi4j.core.api.function.FunctionType;
import com.techsenger.ansi4j.core.api.iso6429.ControlFunction;

/**
 *
 * @author Pavel Castornii
 */
@Immutable
public interface FunctionFinderResult {

    /**
     * Returns position of function in text.
     *
     * @return
     */
    int getFunctionIndex();

    /**
     * Returns found function type. We don't take type from firstFunction because first function can hold C1 function
     * but type can be ControlString.
     *
     * @return
     */
    FunctionType getFunctionType();

    /**
     * For C0 returns C0 function, for C1 return C1 function, for sequence returns CSI, for independent returns
     * independent function, for control strings returns opening delimiter.
     *
     * @return
     */
    ControlFunction getFunction();
}
