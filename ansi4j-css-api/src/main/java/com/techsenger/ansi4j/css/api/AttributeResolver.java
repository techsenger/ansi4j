/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.api;

import java.util.List;
import javax.annotation.concurrent.ThreadSafe;
import com.techsenger.ansi4j.core.api.FunctionFragment;
import com.techsenger.ansi4j.core.api.function.Function;

/**
 *
 * @author Pavel Castornii
 */
@ThreadSafe
public interface AttributeResolver {

    /**
     * Resolves attributes on the base of function and modifies context.
     *
     * @param functionFragment
     * @param context
     * @return changes or empty list.
     */
    List<AttributeChange> resolve(FunctionFragment functionFragment, AttributeContext context);

    /**
     * Returns function that this attribute resolver supports.
     *
     * @return
     */
    Function getTargetFunction();
}
