/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.api.iso6429;

/**
 *
 * @author Pavel Castornii
 */
public final class SgrParameterValue {

    public static final int DEFAULT_RENDITION = 0;

    public static final int BOLD_OR_INCREASED_INTENSITY = 1;

    public static final int FAINT_OR_DECREASED_INTENSITY_OR_SECOND_COLOUR = 2;

    public static final int ITALICIZED = 3;

    public static final int SINGLY_UNDERLINED = 4;

    public static final int SLOWLY_BLINKING = 5;

    public static final int RAPIDLY_BLINKING = 6;

    public static final int NEGATIVE_IMAGE = 7;

    public static final int CONCEALED_CHARACTERS = 8;

    public static final int CROSSED_OUT = 9;

    public static final int PRIMARY_FONT = 10;

    public static final int FIRST_ALTERNATIVE_FONT = 11;

    public static final int SECOND_ALTERNATIVE_FONT = 12;

    public static final int THIRD_ALTERNATIVE_FONT = 13;

    public static final int FOURTH_ALTERNATIVE_FONT = 14;

    public static final int FIFTH_ALTERNATIVE_FONT = 15;

    public static final int SIXTH_ALTERNATIVE_FONT = 16;

    public static final int SEVENTH_ALTERNATIVE_FONT = 17;

    public static final int EIGHTH_ALTERNATIVE_FONT = 18;

    public static final int NINTH_ALTERNATIVE_FONT = 19;

    public static final int FRAKTUR = 20;

    public static final int DOUBLY_UNDERLINED = 21;

    public static final int NORMAL_COLOUR_OR_NORMAL_INTENSITY = 22;

    public static final int NOT_ITALICIZED_OR_NOT_FRAKTUR = 23;

    public static final int NOT_UNDERLINED = 24;

    public static final int STEADY = 25;

    public static final int POSITIVE_IMAGE = 27;

    public static final int REVEALED_CHARACTERS = 28;

    public static final int NOT_CROSSED_OUT = 29;

    public static final int BLACK_DISPLAY = 30;

    public static final int RED_DISPLAY = 31;

    public static final int GREEN_DISPLAY = 32;

    public static final int YELLOW_DISPLAY = 33;

    public static final int BLUE_DISPLAY = 34;

    public static final int MAGENTA_DISPLAY = 35;

    public static final int CYAN_DISPLAY = 36;

    public static final int WHITE_DISPLAY = 37;

    public static final int DEFAULT_DISPLAY_COLOUR = 39;

    public static final int BLACK_BACKGROUND = 40;

    public static final int RED_BACKGROUND = 41;

    public static final int GREEN_BACKGROUND = 42;

    public static final int YELLOW_BACKGROUND = 43;

    public static final int BLUE_BACKGROUND = 44;

    public static final int MAGENTA_BACKGROUND = 45;

    public static final int CYAN_BACKGROUND = 46;

    public static final int WHITE_BACKGROUND = 47;

    public static final int DEFAULT_BACKGROUND_COLOUR = 49;

    public static final int FRAMED = 51;

    public static final int ENCIRCLED = 52;

    public static final int OVERLINED = 53;

    public static final int NOT_FRAMED_OR_NOT_ENCIRCLED = 54;

    public static final int NOT_OVERLINED = 55;

    public static final int IDEOGRAM_UNDERLINE_OR_RIGHT_SIDE_LINE = 60;

    public static final int IDEOGRAM_DOUBLE_UNDERLINE_OR_DOUBLE_LINE_ON_THE_RIGHT_SIDE = 61;

    public static final int IDEOGRAM_OVERLINE_OR_LEFT_SIDE_LINE = 62;

    public static final int IDEOGRAM_DOUBLE_OVERLINE_OR_DOUBLE_LINE_ON_THE_LEFT_SIDE = 63;

    public static final int IDEOGRAM_STRESS_MARKING = 64;

    public static final int IDEOGRAM_RESET = 65;

    private SgrParameterValue() {
        //empty
    }
}
