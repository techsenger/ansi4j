/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.impl;

import com.techsenger.ansi4j.css.api.Attribute;
import com.techsenger.ansi4j.css.api.AttributeChange;
import com.techsenger.ansi4j.css.api.AttributeValue;

/**
 *
 * @author Pavel Castornii
 */
public class AttributeChangeImpl implements AttributeChange {

    private final Attribute key;

    private final AttributeValue oldValue;

    private final AttributeValue newValue;

    public AttributeChangeImpl(Attribute attribute, AttributeValue oldValue, AttributeValue newValue) {
        this.key = attribute;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Attribute getAttribute() {
        return key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AttributeValue getOldValue() {
        return this.oldValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AttributeValue getNewValue() {
        return this.newValue;
    }


}
