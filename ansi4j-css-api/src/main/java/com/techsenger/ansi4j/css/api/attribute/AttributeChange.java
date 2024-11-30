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
