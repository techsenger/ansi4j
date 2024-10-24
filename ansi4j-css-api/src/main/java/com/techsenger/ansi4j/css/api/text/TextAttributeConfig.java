/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.api.text;

import java.util.List;
import com.techsenger.ansi4j.css.api.AttributeConfig;
import com.techsenger.ansi4j.css.api.AttributeValue;
import com.techsenger.ansi4j.css.api.color.Palette16;
import com.techsenger.ansi4j.css.api.color.Palette256;
import com.techsenger.ansi4j.css.api.color.Palette8;

/**
 *
 * @author Pavel Castornii
 */
public interface TextAttributeConfig extends AttributeConfig {

    /**
     * Default foreground color (0xXXXXXX).
     *
     * @return
     */
    AttributeValue getDefaultForegroundColor();

    /**
     * Default background color (0xXXXXXX).
     *
     * @return
     */
    AttributeValue getDefaultBackgroundColor();

    /**
     * Default font has index 0, alternative_1 index 1, etc.
     *
     * @return
     */
    List<String> getFontFamilies();

    /**
     * ISO 6429 supports only 8 colors (3 bits). However, today many terminals supports 4, 8 and 24 bit colors.
     * This flag is used to enable support of these extra colors.
     *
     * @return
     */
    boolean areExtraColorsEnabled();

    /**
     * Returns palette that is used if extra colors ARE NOT enabled.
     *
     * @return
     */
    Palette8 getPalette8();

    /**
     * Returns 16 color palette that is used if extra colors ARE enabled.
     *
     * @return
     */
    Palette16 getPalette16();

    /**
     * Returns 256 palette that is used if extra colors ARE enabled.
     *
     * @return
     */
    Palette256 getPalette256();

}
