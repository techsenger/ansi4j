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

package com.techsenger.ansi4j.css.api.text;

import com.techsenger.ansi4j.css.api.color.ColorUtils;
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

    private String resolvedFgColor;

    private String resolvedBgColor;

    private int resolvedFgRgba;

    private int resolvedBgRgba;

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
        this.resolveFgColor();
        this.resolveBgColor();
        List<String> declarations = new ArrayList<>();
        generateBlinking(declarations);
        generateColor(declarations);
        generateFont(declarations);
        generateItalic(declarations);
        generateStrikethrough(declarations);
        generateUnderline(declarations);
        generateVisibility(declarations);
        generateIntensity(declarations);
        return declarations;
    }

    public TextAttributeGroup getGroup() {
        return group;
    }

    protected void generateIntensity(List<String> declarations) {
        var attribute = this.group.getIntensityAttribute();
        if (!attribute.isValueDefault()) {
            switch (attribute.getValue()) {
                case INCREASED:
                    doIntensityIncreased(declarations);
                    break;
                case DECREASED:
                    doIntensityDecreased(declarations);
                    break;
                case NORMAL:
                    doIntensityNormal(declarations);
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

    /**
     * Color is generated always.
     * @param declarations
     */
    protected void generateColor(List<String> declarations) {
        boolean rvOn = false;
        //when reverse video is on then style declarations are generated in any case
        var rvAttr = this.group.getReverseVideoAttribute();
        if (Boolean.TRUE.equals(rvAttr.getDefaultValue()) || Boolean.TRUE.equals(rvAttr.getValue())) {
            rvOn = true;
        }
        var attribute = this.group.getFgColorAttribute();
        if (attribute.getDefaultValue() != this.resolvedFgRgba) {
            doFgColor(declarations);
        }
        attribute = this.group.getBgColorAttribute();
        if (attribute.getDefaultValue() != this.resolvedBgRgba) {
            doBgColor(declarations);
        }
    };

    protected String getResolvedFgColor() {
        return resolvedFgColor;
    }

    protected String getResolvedBgColor() {
        return resolvedBgColor;
    }

    protected abstract void doIntensityIncreased(List<String> declarations);

    protected abstract void doIntensityDecreased(List<String> declarations);

    protected abstract void doIntensityNormal(List<String> declarations);

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

    protected abstract void doFgColor(List<String> declarations);

    protected abstract void doBgColor(List<String> declarations);

    private void resolveFgColor() {
        if (Boolean.TRUE.equals(this.group.getReverseVideoAttribute().getValue())) {
            this.resolvedFgRgba = this.group.getBgColorAttribute().getValue();
        } else {
            this.resolvedFgRgba = this.group.getFgColorAttribute().getValue();
        }
        if (getGroup().getIntensityAttribute().getValue() == TextAttributeGroup.Intensity.DECREASED) {
            this.resolvedFgRgba = ColorUtils.setAlpha(this.resolvedFgRgba, 128);
        } else {
            this.resolvedFgRgba = ColorUtils.setAlpha(this.resolvedFgRgba, 255);
        }
        this.resolvedFgColor = ColorUtils.toHex(this.resolvedFgRgba);
    }

    private void resolveBgColor() {
        if (Boolean.TRUE.equals(this.group.getReverseVideoAttribute().getValue())) {
            this.resolvedBgRgba = this.group.getFgColorAttribute().getValue();
        } else {
            this.resolvedBgRgba = this.group.getBgColorAttribute().getValue();
        }
        this.resolvedBgRgba = ColorUtils.setAlpha(this.resolvedBgRgba, 255);
        this.resolvedBgColor = ColorUtils.toHex(this.resolvedBgRgba);
    }
}
