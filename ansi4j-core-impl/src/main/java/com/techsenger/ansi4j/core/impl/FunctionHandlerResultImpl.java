/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.impl;

import java.util.Optional;
import com.techsenger.ansi4j.core.api.FunctionFailureReason;
import com.techsenger.ansi4j.core.api.FunctionFragment;
import com.techsenger.ansi4j.core.api.FunctionHandlerResult;

/**
 *
 * @author Pavel Castornii
 */
public class FunctionHandlerResultImpl extends AbstractFragmentHandlerResult implements FunctionHandlerResult {

    private final Optional<FunctionFragment> fragment;

    public FunctionHandlerResultImpl(Optional<FunctionFragment> fragment, FunctionFailureReason failureReason) {
        super(failureReason);
        this.fragment = fragment;
    }

    @Override
    public Optional<FunctionFragment> getFragment() {
        return this.fragment;
    }
}
