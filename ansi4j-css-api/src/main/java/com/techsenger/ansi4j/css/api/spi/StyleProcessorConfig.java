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

package com.techsenger.ansi4j.css.api.spi;

import com.techsenger.ansi4j.css.api.attribute.AttributeGroupConfig;
import java.util.List;
import com.techsenger.ansi4j.css.api.GroupStyleGenerator;

/**
 *
 * @author Pavel Castornii
 */
public class StyleProcessorConfig {

    private List<AttributeGroupConfig<?>> configs;

    private List<GroupStyleGenerator<?>> generators;

    public List<AttributeGroupConfig<?>> getConfigs() {
        return configs;
    }

    public void setConfigs(List<AttributeGroupConfig<?>> groupConfigs) {
        this.configs = groupConfigs;
    }

    public List<GroupStyleGenerator<?>> getGenerators() {
        return generators;
    }

    public void setGenerators(List<GroupStyleGenerator<?>> generators) {
        this.generators = generators;
    }

    public void validate() {
        if (this.configs == null || this.configs.isEmpty()) {
            throw new IllegalStateException("No attribute group configs provided");
        }
        if (this.generators == null || this.generators.isEmpty()) {
            throw new IllegalStateException("No style generators provided");
        }
    }
}
