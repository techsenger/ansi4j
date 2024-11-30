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

import com.techsenger.ansi4j.css.api.attribute.Attribute;
import com.techsenger.ansi4j.css.api.attribute.AttributeGroup;

/**
 *
 * @author Pavel Castornii
 */
public interface TextAttributeGroup extends AttributeGroup<TextAttributeGroup> {

    enum Weight {
        NORMAL, BOLD, FAINT
    }

    enum Underline {
        OFF, SINGLE, DOUBLE
    }

    enum Blinking {
        OFF, SLOW, RAPID
    }

    AttributeGroup.Key<TextAttributeGroup> KEY = new Key<>();

    TextAttributeGroupConfig getConfig();

    Attribute<String> getFontAttribute();

    Attribute<Integer> getFgColorAttribute();

    Attribute<Integer> getBgColorAttribute();

    Attribute<Boolean> getItalicAttribute();

    Attribute<Boolean> getStrikethroughAttribute();

    Attribute<Underline> getUnderlineAttribute();

    Attribute<Weight> getWeightAttribute();

    Attribute<Boolean> getVisibilityAttribute();

    Attribute<Boolean> getReverseVideoAttribute();

    Attribute<Blinking> getBlinkingAttribute();

}
