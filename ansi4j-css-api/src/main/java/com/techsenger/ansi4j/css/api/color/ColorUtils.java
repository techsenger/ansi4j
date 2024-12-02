/*
 * Copyright 2022-2024 Pavel Castornii.
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

package com.techsenger.ansi4j.css.api.color;

/**
 *
 * @author Pavel Castornii
 */
public final class ColorUtils {

    /**
     * Returns #RRGGBBAA string.
     *
     * @param rgba
     * @return
     */
    public static String toHex(int rgba) {
        String hex = Integer.toUnsignedString(rgba, 16);
        hex = "#" + "0".repeat(8 - hex.length()) + hex;
        return hex;
    }

    public static int getRgba(int rgb, int alpha) {
        alpha = Math.max(0, Math.min(255, alpha));
        return (rgb << 8) | (alpha & 0xFF);
    }

    public static int getRgb(int rgba) {
        var rgb = (rgba >>> 8);
        return rgb;
    }

    public static int setAlpha(int rgba, int alpha) {
        alpha = Math.max(0, Math.min(255, alpha));
        rgba = (rgba & 0xFFFFFF00) | (alpha & 0xFF);
        return rgba;
    }

    private ColorUtils() {
        //empty
    }
}
