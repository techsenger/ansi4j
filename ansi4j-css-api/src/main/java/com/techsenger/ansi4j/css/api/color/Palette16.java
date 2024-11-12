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
public interface Palette16 extends Palette8 {

    @Override
    default PaletteType getType() {
        return PaletteType.PALETTE_16;
    }
}
