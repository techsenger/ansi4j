/*
 * Copyright 2024 Pavel Castornii.
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

/**
 *
 * @author Pavel Castornii
 */
public interface AttributeGroup<T extends AttributeGroup<T>> {

    class Key<S extends AttributeGroup<S>> { }

    /**
     * Returns group key.
     *
     * @return
     */
    Key<T> getKey();

    /**
     * Returns the config of the group.
     *
     * @return
     */
    AttributeGroupConfig<T> getConfig();

    /**
     * Resets all the attributes of the group to their default values.
     */
    void reset();
}
