/*
 * Copyright 2024 Pavel Castornii.
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
