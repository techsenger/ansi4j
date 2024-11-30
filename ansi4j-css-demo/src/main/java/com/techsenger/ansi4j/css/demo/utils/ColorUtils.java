/*
 * Copyright 2024 Pavel Castornii.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
