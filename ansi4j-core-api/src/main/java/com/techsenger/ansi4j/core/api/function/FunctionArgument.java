/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.api.function;

import javax.annotation.concurrent.Immutable;

/**
 *
 * @author Pavel Castornii
 */
@Immutable
public interface FunctionArgument {

    /**
     * If function supports default value, but parser can't resolve it (for example, default value = currentRow + 1)
     * then this value is used.
     */
    Object UNSUPPORTED_DEFAULT_VALUE = new Object();

    /**
     * Returns value of the parameter. It can be an explicit value, a default value or UNSUPPORTED_DEFAULT_VALUE.
     *
     */
    Object getValue();

    /**
     * This flag allows to check if parameter value is default (escape code didn't contain explicit value).
     *
     * @return
     */
    boolean isDefault();
}
