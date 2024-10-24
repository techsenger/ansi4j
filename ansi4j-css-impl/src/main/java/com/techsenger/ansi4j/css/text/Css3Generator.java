/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.text;

import java.util.ArrayList;
import com.techsenger.ansi4j.css.api.text.TextAttribute;
import com.techsenger.ansi4j.css.api.text.TextAttributeValue;

/**
 *
 * @author Pavel Castornii
 */
public class Css3Generator extends AbstractCssGenerator {

    private DeclarationGenerator weightGenerator = (change, context) -> {
        var declarations = new ArrayList<String>();
        var newValue = (int) change.getNewValue().getValue();
        switch (newValue) {
            case TextAttributeValue.WEIGHT_BOLD:
                declarations.add("font-weight: bold");
                break;
            case TextAttributeValue.WEIGHT_FAINT:
                declarations.add("font-weight: 300");
                break;
            case TextAttributeValue.WEIGHT_NORMAL:
                declarations.add("font-weight: normal");
                break;
            default:
                throw new IllegalArgumentException("Unknown value=" + newValue);
        }
        return declarations;
    };

    private DeclarationGenerator italicGenerator = (change, context) -> {
        var declarations = new ArrayList<String>();
        var newValue = (int) change.getNewValue().getValue();
        switch (newValue) {
            case TextAttributeValue.ITALIC_ON:
                declarations.add("font-style: italic");
                break;
            case TextAttributeValue.ITALIC_OFF:
                declarations.add("font-style: normal");
                break;
            default:
                throw new IllegalArgumentException("Unknown value=" + newValue);
        }
        return declarations;
    };

    /**
     * We use "border-bottom" property here, but not "text-decoration-line: underline" because
     * "text-decoration-style: solid; text-decoration-line: line-through;" we use for STRIKETHROUGH. If we
     * set "text-decoration-style: double" for double underline then STRIKETHROUGH will be also double.
     */
    private DeclarationGenerator underlineGenerator = (change, context) -> {
        var declarations = new ArrayList<String>();
        var newValue = (int) change.getNewValue().getValue();
        switch (newValue) {
            case TextAttributeValue.UNDERLINE_SINGLE:
                declarations.add("border-bottom-style: solid");
                declarations.add("border-bottom-color: #"
                        + this.toHexColor(this.getResolvedForegroundColor(context)));
                //declarations.add("border-block-width: 3px");
                break;
            case TextAttributeValue.UNDERLINE_DOUBLE:
                declarations.add("border-bottom-style: double");
                break;
            case TextAttributeValue.UNDERLINE_OFF:
                declarations.add("border-bottom-style: none");
                break;
            default:
                throw new IllegalArgumentException("Unknown value=" + newValue);
        }
        return declarations;
    };

    /**
     * To support blinking it is required to add the following @keyframes to CSS style sheet:
     * <code>@keyframes ansi4j-blinker {50% { opacity: 0; }}</code>.
     */
    private DeclarationGenerator blinkingGenerator = (change, context) -> {
        var declarations = new ArrayList<String>();
        var newValue = (int) change.getNewValue().getValue();
        switch (newValue) {
            case TextAttributeValue.BLINKING_SLOW:
                declarations.add("animation: ansi4j-blinker 1s step-start infinite");
                break;
            case TextAttributeValue.BLINKING_RAPID:
                declarations.add("animation: ansi4j-blinker 0.35s step-start infinite");
                break;
            case TextAttributeValue.BLINKING_OFF:
                declarations.add("animation: none");
                break;
            default:
                throw new IllegalArgumentException("Unknown value=" + newValue);
        }
        return declarations;
    };

    private DeclarationGenerator reverseVideoGenerator = (change, context) -> {
        var declarations = new ArrayList<String>();
        var newValue = (int) change.getNewValue().getValue();
        declarations.add("color: #" + this.toHexColor(this.getResolvedForegroundColor(context)));
        declarations.add("background-color: #" + this.toHexColor(this.getResolvedBackgroundColor(context)));
        return declarations;
    };

    private DeclarationGenerator visibilityGenerator = (change, context) -> {
        var declarations = new ArrayList<String>();
        var newValue = (int) change.getNewValue().getValue();
        switch (newValue) {
            case TextAttributeValue.VISIBILITY_HIDDEN:
                declarations.add("visibility:hidden");
                break;
            case TextAttributeValue.VISIBILITY_VISIBLE:
                declarations.add("visibility:visible");
                break;
            default:
                throw new IllegalArgumentException("Unknown value=" + newValue);
        }
        return declarations;
    };

    private DeclarationGenerator strikethroughGenerator = (change, context) -> {
        var declarations = new ArrayList<String>();
        var newValue = (int) change.getNewValue().getValue();
        switch (newValue) {
            case TextAttributeValue.STRIKETHROUGH_ON:
                declarations.add("text-decoration-line: line-through");
                break;
            case TextAttributeValue.STRIKETHROUGH_OFF:
                declarations.add("text-decoration-line: none");
                break;
            default:
                throw new IllegalArgumentException("Unknown value=" + newValue);
        }
        return declarations;
    };

    private DeclarationGenerator fontGenerator = (change, context) -> {
        var declarations = new ArrayList<String>();
        var font = (String) change.getNewValue().getValue();
        declarations.add("font-family: " + font);
        return declarations;
    };

    private DeclarationGenerator colorGenerator = (change, context) -> {
        var declarations = new ArrayList<String>();
        var newValue = (int) change.getNewValue().getValue();
        if (context.getNonDefaultValuesByAttribute().containsKey(TextAttribute.REVERSE_VIDEO)) {
            if (change.getAttribute() == TextAttribute.FOREGROUND_COLOR) {
                declarations.add("background-color: #" + this.toHexColor(newValue));
            } else if (change.getAttribute() == TextAttribute.BACKGROUND_COLOR) {
                declarations.add("color: #" + this.toHexColor(newValue));
            }
        } else {
            if (change.getAttribute() == TextAttribute.FOREGROUND_COLOR) {
                declarations.add("color: #" + this.toHexColor(newValue));
            } else if (change.getAttribute() == TextAttribute.BACKGROUND_COLOR) {
                declarations.add("background-color: #" + this.toHexColor(newValue));
            }
        }
        return declarations;
    };

    public Css3Generator() {
        var map = this.getGeneratorsByAttribuiteKey();
        map.put(TextAttribute.WEIGHT, weightGenerator);
        map.put(TextAttribute.ITALIC, italicGenerator);
        map.put(TextAttribute.UNDERLINE, underlineGenerator);
        map.put(TextAttribute.BLINKING, blinkingGenerator);
        map.put(TextAttribute.REVERSE_VIDEO, reverseVideoGenerator);
        map.put(TextAttribute.VISIBILITY, visibilityGenerator);
        map.put(TextAttribute.STRIKETHROUGH, strikethroughGenerator);
        map.put(TextAttribute.FONT, fontGenerator);
        map.put(TextAttribute.FOREGROUND_COLOR, colorGenerator);
        map.put(TextAttribute.BACKGROUND_COLOR, colorGenerator);
    }
}
