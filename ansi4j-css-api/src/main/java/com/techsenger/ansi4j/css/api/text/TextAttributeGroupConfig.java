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

import com.techsenger.ansi4j.css.api.attribute.AttributeGroup;
import com.techsenger.ansi4j.css.api.attribute.AttributeGroupConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.techsenger.ansi4j.css.api.color.Color;
import com.techsenger.ansi4j.css.api.color.Palette16;
import com.techsenger.ansi4j.css.api.color.Palette256;
import com.techsenger.ansi4j.css.api.color.Palette8;

/**
 *
 * @author Pavel Castornii
 */
public final class TextAttributeGroupConfig implements AttributeGroupConfig<TextAttributeGroup> {

    public static class Builder {

        private Color defaultFgColor;

        private Color defaultBgColor;

        private Boolean defaultItalic;

        private Boolean defaultStrikethrough;

        private TextAttributeGroup.Underline defaultUnderline;

        private TextAttributeGroup.Weight defaultWeight;

        private Boolean defaultVisibility;

        private Boolean defaultReverseVideo;

        private TextAttributeGroup.Blinking defaultBlinking;

        private List<String> fontFamilies;

        private boolean extraColorsEnabled = false;

        private Palette8 palette8;

        private Palette16 palette16;

        private Palette256 palette256;

        public Builder() {

        }

        public Builder defaultFgColor(Color defaultFgColor) {
            this.defaultFgColor = defaultFgColor;
            return this;
        }

        public Builder defaultBgColor(Color defaultBgColor) {
            this.defaultBgColor = defaultBgColor;
            return this;
        }

        public Builder defaultItalic(Boolean defaultItalic) {
            this.defaultItalic = defaultItalic;
            return this;
        }

        public Builder defaultStrikethrough(Boolean defaultStrikethrough) {
            this.defaultStrikethrough = defaultStrikethrough;
            return this;
        }

        public Builder defaultUnderline(TextAttributeGroup.Underline defaultUnderline) {
            this.defaultUnderline = defaultUnderline;
            return this;
        }

        public Builder defaultWeight(TextAttributeGroup.Weight defaultWeight) {
            this.defaultWeight = defaultWeight;
            return this;
        }

        public Builder defaultVisibility(Boolean defaultVisibility) {
            this.defaultVisibility = defaultVisibility;
            return this;
        }

        public Builder defaultReverseVideo(Boolean defaultReverseVideo) {
            this.defaultReverseVideo = defaultReverseVideo;
            return this;
        }

        public Builder defaultBlinking(TextAttributeGroup.Blinking defaultBlinking) {
            this.defaultBlinking = defaultBlinking;
            return this;
        }

        /**
         * See {@link TextAttributeGroupConfig#getFontFamilies() }.
         *
         * @param fontFamilies
         * @return
         */
        public Builder fontFamilies(List<String> fontFamilies) {
            this.fontFamilies = fontFamilies;
            return this;
        }

        /**
         * See {@link TextAttributeGroupConfig#areExtraColorsEnabled() }.
         *
         * @param enabled
         * @return
         */
        public Builder extraColorsEnabled(boolean enabled) {
            this.extraColorsEnabled = enabled;
            return this;
        }

        /**
         * See {@link TextAttributeGroupConfig#getPalette8() }.
         * @param palette8
         * @return
         */
        public Builder palette8(Palette8 palette8) {
            this.palette8 = palette8;
            return this;
        }

        /**
         * See {@link TextAttributeGroupConfig#getPalette16() }.
         * @param palette16
         * @return
         */
        public Builder palette16(Palette16 palette16) {
            this.palette16 = palette16;
            return this;
        }

        /**
         * See {@link TextAttributeGroupConfig#getPalette256() }.
         * @param palette256
         * @return
         */
        public Builder palette256(Palette256 palette256) {
            this.palette256 = palette256;
            return this;
        }

        /**
         * Builds configuration.
         * @return
         */
        public TextAttributeGroupConfig build() {
            this.resolvePaletteColors();
            this.validate();
            var config = new TextAttributeGroupConfig(this);
            return config;
        }

        private void resolvePaletteColors() {
            if (defaultFgColor != null && defaultFgColor.getPaletteType() != null) {
                var value = this.resolveColorValue(defaultFgColor);
                this.defaultFgColor = new Color(defaultFgColor.getPaletteType(), defaultFgColor.getIndex(), value);
            }
            if (defaultBgColor != null && defaultBgColor.getPaletteType() != null) {
                var value = this.resolveColorValue(defaultBgColor);
                this.defaultBgColor = new Color(defaultBgColor.getPaletteType(), defaultBgColor.getIndex(), value);
            }
        }

        private int resolveColorValue(Color color) {
            switch (color.getPaletteType()) {
                case PALETTE_8: return this.palette8.getColors()[color.getIndex()];
                case PALETTE_16: return this.palette16.getColors()[color.getIndex()];
                case PALETTE_256: return this.palette256.getColors()[color.getIndex()];
                default:
                    throw new IllegalArgumentException("Unknown palette type: " + color.getPaletteType());
            }
        }

        private void validate() {
            if (this.fontFamilies == null || this.fontFamilies.isEmpty()) {
                throw new IllegalStateException("No font families, provide at least 1");
            }
            if (this.extraColorsEnabled) {
                if (this.palette16 == null && this.palette256 == null) {
                    throw new IllegalStateException("No palette16 and palette 256, but extra colors are enabled");
                }
            } else {
                if (this.palette8 == null) {
                    throw new IllegalStateException("No palette8, but extra colors are not enabled");
                }
            }
        }
    }

    private final Color defaultFgColor;

    private final Color defaultBgColor;

    private final Boolean defaultItalic;

    private final Boolean defaultStrikethrough;

    private final TextAttributeGroup.Underline defaultUnderline;

    private final TextAttributeGroup.Weight defaultWeight;

    private final Boolean defaultVisibility;

    private final Boolean defaultReverseVideo;

    private final TextAttributeGroup.Blinking defaultBlinking;

    private final List<String> fontFamilies;

    private final boolean extraColorsEnabled;

    private final Palette8 palette8;

    private final Palette16 palette16;

    private final Palette256 palette256;

    @Override
    public AttributeGroup.Key<TextAttributeGroup> getGroupKey() {
        return TextAttributeGroup.KEY;
    }

    public Color getDefaultFgColor() {
        return defaultFgColor;
    }

    public Color getDefaultBgColor() {
        return defaultBgColor;
    }

    public Boolean getDefaultItalic() {
        return defaultItalic;
    }

    public Boolean getDefaultStrikethrough() {
        return defaultStrikethrough;
    }

    public TextAttributeGroup.Underline getDefaultUnderline() {
        return defaultUnderline;
    }

    public TextAttributeGroup.Weight getDefaultWeight() {
        return defaultWeight;
    }

    public Boolean getDefaultVisibility() {
        return defaultVisibility;
    }

    public Boolean getDefaultReverseVideo() {
        return defaultReverseVideo;
    }

    public TextAttributeGroup.Blinking getDefaultBlinking() {
        return defaultBlinking;
    }

    public boolean isExtraColorsEnabled() {
        return extraColorsEnabled;
    }

    /**
     * Default font has index 0, alternative_1 index 1, etc.
     *
     * @return
     */
    public List<String> getFontFamilies() {
        return this.fontFamilies;
    }

    /**
     * ISO 6429 supports only 8 colors (3 bits). However, today many terminals supports 4, 8 and 24 bit colors.
     * This flag is used to enable support of these extra colors.
     *
     * @return
     */
    public boolean areExtraColorsEnabled() {
        return this.extraColorsEnabled;
    }

    /**
     * Returns 16 color palette that is used if extra colors ARE enabled.
     *
     * @return
     */
    public Palette16 getPalette16() {
        return this.palette16;
    }

    /**
     * Returns 256 palette that is used if extra colors ARE enabled.
     *
     * @return
     */
    public Palette256 getPalette256() {
        return this.palette256;
    }

    /**
     * Returns palette that is used if extra colors ARE NOT enabled.
     *
     * @return
     */
    public Palette8 getPalette8() {
        return this.palette8;
    }

    private TextAttributeGroupConfig(Builder builder) {
        this.defaultFgColor = builder.defaultFgColor;
        this.defaultBgColor = builder.defaultBgColor;
        this.defaultBlinking = builder.defaultBlinking;
        this.defaultItalic = builder.defaultItalic;
        this.defaultReverseVideo = builder.defaultReverseVideo;
        this.defaultStrikethrough = builder.defaultStrikethrough;
        this.defaultUnderline = builder.defaultUnderline;
        this.defaultVisibility = builder.defaultVisibility;
        this.defaultWeight = builder.defaultWeight;

        this.fontFamilies = Collections.unmodifiableList(new ArrayList<>(builder.fontFamilies));
        this.extraColorsEnabled = builder.extraColorsEnabled;
        this.palette8 = builder.palette8;
        this.palette16 = builder.palette16;
        this.palette256 = builder.palette256;
    }
}
