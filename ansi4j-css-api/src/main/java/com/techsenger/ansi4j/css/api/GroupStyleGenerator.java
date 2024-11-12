/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
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
