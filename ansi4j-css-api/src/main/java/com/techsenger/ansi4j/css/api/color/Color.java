/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.api.color;

/**
 *
 * @author Pavel Castornii
 */
public class Color {

    private final PaletteType paletteType;

    private final int index;

    private int value;

    public Color(PaletteType paletteType, int index) {
        this.paletteType = paletteType;
        this.index = index;
        this.value = -1;
    }

    public Color(int value) {
        this.value = value;
        this.paletteType = null;
        this.index = -1;
    }

    public Color(PaletteType paletteType, int index, int value) {
        this.paletteType = paletteType;
        this.index = index;
        this.value = value;
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
     * Returns the value of the color.
     *
     * @return
     */
    public int getValue() {
        return value;
    }
}
