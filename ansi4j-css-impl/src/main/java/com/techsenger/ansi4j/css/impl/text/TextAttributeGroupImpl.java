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

package com.techsenger.ansi4j.css.impl.text;

import com.techsenger.ansi4j.css.api.attribute.Attribute;
import com.techsenger.ansi4j.css.api.color.ColorUtils;
import com.techsenger.ansi4j.css.api.text.FontIndex;
import com.techsenger.ansi4j.css.api.text.TextAttributeGroupConfig;
import java.util.List;
import com.techsenger.ansi4j.css.api.text.TextAttributeGroup;
import com.techsenger.ansi4j.css.impl.attribute.AbstractAttributeGroup;
import com.techsenger.ansi4j.css.impl.attribute.AttributeImpl;


/**
 * @author Pavel Castornii
 */
public final class TextAttributeGroupImpl extends AbstractAttributeGroup<TextAttributeGroup>
        implements TextAttributeGroup {

    private final AttributeImpl<Intensity> intensityAttribute = new AttributeImpl<>(this, "Intensity");

    private final AttributeImpl<Boolean> italicAttribute = new AttributeImpl<>(this, "Italic");

    private final AttributeImpl<Underline> underlineAttribute = new AttributeImpl<>(this, "Underline");

    private final AttributeImpl<Blinking> blinkingAttribute = new AttributeImpl<>(this, "Blinking");

    private final AttributeImpl<Boolean> reverseVideoAttribute = new AttributeImpl<>(this, "ReverseVideo");

    private final AttributeImpl<Boolean> visibilityAttribute = new AttributeImpl<>(this, "Visibility");

    private final AttributeImpl<Boolean> strikethroughAttribute = new AttributeImpl<>(this, "Strikethrough");

    private final AttributeImpl<String> fontAttribute;

    private final AttributeImpl<Integer> fgColorAttribute;

    private final AttributeImpl<Integer> bgColorAttribute;

    private final TextAttributeGroupConfig config;

    private final List<Attribute<?>> attributes;

    public TextAttributeGroupImpl(TextAttributeGroupConfig config) {
        this.config = config;
        this.fontAttribute = new AttributeImpl<>(this, "Font");
        this.fgColorAttribute = new AttributeImpl<>(this, "FgColor");
        this.bgColorAttribute = new AttributeImpl<>(this, "BgColor");
        this.attributes = List.of(
                intensityAttribute,
                italicAttribute,
                underlineAttribute,
                blinkingAttribute,
                reverseVideoAttribute,
                visibilityAttribute,
                strikethroughAttribute,
                fontAttribute,
                fgColorAttribute,
                bgColorAttribute);
        reset();
    }

    @Override
    public Key getKey() {
        return KEY;
    }

    @Override
    public TextAttributeGroupConfig getConfig() {
        return config;
    }

    @Override
    public Attribute<Intensity> getIntensityAttribute() {
        return intensityAttribute;
    }

    @Override
    public Attribute<Boolean> getItalicAttribute() {
        return italicAttribute;
    }

    @Override
    public Attribute<Underline> getUnderlineAttribute() {
        return underlineAttribute;
    }

    @Override
    public Attribute<Blinking> getBlinkingAttribute() {
        return blinkingAttribute;
    }

    @Override
    public Attribute<Boolean> getReverseVideoAttribute() {
        return reverseVideoAttribute;
    }

    @Override
    public Attribute<Boolean> getVisibilityAttribute() {
        return visibilityAttribute;
    }

    @Override
    public Attribute<Boolean> getStrikethroughAttribute() {
        return strikethroughAttribute;
    }

    @Override
    public Attribute<String> getFontAttribute() {
        return fontAttribute;
    }

    @Override
    public Attribute<Integer> getFgColorAttribute() {
        return fgColorAttribute;
    }

    @Override
    public Attribute<Integer> getBgColorAttribute() {
        return bgColorAttribute;
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        return this.attributes;
    }

    @Override
    public void reset() {
        var intensity = this.config.getDefaultIntensity();
        intensity = (intensity != null) ? intensity : Intensity.NORMAL;
        this.intensityAttribute.setBothValues(intensity);

        var italic = this.config.getDefaultItalic();
        italic = (italic != null) ? italic : false;
        this.italicAttribute.setBothValues(italic);

        var underline = this.config.getDefaultUnderline();
        underline = (underline != null) ? underline : Underline.OFF;
        this.underlineAttribute.setBothValues(underline);

        var blinking = this.config.getDefaultBlinking();
        blinking = (blinking != null) ? blinking : Blinking.OFF;
        this.blinkingAttribute.setBothValues(blinking);

        var reverseVideo = this.config.getDefaultReverseVideo();
        reverseVideo = (reverseVideo != null) ? reverseVideo : false;
        this.reverseVideoAttribute.setBothValues(reverseVideo);

        var visibility = this.config.getDefaultVisibility();
        visibility = (visibility != null) ? visibility : true;
        this.visibilityAttribute.setBothValues(visibility);

        var strikethrough = this.config.getDefaultStrikethrough();
        strikethrough = (strikethrough != null) ? strikethrough : false;
        this.strikethroughAttribute.setBothValues(strikethrough);

        this.fontAttribute.setBothValues(this.config.getFontFamilies().get(FontIndex.DEFAULT));

        var fgColor = this.config.getDefaultFgColor();
        if (fgColor != null) {
            this.fgColorAttribute.setBothValues(ColorUtils.getRgba(fgColor.getRgb(), 255));
        } else {
            this.fgColorAttribute.setBothValues(0x000000FF);
        }

        var bgColor = this.config.getDefaultBgColor();
        if (bgColor != null) {
            this.bgColorAttribute.setBothValues(ColorUtils.getRgba(bgColor.getRgb(), 255));
        } else {
            this.bgColorAttribute.setBothValues(0xFFFFFFFF);
        }
    }
}
