/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.demo.utils;

import javafx.scene.paint.Color;

/**
 *
 * @author Pavel Castornii
 */
public final class ColorUtils {

    public static String toHex(int color) {
        return String.format("#%06X", color);
    }

    public static int toInt(Color color) {
        int rgb = ((int) (color.getRed() * 255) << 16)
                | ((int) (color.getGreen() * 255) << 8)
                | (int) (color.getBlue() * 255);
        return rgb;
    }

    private ColorUtils() {
        //empty
    }
}
