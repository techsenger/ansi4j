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

package com.techsenger.ansi4j.css.api;

import com.techsenger.ansi4j.css.api.attribute.AttributeGroup;
import com.techsenger.ansi4j.css.api.attribute.AttributeGroupConfig;
import com.techsenger.ansi4j.css.api.attribute.AttributeRegistry;
import com.techsenger.ansi4j.core.api.FunctionFragment;
import com.techsenger.ansi4j.css.api.spi.StyleProcessorConfig;
import java.util.Arrays;
import java.util.ServiceLoader;
import com.techsenger.ansi4j.css.api.spi.StyleProcessorService;
import javax.annotation.concurrent.NotThreadSafe;

/**
 *
 * @author Pavel Castornii
 */
@NotThreadSafe
public interface StyleProcessor {

    class Builder {

        private final StyleProcessorConfig config = new StyleProcessorConfig();

        /**
         * Sets the {@link AttributeGroupConfig}.
         *
         * @param groupConfigs
         * @return
         */
        public Builder configs(AttributeGroupConfig... groupConfigs) {
            this.config.setConfigs(Arrays.asList(groupConfigs));
            return this;
        }

        /**
         * Sets the {@link GroupStyleGenerator}.
         *
         * @param generators
         * @return
         */
        public Builder generators(GroupStyleGenerator... generators) {
            this.config.setGenerators(Arrays.asList(generators));
            return this;
        }

        public StyleProcessor build() {
            this.config.validate();
            var processor = ServiceLoader
                    .load(StyleProcessorService.class).findFirst().orElseThrow().createProcessor(config);
            return processor;
        }
    }

    /**
     * Returns an attribute registry that this processes uses.
     *
     * @return
     */
    AttributeRegistry getAttributeRegistry();

    /**
     * Returns a generator for for the specified control and group.
     * @return
     */
    <T extends AttributeGroup<T>> GroupStyleGenerator<T> getGenerator(TargetControl targetControl,
            AttributeGroup.Key<T> key);

    /**
     * Processes a function fragment using attribute registry and generators.
     *
     * @param functionFragment
     * @return declarations or empty list.
     */
    ProcessorResult process(FunctionFragment functionFragment, TargetControl targetControl);
}
