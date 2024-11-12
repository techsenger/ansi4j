/*
 * Copyright 2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
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
