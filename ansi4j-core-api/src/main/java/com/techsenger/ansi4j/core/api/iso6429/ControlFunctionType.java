/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.api.iso6429;

import com.techsenger.ansi4j.core.api.function.FunctionType;

/**
 *
 * @author Pavel Castornii
 */
public enum ControlFunctionType implements FunctionType {

    C0_SET,

    C1_SET,

    CONTROL_SEQUENCE,

    INDEPENDENT_FUNCTION,

    CONTROL_STRING
}
