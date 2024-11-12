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
