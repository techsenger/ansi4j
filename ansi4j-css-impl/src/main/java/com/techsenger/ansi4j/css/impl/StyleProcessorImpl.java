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

package com.techsenger.ansi4j.css.impl;

import com.techsenger.ansi4j.css.impl.attribute.AttributeRegistryImpl;
import java.util.HashMap;
import java.util.Map;
import com.techsenger.ansi4j.core.api.FunctionFragment;
import com.techsenger.ansi4j.core.api.function.Function;
import com.techsenger.ansi4j.css.api.attribute.AttributeGroup;
import com.techsenger.ansi4j.css.api.spi.StyleProcessorConfig;
import com.techsenger.ansi4j.css.impl.text.SgrFunctionEvaluator;
import com.techsenger.ansi4j.css.api.StyleProcessor;
import com.techsenger.ansi4j.css.api.attribute.AttributeRegistry;
import com.techsenger.ansi4j.css.api.GroupStyleGenerator;
import com.techsenger.ansi4j.css.api.ProcessorResult;
import com.techsenger.ansi4j.css.api.TargetControl;

/**
 *
 * @author Pavel Castornii
 */
public final class StyleProcessorImpl implements StyleProcessor {

    private final Map<Function, FunctionEvaluator> evaluatorsByFunction = new HashMap<>();

    private final Map<TargetControl, Map<AttributeGroup.Key<?>, GroupStyleGenerator<?>>>
            generatorsByControlAndGroupKey = new HashMap<>();

    private final AttributeRegistryImpl attributeRegistry;

    public StyleProcessorImpl(StyleProcessorConfig config) {
        this.attributeRegistry = new AttributeRegistryImpl(config.getConfigs());

        var sgrEvaluator = new SgrFunctionEvaluator(this.attributeRegistry);
        this.evaluatorsByFunction.put(sgrEvaluator.getFunction(), sgrEvaluator);

        config.getGenerators().forEach(g -> {
            var group = this.attributeRegistry.getGroup(g.getGroupKey());
            ((GroupStyleGenerator) g).initialize(group);

            var generatorsByGroupAndKey = this.generatorsByControlAndGroupKey.get(g.getTargetControl());
            if (generatorsByGroupAndKey == null) {
                generatorsByGroupAndKey = new HashMap<>();
                this.generatorsByControlAndGroupKey.put(g.getTargetControl(), generatorsByGroupAndKey);
            }
            generatorsByGroupAndKey.put(g.getGroupKey(), g);
        });
    }

    @Override
    public <T extends AttributeGroup<T>> GroupStyleGenerator<T> getGenerator(TargetControl targetControl,
            AttributeGroup.Key<T> key) {
        var generatorsByGroup = this.generatorsByControlAndGroupKey.get(targetControl);
        if (generatorsByGroup == null) {
            return null;
        }
        return (GroupStyleGenerator<T>) generatorsByGroup.get(key);
    }

    @Override
    public AttributeRegistry getAttributeRegistry() {
        return this.attributeRegistry;
    }

    @Override
    public ProcessorResult process(FunctionFragment functionFragment, TargetControl targetControl) {
        var result = new ProcessorResultImpl();
        var function = functionFragment.getFunction();
        var evaluator = this.evaluatorsByFunction.get(function);
        if (evaluator != null) {
            evaluator.evaluate(functionFragment, result);
            this.attributeRegistry.applyChanges(result.getAttributeChanges());
            var generatorsByGroup = this.generatorsByControlAndGroupKey.get(targetControl);
            if (generatorsByGroup != null) {
                for (var generator : generatorsByGroup.values()) {
                    var declarations = generator.generate();
                    result.getStyleDeclarations().addAll(declarations);
                }
            }
        }
        result.makeListsUnmodifiable();
        return result;
    }
}
