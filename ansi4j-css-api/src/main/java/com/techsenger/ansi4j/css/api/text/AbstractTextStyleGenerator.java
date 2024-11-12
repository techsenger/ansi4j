/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.api.text;

import com.techsenger.ansi4j.css.api.attribute.AttributeGroup;
import com.techsenger.ansi4j.css.api.GroupStyleGenerator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pavel Castornii
 */
public abstract class AbstractTextStyleGenerator implements GroupStyleGenerator<TextAttributeGroup> {

    private TextAttributeGroup group;

    @Override
    public AttributeGroup.Key<TextAttributeGroup> getGroupKey() {
        return TextAttributeGroup.KEY;
    }

    @Override
    public void initialize(TextAttributeGroup group) {
        this.group = group;
    }

    @Override
    public List<String> generate() {
        List<String> declarations = new ArrayList<>();
        generateBlinking(declarations);
        generateColor(declarations);
        generateFont(declarations);
        generateItalic(declarations);
        generateStrikethrough(declarations);
        generateUnderline(declarations);
        generateVisibility(declarations);
        generateWeight(declarations);
        return declarations;
    }

    public TextAttributeGroup getGroup() {
        return group;
    }

    protected void generateWeight(List<String> declarations) {
        var attribute = this.group.getWeightAttribute();
        if (!attribute.isValueDefault()) {
            switch (attribute.getValue()) {
                case BOLD:
                    doWeightBold(declarations);
                    break;
                case FAINT:
                    doWeightFaint(declarations);
                    break;
                case NORMAL:
                    doWeightNormal(declarations);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown value=" + attribute.getValue());
            }
        }
    };

    protected void generateItalic(List<String> declarations) {
        var attribute = this.group.getItalicAttribute();
        if (!attribute.isValueDefault()) {
            if (Boolean.TRUE.equals(attribute.getValue())) {
                doItalicOn(declarations);
            } else {
                doItalicOff(declarations);
            }
        }
    };

    protected void generateUnderline(List<String> declarations) {
        var attribute = this.group.getUnderlineAttribute();
        if (!attribute.isValueDefault()) {
            switch (attribute.getValue()) {
                case SINGLE:
                    doUnderlineSingle(declarations);
                    break;
                case DOUBLE:
                    doUnderlineDouble(declarations);
                    break;
                case OFF:
                    doUnderlineOff(declarations);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown value=" + attribute.getValue());
            }
        }
    };

    protected void generateBlinking(List<String> declarations) {
        var attribute = this.group.getBlinkingAttribute();
        if (!attribute.isValueDefault()) {
            switch (attribute.getValue()) {
                case SLOW:
                    doBlinkingSlow(declarations);
                    break;
                case RAPID:
                    doBlinkingRapid(declarations);
                    break;
                case OFF:
                    doBlinkingOff(declarations);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown value=" + attribute.getValue());
            }
        }
    };

    protected void generateVisibility(List<String> declarations) {
        var attribute = this.group.getVisibilityAttribute();
        if (!attribute.isValueDefault()) {
            if (Boolean.TRUE.equals(attribute.getValue())) {
                doVisibilityOn(declarations);
            } else {
                doVisibilityOff(declarations);
            }
        }
    };

    protected void generateStrikethrough(List<String> declarations) {
        var attribute = this.group.getStrikethroughAttribute();
        if (!attribute.isValueDefault()) {
            if (Boolean.TRUE.equals(attribute.getValue())) {
                doStrikethroughOn(declarations);
            } else {
                doStrikethroughOff(declarations);
            }
        }
    };

    protected void generateFont(List<String> declarations) {
        var attribute = this.group.getFontAttribute();
        if (!attribute.isValueDefault()) {
            //single qutes are set by developer in config
            doFont(attribute.getValue(), declarations);
        }
    };

    protected void generateColor(List<String> declarations) {
        boolean rvOn = false;
        //when reverse video is on then style declarations are generated in any case
        var rvAttr = this.group.getReverseVideoAttribute();
        if (Boolean.TRUE.equals(rvAttr.getDefaultValue()) || Boolean.TRUE.equals(rvAttr.getValue())) {
            rvOn = true;
        }
        var attribute = this.group.getFgColorAttribute();
        var color = resolveFgColor();
        if (attribute.getDefaultValue() != color || rvOn) {
            doFgColor(color, declarations);
        }
        attribute = this.group.getBgColorAttribute();
        color = resolveBgColor();
        if (attribute.getDefaultValue() != color || rvOn) {
            doBgColor(color, declarations);
        }
    };

    protected String toHexColor(int i) {
        return String.format("%06X", i);
    }

    protected int resolveFgColor() {
        int color;
        if (Boolean.TRUE.equals(this.group.getReverseVideoAttribute().getValue())) {
            color = this.group.getBgColorAttribute().getValue();
        } else {
            color = this.group.getFgColorAttribute().getValue();
        }
        return color;
    }

    protected int resolveBgColor() {
        int color;
        if (Boolean.TRUE.equals(this.group.getReverseVideoAttribute().getValue())) {
            color = this.group.getFgColorAttribute().getValue();
        } else {
            color = this.group.getBgColorAttribute().getValue();
        }
        return color;
    }

    protected abstract void doWeightBold(List<String> declarations);

    protected abstract void doWeightFaint(List<String> declarations);

    protected abstract void doWeightNormal(List<String> declarations);

    protected abstract void doItalicOn(List<String> declarations);

    protected abstract void doItalicOff(List<String> declarations);

    protected abstract void doUnderlineSingle(List<String> declarations);

    protected abstract void doUnderlineDouble(List<String> declarations);

    protected abstract void doUnderlineOff(List<String> declarations);

    protected abstract void doBlinkingSlow(List<String> declarations);

    protected abstract void doBlinkingRapid(List<String> declarations);

    protected abstract void doBlinkingOff(List<String> declarations);

    protected abstract void doVisibilityOff(List<String> declarations);

    protected abstract void doVisibilityOn(List<String> declarations);

    protected abstract void doStrikethroughOn(List<String> declarations);

    protected abstract void doStrikethroughOff(List<String> declarations);

    protected abstract void doFont(String font, List<String> declarations);

    protected abstract void doFgColor(int color, List<String> declarations);

    protected abstract void doBgColor(int color, List<String> declarations);
}
