/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.api.text;

/**
 *
 * @author Pavel Castornii
 */
public final class TextAttributeValue {

    public static final int WEIGHT_NORMAL = 0;

    public static final int WEIGHT_BOLD = 1;

    public static final int WEIGHT_FAINT = 2;

    public static final int ITALIC_OFF = 0;

    public static final int ITALIC_ON = 1;

    public static final int UNDERLINE_OFF = 0;

    public static final int UNDERLINE_SINGLE = 1;

    public static final int UNDERLINE_DOUBLE = 2;

    public static final int BLINKING_OFF = 0;

    public static final int BLINKING_SLOW = 1;

    public static final int BLINKING_RAPID = 2;

    public static final int REVERSE_VIDEO_OFF = 0;

    public static final int REVERSE_VIDEO_ON = 1;

    public static final int VISIBILITY_HIDDEN = 0;

    public static final int VISIBILITY_VISIBLE = 1;

    public static final int STRIKETHROUGH_OFF = 0;

    public static final int STRIKETHROUGH_ON = 1;

    private TextAttributeValue() {
        //empty
    }

}
