/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.it;

import java.util.List;
import com.techsenger.ansi4j.core.api.ParserFactory;
import com.techsenger.ansi4j.core.api.Environment;
import com.techsenger.ansi4j.core.api.Fragment;
import com.techsenger.ansi4j.core.api.FragmentType;
import com.techsenger.ansi4j.core.api.FunctionFragment;
import com.techsenger.ansi4j.core.api.TextFragment;
import com.techsenger.ansi4j.core.api.iso6429.ControlFunctionType;
import com.techsenger.ansi4j.core.api.iso6429.ControlSequenceFunction;
import com.techsenger.ansi4j.css.api.color.XtermPalette256;
import com.techsenger.ansi4j.css.api.text.TextAttributeGroupConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.techsenger.ansi4j.css.api.StyleProcessor;
import com.techsenger.ansi4j.css.api.TargetControl;
import com.techsenger.ansi4j.css.api.attribute.AttributeChange;
import com.techsenger.ansi4j.css.api.color.Palette256;
import com.techsenger.ansi4j.css.api.text.TextAttributeGroup;
import com.techsenger.ansi4j.css.api.text.WebViewStyleGenerator;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author Pavel Castornii
 */
public class StyleProcessorIT {

    private static final Logger logger = LoggerFactory.getLogger(StyleProcessorIT.class);

    private static ParserFactory factory;

    @BeforeAll
    public static void init() {
        factory = new ParserFactory.Builder()
            .environment(Environment._7_BIT)
            .functionTypes(
                    ControlFunctionType.C0_SET,
                    ControlFunctionType.C1_SET,
                    ControlFunctionType.CONTROL_SEQUENCE,
                    ControlFunctionType.INDEPENDENT_FUNCTION,
                    ControlFunctionType.CONTROL_STRING)
            .build();
    }

    @Test
    public void process_normalWeightWithDefaultBold_correctDeclarations() {
        var text = "Some bold text \u001b[22m some normal text";
        var parser = factory.createParser(text);
        Palette256 palette256 = new XtermPalette256();
        TextAttributeGroupConfig groupConfig = new TextAttributeGroupConfig.Builder()
                .defaultWeight(TextAttributeGroup.Weight.BOLD)
                .fontFamilies(List.of("Arial"))
                .extraColorsEnabled(true)
                .palette16(palette256)
                .palette256(palette256)
                .build();
        var processor = new StyleProcessor.Builder()
                .configs(groupConfig)
                .generators(new WebViewStyleGenerator())
                .build();
        Fragment fragment = null;
        while ((fragment = parser.parse()) != null) {
            if (fragment.getType() == FragmentType.TEXT) {
                TextFragment textFragment = (TextFragment) fragment;

            } else if (fragment.getType() == FragmentType.FUNCTION) {
                FunctionFragment functionFragment = (FunctionFragment) fragment;
                if (functionFragment.getFunction() == ControlSequenceFunction.SGR) {
                    var result = processor.process(functionFragment, TargetControl.WEB_VIEW);
                    assertThat(result.getAttributeChanges()).hasSize(1);
                    var change = (AttributeChange<TextAttributeGroup.Weight>) result.getAttributeChanges().get(0);
                    assertThat(change.getOldValue() == TextAttributeGroup.Weight.BOLD);
                    assertThat(change.getNewValue() == TextAttributeGroup.Weight.NORMAL);
                    assertThat(change.getAttribute().getName()).isEqualTo("Weight");
                    assertThat(result.getStyleDeclarations()).hasSize(1);
                    assertThat(result.getStyleDeclarations().get(0)).isEqualTo("font-weight: normal");
                }
            }
        }
    }
}


