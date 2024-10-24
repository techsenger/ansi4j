/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.techsenger.ansi4j.css.api.Attribute;
import com.techsenger.ansi4j.css.api.AttributeChange;
import com.techsenger.ansi4j.css.api.AttributeContext;
import com.techsenger.ansi4j.css.api.AttributeCssGenerator;
import com.techsenger.ansi4j.css.api.text.TextAttribute;

/**
 *
 * @author Pavel Castornii
 */
public abstract class AbstractCssGenerator implements AttributeCssGenerator {


    @FunctionalInterface
    protected interface DeclarationGenerator {

        /**
         * Generates CSS declarations and add them to declaration list.
         *
         * @param change
         */
        List<String> generate(AttributeChange change, AttributeContext context);
    }

    private Map<Attribute, DeclarationGenerator> generatorsByAttribuite = new HashMap<>();

    protected Map<Attribute, DeclarationGenerator> getGeneratorsByAttribuiteKey() {
        return generatorsByAttribuite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> generate(AttributeChange change, AttributeContext context) {
        var generator = generatorsByAttribuite.get(change.getAttribute());
        var declarations =  generator.generate(change, context);
        return declarations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Attribute> getTargetAttributeClass() {
        return TextAttribute.class;
    }

    /**
     * Returns foreground color in accordance with REVERSE_VIDEO.
     * @param context
     * @return
     */
    protected int getResolvedForegroundColor(AttributeContext context) {
        if (context.getNonDefaultValuesByAttribute().containsKey(TextAttribute.REVERSE_VIDEO)) {
            var bgColor = (int) context.getAttribute(TextAttribute.BACKGROUND_COLOR).getValue();
            return bgColor;
        } else {
            var fgColor = (int) context.getAttribute(TextAttribute.FOREGROUND_COLOR).getValue();
            return fgColor;
        }
    }

    /**
     * Returns background color in accordance with REVERSE_VIDEO.
     * @param context
     * @return
     */
    protected int getResolvedBackgroundColor(AttributeContext context) {
        if (context.getNonDefaultValuesByAttribute().containsKey(TextAttribute.REVERSE_VIDEO)) {
            var fgColor = (int) context.getAttribute(TextAttribute.FOREGROUND_COLOR).getValue();
            return fgColor;
        } else {
            var bgColor = (int) context.getAttribute(TextAttribute.BACKGROUND_COLOR).getValue();
            return bgColor;
        }
    }

    protected String toHexColor(int i) {
        return String.format("%06X", i);
    }
}
