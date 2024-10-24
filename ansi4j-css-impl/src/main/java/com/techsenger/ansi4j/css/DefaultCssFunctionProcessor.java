/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.techsenger.ansi4j.core.api.FunctionFragment;
import com.techsenger.ansi4j.core.api.function.Function;
import com.techsenger.ansi4j.css.api.Attribute;
import com.techsenger.ansi4j.css.api.AttributeChange;
import com.techsenger.ansi4j.css.api.AttributeContext;
import com.techsenger.ansi4j.css.api.AttributeCssGenerator;
import com.techsenger.ansi4j.css.api.AttributeResolver;
import com.techsenger.ansi4j.css.api.CssFunctionProcessor;

/**
 *
 * @author Pavel Castornii
 */
public final class DefaultCssFunctionProcessor implements CssFunctionProcessor {

    public static class Builder {

        private List<AttributeResolver> resolvers;

        private List<AttributeCssGenerator> generators;

        public Builder resolvers(AttributeResolver... resolvers) {
            this.resolvers = Arrays.asList(resolvers);
            return this;
        }

        public Builder generators(AttributeCssGenerator... generators) {
            this.generators = Arrays.asList(generators);
            return this;
        }

        public CssFunctionProcessor build() {
            this.validate();
            var processor = new DefaultCssFunctionProcessor(this);
            return processor;
        }

        private void validate() {
            if (this.resolvers == null || resolvers.isEmpty()) {
                throw new IllegalStateException("No resolvers provided");
            }
            if (this.generators == null || generators.isEmpty()) {
                throw new IllegalStateException("No generators provided");
            }
        }
    }

    private final Map<Function, AttributeResolver> resolversByFunction = new HashMap<>();

    private final Map<Class<? extends Attribute>, AttributeCssGenerator> generatorsByAttribute = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Function, AttributeResolver> getAttributeResolversByFunction() {
        return Collections.unmodifiableMap(this.resolversByFunction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Class<? extends Attribute>, AttributeCssGenerator> getCssGeneratorsByAttributeClass() {
        return Collections.unmodifiableMap(this.generatorsByAttribute);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> process(FunctionFragment functionFragment, AttributeContext context) {
        List<String> declarations = new ArrayList<>();
        var function = functionFragment.getFunction();
        var resolver = this.resolversByFunction.get(function);
        if (resolver != null) {
            List<AttributeChange> changes = resolver.resolve(functionFragment, context);
            for (var change : changes) {
                var generator = this.generatorsByAttribute.get(change.getAttribute().getClass());
                if (generator != null) {
                    var decl = generator.generate(change, context);
                    declarations.addAll(decl);
                } else {
                    throw new NullPointerException("No generator for attribute=" + change.getAttribute().getClass());
                }
            }
        }
        return declarations;
    }

    private DefaultCssFunctionProcessor(Builder builder) {
        for (var resolver : builder.resolvers) {
            this.resolversByFunction.put(resolver.getTargetFunction(), resolver);
        }
        for (var generator : builder.generators) {
            this.generatorsByAttribute.put(generator.getTargetAttributeClass(), generator);
        }
    }
}
