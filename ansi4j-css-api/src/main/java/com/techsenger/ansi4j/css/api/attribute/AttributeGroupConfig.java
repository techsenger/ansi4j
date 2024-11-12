/*
 * Copyright 2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.api.attribute;

/**
 *
 * @author Pavel Castornii
 */
public interface AttributeGroupConfig<T extends AttributeGroup<T>> {

    /**
     * Returns the key of the group.
     * @return
     */
    AttributeGroup.Key<T> getGroupKey();
}
