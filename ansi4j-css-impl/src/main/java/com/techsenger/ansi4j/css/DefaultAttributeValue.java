/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css;

import java.util.Objects;
import java.util.OptionalInt;
import com.techsenger.ansi4j.css.api.AttributeValue;

/**
 *
 * @author Pavel Castornii
 */
public class DefaultAttributeValue implements AttributeValue {

    private final OptionalInt index;

    private final Object value;

    private final Object flag;

    public DefaultAttributeValue(Integer index, Object value) {
        this(index, value, null);
    }

    public DefaultAttributeValue(Integer index, Object value, Object flag) {
        if (index == null) {
            this.index = OptionalInt.empty();
        } else {
            this.index = OptionalInt.of(index);
        }
        this.value = value;
        this.flag = flag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionalInt getIndex() {
        return this.index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getValue() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getFlag() {
        return this.flag;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.index);
        hash = 11 * hash + Objects.hashCode(this.value);
        hash = 11 * hash + Objects.hashCode(this.flag);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultAttributeValue other = (DefaultAttributeValue) obj;
        if (!Objects.equals(this.index, other.index)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return Objects.equals(this.flag, other.flag);
    }
}
