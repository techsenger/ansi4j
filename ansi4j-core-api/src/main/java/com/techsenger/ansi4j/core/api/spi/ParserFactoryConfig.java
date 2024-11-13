/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
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
