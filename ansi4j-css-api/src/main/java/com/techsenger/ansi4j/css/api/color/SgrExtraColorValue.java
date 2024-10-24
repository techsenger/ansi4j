/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.api.color;

/**
 * ISO 6429 DOES NOT include these parameters values. However, they are widely used in many types of terminal.
 * See details here https://unix.stackexchange.com/a/696593/139986
 *
 * @author Pavel Castornii
 */
public final class SgrExtraColorValue {

    public static final int BRIGHT_BLACK_DISPLAY = 90;

    public static final int BRIGHT_RED_DISPLAY = 91;

    public static final int BRIGHT_GREEN_DISPLAY = 92;

    public static final int BRIGHT_YELLOW_DISPLAY = 93;

    public static final int BRIGHT_BLUE_DISPLAY = 94;

    public static final int BRIGHT_MAGENTA_DISPLAY = 95;

    public static final int BRIGHT_CYAN_DISPLAY = 96;

    public static final int BRIGHT_WHITE_DISPLAY = 97;


    public static final int BRIGHT_BLACK_BACKGROUND = 100;

    public static final int BRIGHT_RED_BACKGROUND = 101;

    public static final int BRIGHT_GREEN_BACKGROUND = 102;

    public static final int BRIGHT_YELLOW_BACKGROUND = 103;

    public static final int BRIGHT_BLUE_BACKGROUND = 104;

    public static final int BRIGHT_MAGENTA_BACKGROUND = 105;

    public static final int BRIGHT_CYAN_BACKGROUND = 106;

    public static final int BRIGHT_WHITE_BACKGROUND = 107;

    /**
     * 38;x.
     */
    public static final int DISPLAY_8_OR_24_BIT_PALETTE = 38;

    /**
     * 48;x.
     */
    public static final int BACKGROUND_8_OR_24_BIT_PALETTE = 48;

    /**
     * 38;5;⟨n⟩m OR 48;5;⟨n⟩m.
     */
    public static final int PALETTE_8_BIT = 5;

    /**
     * 38;2;⟨r⟩;⟨g⟩;⟨b⟩ OR 48;2;⟨r⟩;⟨g⟩;⟨b⟩.
     */
    public static final int PALETTE_24_BIT = 2;

    private SgrExtraColorValue() {
        //empty
    }
}
