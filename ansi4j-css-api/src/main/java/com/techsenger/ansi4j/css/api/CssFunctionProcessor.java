/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.api;

import java.util.List;
import java.util.Map;
import javax.annotation.concurrent.ThreadSafe;
import com.techsenger.ansi4j.core.api.FunctionFragment;
import com.techsenger.ansi4j.core.api.function.Function;

/**
 *
 * @author Pavel Castornii
 */
@ThreadSafe
public interface CssFunctionProcessor {

    /**
     * Returns resolvers by function in unmodifiable map.
     *
     * @return
     */
    Map<Function, AttributeResolver> getAttributeResolversByFunction();

    /**
     * Returns generators by attribute class in unmodifiable map.
     * @return
     */
    Map<Class<? extends Attribute>, AttributeCssGenerator> getCssGeneratorsByAttributeClass();

    /**
     * Processes function fragment, context and return CSS declarations.
     *
     * @param functionFragment
     * @param context
     * @return declarations or empty list.
     */
    List<String> process(FunctionFragment functionFragment, AttributeContext context);
}
