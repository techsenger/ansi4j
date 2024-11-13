/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.impl.attribute;

import com.techsenger.ansi4j.css.api.attribute.AttributeChange;
import com.techsenger.ansi4j.css.api.attribute.AttributeGroup;
import com.techsenger.ansi4j.css.api.attribute.AttributeGroupConfig;
import com.techsenger.ansi4j.css.api.attribute.AttributeRegistry;
import com.techsenger.ansi4j.css.api.text.TextAttributeGroup;
import com.techsenger.ansi4j.css.api.text.TextAttributeGroupConfig;
import com.techsenger.ansi4j.css.impl.text.TextAttributeGroupImpl;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author Pavel Castornii
 */
public class AttributeRegistryImpl implements AttributeRegistry {

    private final Map<AttributeGroup.Key<?>, AttributeGroup> groupsByKey = new HashMap<>();

    public AttributeRegistryImpl(List<AttributeGroupConfig<?>> configs) {
        var configsByKey = configs.stream()
                .collect(Collectors.toMap(AttributeGroupConfig::getGroupKey, Function.identity()));

        var textGroup = new TextAttributeGroupImpl((TextAttributeGroupConfig) configsByKey.get(TextAttributeGroup.KEY));
        groupsByKey.put(textGroup.getKey(), textGroup);
    }

    @Override
    public <T extends AttributeGroup<T>> T getGroup(AttributeGroup.Key<T> key) {
        return (T) this.groupsByKey.get(key);
    }

    @Override
    public Set<AttributeGroup.Key<?>> getGroupKeys() {
        return Collections.unmodifiableSet(this.groupsByKey.keySet());
    }

    /**
     * Updates attribute values.
     *
     * @param changes
     */
    public void applyChanges(List<AttributeChange<?>> changes) {
        changes.forEach(c -> {
            var attribute = (AttributeImpl) c.getAttribute();
            attribute.setValue(c.getNewValue());
        });
    }
}
