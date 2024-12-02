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
public class Color {

    private final PaletteType paletteType;

    private final int index;

    private int rgb;

    public Color(PaletteType paletteType, int index) {
        this.paletteType = paletteType;
        this.index = index;
        this.rgb = -1;
    }

    /**
     * Color as RRGGBB. This is the only place where RGB is used. Everywhere RGBA is used.
     *
     * @param rgb
     */
    public Color(int rgb) {
        this.rgb = rgb;
        this.paletteType = null;
        this.index = -1;
    }

    /**
     * Returns a palette type or null if palette is not used.
     *
     * @return
     */
    public PaletteType getPaletteType() {
        return paletteType;
    }

    /**
     * Returns a color index in the palette or -1 if palette is not used.
     *
     * @return
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns color as RRGGBBAA.
     *
     * @return
     */
    public int getRgb() {
        return rgb;
    }

    /**
     * Sets rgba.
     * @param rgb
     */
    public void setRgb(int rgb) {
        this.rgb = rgb;
    }
}
