/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.api;

import java.util.OptionalInt;
import javax.annotation.concurrent.Immutable;

/**
 *
 * @author Pavel Castornii
 */
@Immutable
public interface AttributeValue {

    /**
     * Index in fonts, palette etc. Some attributes don't have index.
     * @return
     */
    OptionalInt getIndex();

    /**
     * Attribute value. Never null.
     * @return
     */
    Object getValue();

    /**
     * Flag of the attribute, for example, palette object etc.
     * @return
     */
    Object getFlag();
}
