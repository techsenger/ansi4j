/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.impl;

import java.util.Optional;
import com.techsenger.ansi4j.core.api.FunctionFailureReason;
import com.techsenger.ansi4j.core.api.TextFragment;
import com.techsenger.ansi4j.core.api.TextHandlerResult;

/**
 *
 * @author Pavel Castornii
 */
public class TextHandlerResultImpl extends AbstractFragmentHandlerResult implements TextHandlerResult {

    private final Optional<TextFragment> fragment;

    public TextHandlerResultImpl(Optional<TextFragment> fragment, FunctionFailureReason failureReason) {
        super(failureReason);
        this.fragment = fragment;
    }

    @Override
    public Optional<TextFragment> getFragment() {
        return this.fragment;
    }
}
