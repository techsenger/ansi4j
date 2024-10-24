/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.api;

import java.util.Map;
import java.util.Optional;
import javax.annotation.concurrent.NotThreadSafe;

/**
 * AttributeContext holds and manages attributes for some text including default and non default values.
 * One context can work with N attribute classes.
 *
 * @author Pavel Castornii
 */
@NotThreadSafe
public interface AttributeContext {

    /**
     * Returns non default values of the context in unmodifiable map.
     *
     * @return
     */
    Map<Attribute, AttributeValue> getNonDefaultValuesByAttribute();

    /**
     * Sets attribute in context and returns change, if there has been any modifications in value.
     *
     * @param value if value is null, then value is equal to default value.
     * @return change or empty optional.
     */
    Optional<AttributeChange> setAttribute(Attribute attribute, AttributeValue value);

    /**
     * Returns both default and non default values.
     *
     * @param attribute
     * @return
     */
    AttributeValue getAttribute(Attribute attribute);

    /**
     * Attribute configuration that this context uses for resolving values.
     *
     * @return
     */
    AttributeConfig getAttributeConfig(Class<? extends Attribute> attributeClass);

}
