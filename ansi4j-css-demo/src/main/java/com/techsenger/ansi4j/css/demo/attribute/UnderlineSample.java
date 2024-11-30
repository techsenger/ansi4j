/*
 * Copyright 2024 Pavel Castornii.
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

package com.techsenger.ansi4j.css.demo.attribute;

import com.techsenger.ansi4j.css.api.attribute.Attribute;
import com.techsenger.ansi4j.css.api.text.TextAttributeGroup;

/**
 *
 * @author Pavel Castornii
 */
class UnderlineSample extends AbstractAttributeSample<TextAttributeGroup.Underline> {

    UnderlineSample(TextAttributeGroup.Underline defaultValue, TextAttributeGroup.Underline currentValue,
            boolean supportedInWebView, boolean supportedInTextFlow, boolean supportedInRtfxTextArea) {
        super(defaultValue, currentValue, supportedInWebView, supportedInTextFlow, supportedInRtfxTextArea);
    }

    @Override
    public Attribute<TextAttributeGroup.Underline> resolveAttribute(TextAttributeGroup group) {
        return group.getUnderlineAttribute();
    }
}
