/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.api;

import java.util.List;
import javax.annotation.concurrent.ThreadSafe;

/**
 * CSS generator generates CSS declarations on the base of context change for certain attribute.
 *
 * @author Pavel Castornii
 */
@ThreadSafe
public interface AttributeCssGenerator {

    /**
     * Returns collections of CSS declarations.
     * @param context
     * @return declarations or empty list.
     */
    List<String> generate(AttributeChange change, AttributeContext context);

    /**
     * Returns class of the attribute that this css generators supports.
     * @return
     */
    Class<? extends Attribute> getTargetAttributeClass();
}
