/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.demo.utils;

import com.techsenger.ansi4j.core.api.Environment;
import com.techsenger.ansi4j.core.api.ParserFactory;
import com.techsenger.ansi4j.core.api.iso6429.ControlFunctionType;
import com.techsenger.ansi4j.css.api.StyleProcessor;
import com.techsenger.ansi4j.css.api.color.Color;
import com.techsenger.ansi4j.css.api.color.Palette16;
import com.techsenger.ansi4j.css.api.color.Palette256;
import com.techsenger.ansi4j.css.api.text.RtfxTextAreaStyleGenerator;
import com.techsenger.ansi4j.css.api.text.TextAttributeGroupConfig;
import com.techsenger.ansi4j.css.api.text.TextFlowStyleGenerator;
import com.techsenger.ansi4j.css.api.text.WebViewStyleGenerator;
import com.techsenger.ansi4j.css.demo.Constants;
import java.util.List;

/**
 *
 * @author Pavel Castornii
 */
public final class Ansi4jUtils {

    public static ParserFactory createParserFactory() {
        var factory = new ParserFactory.Builder()
            .environment(Environment._7_BIT)
            .functionTypes(
                    ControlFunctionType.C0_SET,
                    ControlFunctionType.C1_SET,
                    ControlFunctionType.CONTROL_SEQUENCE,
                    ControlFunctionType.INDEPENDENT_FUNCTION,
                    ControlFunctionType.CONTROL_STRING)
            .build();
        return factory;
    }

    public static StyleProcessor createProcessor(int defaultFgColor, int defaultBgColor, Palette16 palette16) {
        Palette256 palette256 = null;
        if (palette16 instanceof Palette256) {
            palette256 = (Palette256) palette16;
        }
        TextAttributeGroupConfig config = new TextAttributeGroupConfig.Builder()
                .defaultFgColor(new Color(defaultFgColor))
                .defaultBgColor(new Color(defaultBgColor))
                .fontFamilies(List.of(Constants.FONT_FAMILY, Constants.ALTERNATIVE_FONT_FAMILY))
                .extraColorsEnabled(true)
                .palette16(palette16)
                .palette256(palette256)
                .build();

        var processor = new StyleProcessor.Builder()
                .configs(config)
                .generators(new WebViewStyleGenerator(),
                        new TextFlowStyleGenerator(),
                        new RtfxTextAreaStyleGenerator())
                .build();
        return processor;
    }

    private Ansi4jUtils() {
        //empty
    }
}