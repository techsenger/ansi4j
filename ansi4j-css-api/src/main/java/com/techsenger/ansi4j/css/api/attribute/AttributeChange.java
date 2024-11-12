/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.api.attribute;

import javax.annotation.concurrent.Immutable;

/**
 * Change in value of some attribute.
 *
 * @author Pavel Castornii
 */
@Immutable
public final class AttributeChange<T> {

    private final Attribute key;

    private final T oldValue;

    private final T newValue;

    public AttributeChange(Attribute attribute, T oldValue, T newValue) {
        this.key = attribute;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    /**
     * Returns attribute.
     *
     * @return
     */
    public Attribute getAttribute() {
        return key;
    }

    /**
     * Returns the old value of the attribute. It can be either default and non default.
     *
     * @return
     */
    public T getOldValue() {
        return this.oldValue;
    }

    /**
     * Returns the new value of the attribute. It can be either default and non default.
     * @return
     */
    public T getNewValue() {
        return this.newValue;
    }

}
