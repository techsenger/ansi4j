/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.api;

import javax.annotation.concurrent.Immutable;

/**
 *
 * @author Pavel Castornii
 */
@Immutable
public interface AttributeConfig {

    /**
     * Returns attribute class that this configuration supports.
     * @return
     */
    Class<? extends Attribute> getTargetAttribute();

    /**
     * Returns default value for the attribute.
     *
     * @param attribute
     * @return
     */
    AttributeValue getDefaultValue(Attribute attribute);

}
