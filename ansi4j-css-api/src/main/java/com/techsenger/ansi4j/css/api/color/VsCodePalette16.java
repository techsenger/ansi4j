/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.api.color;

/**
 * 16 colors for VS. See https://en.wikipedia.org/wiki/ANSI_escape_code#3-bit_and_4-bit
 *
 * @author Pavel Castornii
 */
public class VsCodePalette16 implements Palette16 {

    private final int[] colors = new int[] {
        0x000000, 0xcd3131, 0x0dbc79, 0xe5e510, 0x2472c8, 0xbc3fbc, 0x11a8cd, 0xe5e5e5,
        0x666666, 0xf14c4c, 0x23d18b, 0xf5f543, 0x3b8eea, 0xd670d6, 0x29b8db, 0xe5e5e5
    };

    @Override
    public int[] getColors() {
        return colors;
    }
}
