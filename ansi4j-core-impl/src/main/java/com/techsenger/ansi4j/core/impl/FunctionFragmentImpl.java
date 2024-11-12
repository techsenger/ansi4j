/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.impl;

import java.util.Collections;
import java.util.List;
import com.techsenger.ansi4j.core.api.FragmentType;
import com.techsenger.ansi4j.core.api.FunctionFragment;
import com.techsenger.ansi4j.core.api.function.Function;
import com.techsenger.ansi4j.core.api.function.FunctionArgument;

/**
 *
 * @author Pavel Castornii
 */
public class FunctionFragmentImpl extends AbstractFragment implements FunctionFragment {

    private final Function function;

    private final List<FunctionArgument> arguments;

    /**
     *
     * @param text
     * @param function
     * @param arguments modifiable collection.
     */
    public FunctionFragmentImpl(String text, int currentIndex, Function function, List<FunctionArgument> arguments) {
        super(FragmentType.FUNCTION, text, currentIndex);
        this.function = function;
        if (arguments != null) {
            this.arguments = Collections.unmodifiableList(arguments);
        } else {
            this.arguments = null;
        }
    }

    @Override
    public Function getFunction() {
        return this.function;
    }

    @Override
    public List<FunctionArgument> getArguments() {
        return this.arguments;
    }

    @Override
    public String toString() {
        return "FunctionFragment{" + "function=" + function + ", arguments=" + arguments + '}'
                + "->" + super.toString();
    }
}
