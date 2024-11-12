/*
 * Copyright 2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.impl.attribute;

import com.techsenger.ansi4j.css.api.attribute.Attribute;
import com.techsenger.ansi4j.css.api.attribute.AttributeGroup;
import java.util.Objects;

/**
 *
 * @author Pavel Castornii
 */
public class AttributeImpl<T> implements Attribute<T> {

    private final String name;

    private final AttributeGroup group;

    private T value;

    private T defaultValue;

    public AttributeImpl(AttributeGroup group, String name) {
        this.name = name;
        this.group = group;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public T getDefaultValue() {
        return defaultValue;
    }

    @Override
    public boolean isValueDefault() {
        return Objects.equals(this.value, this.defaultValue);
    }

    @Override
    public AttributeGroup getGroup() {
        return group;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setBothValues(T value) {
        this.value = value;
        this.defaultValue = value;
    }
}
