/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.api.color;

/**
 * 16 colors for Xterm. See https://en.wikipedia.org/wiki/ANSI_escape_code#3-bit_and_4-bit
 *
 * @author Pavel Castornii
 */
public class XtermPalette16 implements Palette16 {

    private final int[] colors = new int[] {
            0x000000, 0xcd0000, 0x00cd00, 0xcdcd00, 0x0000ee, 0xcd00cd, 0x00cdcd, 0xe5e5e5,
            0x7f7f7f, 0xff0000, 0x00ff00, 0xffff00, 0x5c5cff, 0xff00ff, 0x00ffff, 0xffffff
    };

    @Override
    public int[] getColors() {
        return colors;
    }
}
