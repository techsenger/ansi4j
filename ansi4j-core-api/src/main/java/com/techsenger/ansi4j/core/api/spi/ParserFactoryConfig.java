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

package com.techsenger.ansi4j.core.api.spi;

import com.techsenger.ansi4j.core.api.Environment;
import com.techsenger.ansi4j.core.api.FunctionFinder;
import com.techsenger.ansi4j.core.api.FunctionHandler;
import com.techsenger.ansi4j.core.api.TextHandler;
import com.techsenger.ansi4j.core.api.iso6429.ControlFunctionType;
import java.util.List;

/**
 *
 * @author Pavel Castornii
 */
public class ParserFactoryConfig {

    private Environment environment;

    private List<ControlFunctionType> functionTypes;

    private FunctionFinder functionFinder;

    private List<FunctionHandler> functionHandlers;

    private TextHandler textHandler;

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public List<ControlFunctionType> getFunctionTypes() {
        return functionTypes;
    }

    public void setFunctionTypes(List<ControlFunctionType> functionTypes) {
        this.functionTypes = functionTypes;
    }

    public FunctionFinder getFunctionFinder() {
        return functionFinder;
    }

    public void setFunctionFinder(FunctionFinder functionFinder) {
        this.functionFinder = functionFinder;
    }

    public List<FunctionHandler> getFunctionHandlers() {
        return functionHandlers;
    }

    public void setFunctionHandlers(List<FunctionHandler> functionHandlers) {
        this.functionHandlers = functionHandlers;
    }

    public TextHandler getTextHandler() {
        return textHandler;
    }

    public void setTextHandler(TextHandler textHandler) {
        this.textHandler = textHandler;
    }

    public void validate() {
        if (environment == null) {
            throw new IllegalStateException("No environment");
        }

        if ((this.functionTypes == null || this.functionTypes.isEmpty())
                && (this.functionHandlers == null || this.functionHandlers.isEmpty())) {
            throw new IllegalStateException("No function types, no function handlers");
        }
    }
}
