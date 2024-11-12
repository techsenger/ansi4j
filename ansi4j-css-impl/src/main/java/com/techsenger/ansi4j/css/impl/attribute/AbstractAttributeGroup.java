/*
 * Copyright 2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.impl.attribute;

import com.techsenger.ansi4j.css.api.attribute.Attribute;
import com.techsenger.ansi4j.css.api.attribute.AttributeGroup;
import java.util.List;

/**
 *
 * @author Pavel Castornii
 */
public abstract class AbstractAttributeGroup<T extends AttributeGroup<T>> implements AttributeGroup<T> {

    public abstract List<Attribute<?>> getAttributes();

}
