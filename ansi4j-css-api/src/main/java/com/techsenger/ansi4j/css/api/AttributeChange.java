/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.api;

import javax.annotation.concurrent.Immutable;

/**
 * Change in value of some attribute.
 *
 * @author Pavel Castornii
 */
@Immutable
public interface AttributeChange {

    /**
     * Returns attribute.
     *
     * @return
     */
    Attribute getAttribute();

    /**
     * Returns old value of the attribute. It can be both default and non default values.
     *
     * @return
     */
    AttributeValue getOldValue();

    /**
     * Returns new value of the attribute. It can be both default and non default values.
     * @return
     */
    AttributeValue getNewValue();
}
