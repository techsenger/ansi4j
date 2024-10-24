/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.color;

import com.techsenger.ansi4j.css.api.color.Palette16;

/**
 * 16 colors for VGA. See https://en.wikipedia.org/wiki/ANSI_escape_code#3-bit_and_4-bit
 *
 * @author Pavel Castornii
 */
public class VgaPalette16  implements Palette16 {

    private static final int[] COLORS = {

            0x000000, 0xaa0000, 0x00aa00, 0xaa5500, 0x0000aa, 0xaa00aa, 0x00aaaa, 0xaaaaaa,
            0x555555, 0xff5555, 0x55ff55, 0xffff55, 0x5555ff, 0xff55ff, 0x55ffff, 0xffffff

    };

    private int[] colors = COLORS.clone();

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getColors() {
        return colors;
    }

}
