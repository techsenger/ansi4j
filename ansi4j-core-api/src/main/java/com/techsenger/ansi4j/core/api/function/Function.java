/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.api.function;

import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 *
 * @author Pavel Castornii
 */
@Immutable
public interface Function {

    /**
     * Returns pattern. Definitions:
     *
     * <p><ul>
     * <li>{s} - single numeric parameter,
     * <li>{m} - any number of numeric parameters.
     * <li>{c} - a single character,
     * <li>{t} - text.
     * </ul><p>
     *
     * @return
     */
    String getPattern();

    /**
     * Returns function type.
     *
     * @return
     */
    FunctionType getType();

    /**
     * Returns default values of the function.
     *
     * @return values or null.
     */
    List<Object> getDefaultValues();
}
