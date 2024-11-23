/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.api.iso6429;

/**
 *
 * @author Pavel Castornii
 */
public interface IndependentControlFunctionAlias {

    /**
     * See {@link IndependentControlFunction#CMD}.
     */
    IndependentControlFunction CODING_METHOD_DELIMITER = IndependentControlFunction.CMD;

    /**
     * See {@link IndependentControlFunction#DMI}.
     */
    IndependentControlFunction DISABLE_MANUAL_INPUT = IndependentControlFunction.DMI;

    /**
     * See {@link IndependentControlFunction#EMI}.
     */
    IndependentControlFunction ENABLE_MANUAL_INPUT = IndependentControlFunction.EMI;

    /**
     * See {@link IndependentControlFunction#INT}.
     */
    IndependentControlFunction INTERRUPT = IndependentControlFunction.INT;

    /**
     * See {@link IndependentControlFunction#LS1R}.
     */
    IndependentControlFunction LOCKING_SHIFT_ONE_RIGHT = IndependentControlFunction.LS1R;

    /**
     * See {@link IndependentControlFunction#LS2}.
     */
    IndependentControlFunction LOCKING_SHIFT_TWO = IndependentControlFunction.LS2;

    /**
     * See {@link IndependentControlFunction#LS2R}.
     */
    IndependentControlFunction LOCKING_SHIFT_TWO_RIGHT = IndependentControlFunction.LS2R;

    /**
     * See {@link IndependentControlFunction#LS3}.
     */
    IndependentControlFunction LOCKING_SHIFT_THREE = IndependentControlFunction.LS3;

    /**
     * See {@link IndependentControlFunction#LS3R}.
     */
    IndependentControlFunction LOCKING_SHIFT_THREE_RIGHT = IndependentControlFunction.LS3R;

    /**
     * See {@link IndependentControlFunction#RIS}.
     */
    IndependentControlFunction RESET_TO_INITIAL_STATE = IndependentControlFunction.RIS;
}
