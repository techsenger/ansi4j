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
