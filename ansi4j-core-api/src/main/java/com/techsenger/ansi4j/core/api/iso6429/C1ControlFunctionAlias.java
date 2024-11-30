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
public interface C1ControlFunctionAlias {

    /**
     * See {@link C1ControlFunction#APC}.
     */
    C1ControlFunction APPLICATION_PROGRAM_COMMAND = C1ControlFunction.APC;

    /**
     * See {@link C1ControlFunction#BPH}.
     */
    C1ControlFunction BREAK_PERMITTED_HERE = C1ControlFunction.BPH;

    /**
     * See {@link C1ControlFunction#CCH}.
     */
    C1ControlFunction CANCEL_CHARACTER = C1ControlFunction.CCH;

    /**
     * See {@link C1ControlFunction#CSI}.
     */
    C1ControlFunction CONTROL_SEQUENCE_INTRODUCER = C1ControlFunction.CSI;

    /**
     * See {@link C1ControlFunction#DCS}.
     */
    C1ControlFunction DEVICE_CONTROL_STRING = C1ControlFunction.DCS;

    /**
     * See {@link C1ControlFunction#EPA}.
     */
    C1ControlFunction END_OF_GUARDED_AREA = C1ControlFunction.EPA;

    /**
     * See {@link C1ControlFunction#ESA}.
     */
    C1ControlFunction END_OF_SELECTED_AREA = C1ControlFunction.ESA;

    /**
     * See {@link C1ControlFunction#HTJ}.
     */
    C1ControlFunction CHARACTER_TABULATION_WITH_JUSTIFICATION = C1ControlFunction.HTJ;

    /**
     * See {@link C1ControlFunction#HTS}.
     */
    C1ControlFunction CHARACTER_TABULATION_SET = C1ControlFunction.HTS;

    /**
     * See {@link C1ControlFunction#MW}.
     */
    C1ControlFunction MESSAGE_WAITING = C1ControlFunction.MW;

    /**
     * See {@link C1ControlFunction#NBH}.
     */
    C1ControlFunction NO_BREAK_HERE = C1ControlFunction.NBH;

    /**
     * See {@link C1ControlFunction#NEL}.
     */
    C1ControlFunction NEXT_LINE = C1ControlFunction.NEL;

    /**
     * See {@link C1ControlFunction#OSC}.
     */
    C1ControlFunction OPERATING_SYSTEM_COMMAND = C1ControlFunction.OSC;

    /**
     * See {@link C1ControlFunction#PLD}.
     */
    C1ControlFunction PARTIAL_LINE_FORWARD = C1ControlFunction.PLD;

    /**
     * See {@link C1ControlFunction#PLU}.
     */
    C1ControlFunction PARTIAL_LINE_BACKWARD = C1ControlFunction.PLU;

    /**
     * See {@link C1ControlFunction#PM}.
     */
    C1ControlFunction PRIVACY_MESSAGE = C1ControlFunction.PM;

    /**
     * See {@link C1ControlFunction#PU1}.
     */
    C1ControlFunction PRIVATE_USE_ONE = C1ControlFunction.PU1;

    /**
     * See {@link C1ControlFunction#PU2}.
     */
    C1ControlFunction PRIVATE_USE_TWO = C1ControlFunction.PU2;

    /**
     * See {@link C1ControlFunction#RI}.
     */
    C1ControlFunction REVERSE_LINE_FEED = C1ControlFunction.RI;

    /**
     * See {@link C1ControlFunction#SCI}.
     */
    C1ControlFunction SINGLE_CHARACTER_INTRODUCER = C1ControlFunction.SCI;

    /**
     * See {@link C1ControlFunction#SOS}.
     */
    C1ControlFunction START_OF_STRING = C1ControlFunction.SOS;

    /**
     * See {@link C1ControlFunction#SPA}.
     */
    C1ControlFunction START_OF_GUARDED_AREA = C1ControlFunction.SPA;

    /**
     * See {@link C1ControlFunction#SSA}.
     */
    C1ControlFunction START_OF_SELECTED_AREA = C1ControlFunction.SSA;

    /**
     * See {@link C1ControlFunction#SS2}.
     */
    C1ControlFunction SINGLE_SHIFT_TWO = C1ControlFunction.SS2;

    /**
     * See {@link C1ControlFunction#SS3}.
     */
    C1ControlFunction SINGLE_SHIFT_THREE = C1ControlFunction.SS3;

    /**
     * See {@link C1ControlFunction#ST}.
     */
    C1ControlFunction STRING_TERMINATOR = C1ControlFunction.ST;

    /**
     * See {@link C1ControlFunction#STS}.
     */
    C1ControlFunction SET_TRANSMIT_STATE = C1ControlFunction.STS;

    /**
     * See {@link C1ControlFunction#VTS}.
     */
    C1ControlFunction LINE_TABULATION_SET = C1ControlFunction.VTS;
}
