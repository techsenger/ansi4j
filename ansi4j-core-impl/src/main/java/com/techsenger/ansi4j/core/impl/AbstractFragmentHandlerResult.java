/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.impl;

import com.techsenger.ansi4j.core.api.FailureReason;
import com.techsenger.ansi4j.core.api.FragmentHandlerResult;

/**
 *
 * @author Pavel Castornii
 */
abstract class AbstractFragmentHandlerResult implements FragmentHandlerResult {

    private final FailureReason failureReason;

    AbstractFragmentHandlerResult(FailureReason failureReason) {
        this.failureReason = failureReason;
    }

    @Override
    public FailureReason getFailureReason() {
        return this.failureReason;
    }

}
