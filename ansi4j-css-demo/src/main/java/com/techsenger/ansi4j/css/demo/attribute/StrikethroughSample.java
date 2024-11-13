/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.demo.attribute;

import com.techsenger.ansi4j.css.api.attribute.Attribute;
import com.techsenger.ansi4j.css.api.text.TextAttributeGroup;

/**
 *
 * @author Pavel Castornii
 */
class StrikethroughSample extends AbstractAttributeSample<Boolean> {

    StrikethroughSample(Boolean defaultValue, Boolean currentValue, boolean supportedInWebView,
            boolean supportedInTextFlow, boolean supportedInRtfxTextArea) {
        super(defaultValue, currentValue, supportedInWebView, supportedInTextFlow, supportedInRtfxTextArea);
    }

    @Override
    public Attribute<Boolean> resolveAttribute(TextAttributeGroup group) {
        return group.getStrikethroughAttribute();
    }
}
