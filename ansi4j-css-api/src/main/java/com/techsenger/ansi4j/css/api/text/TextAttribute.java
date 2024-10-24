/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.api.text;

import com.techsenger.ansi4j.css.api.Attribute;


/**
 * @author Pavel Castornii
 */
public enum TextAttribute implements Attribute {

    WEIGHT,

    ITALIC,

    UNDERLINE,

    BLINKING,

    REVERSE_VIDEO,

    VISIBILITY,

    STRIKETHROUGH,

    FONT,

    FOREGROUND_COLOR,

    BACKGROUND_COLOR;
}
