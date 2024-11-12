/*
 * Copyright 2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
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
