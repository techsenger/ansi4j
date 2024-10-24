/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import com.techsenger.ansi4j.css.api.Attribute;
import com.techsenger.ansi4j.css.api.AttributeChange;
import com.techsenger.ansi4j.css.api.AttributeConfig;
import com.techsenger.ansi4j.css.api.AttributeContext;
import com.techsenger.ansi4j.css.api.AttributeValue;
import com.techsenger.ansi4j.css.impl.AttributeChangeImpl;

/**
 *
 * @author Pavel Castornii
 */
public class DefaultAttributeContext implements AttributeContext {

    private final Map<Attribute, AttributeValue> nonDefaultValuesByAttribute = new HashMap<>();

    private final Map<Class<? extends Attribute>, AttributeConfig> attributeConfigByClass = new HashMap<>();

    public DefaultAttributeContext(List<AttributeConfig> configs) {
        if (configs == null || configs.size() == 0) {
            throw new IllegalArgumentException("At least one config must be provided");
        }
        for (var config : configs) {
            attributeConfigByClass.put(config.getTargetAttribute(), config);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Attribute, AttributeValue> getNonDefaultValuesByAttribute() {
        return this.nonDefaultValuesByAttribute;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<AttributeChange> setAttribute(Attribute attribute, AttributeValue newValue) {
        if (attribute == null) {
            throw new IllegalArgumentException("Attribute can't be null");
        }
        var defaultValue = this.provideDefaultValue(attribute);
        var oldValue = this.nonDefaultValuesByAttribute.get(attribute);
        if (oldValue == null) {
            oldValue = defaultValue;
        }
        if (newValue == null) {
            newValue = defaultValue;
        }
        if (Objects.equals(newValue, oldValue)) {
            //then do nothing
            return Optional.empty();
        } else {
            if (Objects.equals(newValue, defaultValue)) {
                this.nonDefaultValuesByAttribute.remove(attribute);
            } else {
                this.nonDefaultValuesByAttribute.put(attribute, newValue);
            }
            return Optional.of(new AttributeChangeImpl(attribute, oldValue, newValue));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AttributeValue getAttribute(Attribute attribute) {
        var value = this.nonDefaultValuesByAttribute.get(attribute);
        if (value == null) {
            value = this.provideDefaultValue(attribute);
        }
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AttributeConfig getAttributeConfig(Class<? extends Attribute> attributeClass) {
        return this.attributeConfigByClass.get(attributeClass);
    }

    private AttributeValue provideDefaultValue(Attribute attribute) {
        var config = this.attributeConfigByClass.get(attribute.getClass());
        if (config == null) {
            throw new NullPointerException("No config for attribute=" + attribute);
        }
        var value = config.getDefaultValue(attribute);
        return value;
    }

}
