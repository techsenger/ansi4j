/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.impl;

import java.util.Collections;
import java.util.List;
import com.techsenger.ansi4j.core.api.function.Function;

/**
 *
 * @author Pavel Castornii
 */
public class FunctionDescriptor {

    private final Function function;

    private final List<String> parameters;

    private final List<String> codes;

    /**
     *
     * @param function
     * @param parameters modifiable collection of parameters.
     * @param codes modifiable collection codes.
     */
    public FunctionDescriptor(Function function, List<String> parameters, List<String> codes) {
        this.function = function;
        if (parameters != null) {
            this.parameters = Collections.unmodifiableList(parameters);
        } else {
            this.parameters = null;
        }
        this.codes = Collections.unmodifiableList(codes);
    }

    public Function getFunction() {
        return this.function;
    }

    public List<String> getParameters() {
        return this.parameters;
    }

    public List<String> getCodes() {
        return this.codes;
    }

}
