/*
 * Copyright 2022-2024 Pavel Castornii.
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

package com.techsenger.ansi4j.core.impl.iso6429;

import com.techsenger.ansi4j.core.api.Environment;
import com.techsenger.ansi4j.core.api.FunctionHandler;

/**
 *
 * @author Pavel Castornii
 */
public abstract class AbstractFunctionHandler implements FunctionHandler {

    private Environment environment;

    /**
     * Functions checks if whole function is present in text.
     * @param text
     * @param endIndex
     * @return
     */
    protected boolean isEndOfFunctionPresent(String text, int endIndex) {
        return !(endIndex > text.length());
    }

    @Override
    public void initialize(Environment environment) {
        this.environment = environment;
    }

    protected Environment getEnvironment() {
        return environment;
    }
}
