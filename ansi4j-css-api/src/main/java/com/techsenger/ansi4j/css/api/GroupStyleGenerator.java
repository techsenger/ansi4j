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

package com.techsenger.ansi4j.css.api;

import com.techsenger.ansi4j.css.api.attribute.AttributeGroup;
import java.util.List;
import javax.annotation.concurrent.NotThreadSafe;

/**
 * CSS generator generates CSS declarations on the base of attribute values.
 *
 * @author Pavel Castornii
 */
@NotThreadSafe
public interface GroupStyleGenerator<T extends AttributeGroup<T>> {

    /**
     * Returns the type of the control this generator creates declarations for.
     *
     * @return
     */
    TargetControl getTargetControl();

    /**
     * Returns the key of the attribute group this generator creates declarations for.
     *
     * @return
     */
    AttributeGroup.Key<T> getGroupKey();

    /**
     * Initializes the generator. This method is called only once after registry is created.
     * @param group
     */
    void initialize(T group);

    /**
     * Generates collections of CSS declarations.
     *
     * @return declarations or empty list.
     */
    List<String> generate();
}
