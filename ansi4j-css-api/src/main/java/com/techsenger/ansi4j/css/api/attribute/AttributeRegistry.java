/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.api.attribute;

import java.util.Set;
import javax.annotation.concurrent.NotThreadSafe;

/**
 *
 * @author Pavel Castornii
 */
@NotThreadSafe
public interface AttributeRegistry {

    /**
     * Returns an attribute group by group key.
     *
     * @param attribute
     * @return
     */
    <T extends AttributeGroup<T>> T getGroup(AttributeGroup.Key<T> key);

    /**
     * Returns the keys of all groups.
     *
     * @return
     */
    Set<AttributeGroup.Key<?>> getGroupKeys();
}
