/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
