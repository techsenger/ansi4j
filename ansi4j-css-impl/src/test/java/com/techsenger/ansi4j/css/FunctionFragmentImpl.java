/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css;

import java.util.List;
import com.techsenger.ansi4j.core.api.FragmentType;
import com.techsenger.ansi4j.core.api.FunctionFragment;
import com.techsenger.ansi4j.core.api.function.Function;
import com.techsenger.ansi4j.core.api.function.FunctionArgument;
import com.techsenger.ansi4j.core.api.iso6429.ControlSequenceFunction;

/**
 *
 * @author Pavel Castornii
 */
public class FunctionFragmentImpl implements FunctionFragment {

    private List<FunctionArgument> arguments;

    public FunctionFragmentImpl(List<FunctionArgument> arguments) {
        this.arguments = arguments;
    }

    @Override
    public Function getFunction() {
        return ControlSequenceFunction.SGR_SELECT_GRAPHIC_RENDITION;
    }

    @Override
    public List<FunctionArgument> getArguments() {
        return this.arguments;
    }

    @Override
    public FragmentType getType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getText() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getStartIndex() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getEndIndex() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
