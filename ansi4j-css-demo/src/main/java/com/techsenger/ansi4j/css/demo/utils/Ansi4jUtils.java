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

package com.techsenger.ansi4j.css.demo.utils;

import com.techsenger.ansi4j.core.api.Environment;
import com.techsenger.ansi4j.core.api.ParserFactory;
import com.techsenger.ansi4j.core.api.iso6429.ControlFunctionType;
import com.techsenger.ansi4j.css.api.GroupStyleGenerator;
import com.techsenger.ansi4j.css.api.StyleProcessor;
import com.techsenger.ansi4j.css.api.color.Color;
import com.techsenger.ansi4j.css.api.color.ColorUtils;
import com.techsenger.ansi4j.css.api.color.Palette16;
import com.techsenger.ansi4j.css.api.color.Palette256;
import com.techsenger.ansi4j.css.api.text.RtfxTextAreaStyleGenerator;
import com.techsenger.ansi4j.css.api.text.TextAttributeGroupConfig;
import com.techsenger.ansi4j.css.api.text.TextFlowStyleGenerator;
import com.techsenger.ansi4j.css.api.text.WebViewStyleGenerator;
import com.techsenger.ansi4j.css.demo.Constants;
import com.techsenger.ansi4j.css.demo.TargetControl;
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

    public static StyleProcessor createProcessor(TargetControl targetControl, int defaultFgColor, int defaultBgColor,
            Palette16 palette16) {
        Palette256 palette256 = null;
        if (palette16 instanceof Palette256) {
            palette256 = (Palette256) palette16;
        }
        TextAttributeGroupConfig config = new TextAttributeGroupConfig.Builder()
                .defaultFgColor(new Color(ColorUtils.getRgb(defaultFgColor)))
                .defaultBgColor(new Color(ColorUtils.getRgb(defaultBgColor)))
                .fontFamilies(List.of(Constants.FONT_FAMILY, Constants.ALTERNATIVE_FONT_FAMILY))
                .extraColorsEnabled(true)
                .palette16(palette16)
                .palette256(palette256)
                .build();

        GroupStyleGenerator generator;
        switch (targetControl) {
            case WEB_VIEW:
                generator = new WebViewStyleGenerator();
                break;
            case TEXT_FLOW:
                generator = new TextFlowStyleGenerator();
                break;
            case RTFX_TEXT_AREA:
                generator = new RtfxTextAreaStyleGenerator();
                break;
            default:
                throw new AssertionError();
        }

        var processor = new StyleProcessor.Builder()
                .configs(config)
                .generators(generator)
                .build();
        return processor;
    }

    private Ansi4jUtils() {
        //empty
    }
}
