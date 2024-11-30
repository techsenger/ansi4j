/*
 * Copyright 2022-2024 Pavel Castornii.
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
public interface C0ControlFunctionAlias {

    /**
     * See {@link C0ControlFunction#ACK}.
     */
    C0ControlFunction ACKNOWLEDGE = C0ControlFunction.ACK;

    /**
     * See {@link C0ControlFunction#BEL}.
     */
    C0ControlFunction BELL = C0ControlFunction.BEL;

    /**
     * See {@link C0ControlFunction#BS}.
     */
    C0ControlFunction BACKSPACE = C0ControlFunction.BS;

    /**
     * See {@link C0ControlFunction#CAN}.
     */
    C0ControlFunction CANCEL = C0ControlFunction.CAN;

    /**
     * See {@link C0ControlFunction#CR}.
     */
    C0ControlFunction CARRIAGE_RETURN = C0ControlFunction.CR;

    /**
     * See {@link C0ControlFunction#DC1}.
     */
    C0ControlFunction DEVICE_CONTROL_ONE = C0ControlFunction.DC1;

    /**
     * See {@link C0ControlFunction#DC2}.
     */
    C0ControlFunction DEVICE_CONTROL_TWO = C0ControlFunction.DC2;

    /**
     * See {@link C0ControlFunction#DC3}.
     */
    C0ControlFunction DEVICE_CONTROL_THREE = C0ControlFunction.DC3;

    /**
     * See {@link C0ControlFunction#DC4}.
     */
    C0ControlFunction DEVICE_CONTROL_FOUR = C0ControlFunction.DC4;

    /**
     * See {@link C0ControlFunction#DEL}.
     */
    C0ControlFunction DELETE = C0ControlFunction.DEL;

    /**
     * See {@link C0ControlFunction#DLE}.
     */
    C0ControlFunction DATA_LINK_ESCAPE = C0ControlFunction.DLE;

    /**
     * See {@link C0ControlFunction#EM}.
     */
    C0ControlFunction END_OF_MEDIUM = C0ControlFunction.EM;

    /**
     * See {@link C0ControlFunction#ENQ}.
     */
    C0ControlFunction ENQUIRY = C0ControlFunction.ENQ;

    /**
     * See {@link C0ControlFunction#EOT}.
     */
    C0ControlFunction END_OF_TRANSMISSION = C0ControlFunction.EOT;

    /**
     * See {@link C0ControlFunction#ESC}.
     */
    C0ControlFunction ESCAPE = C0ControlFunction.ESC;

    /**
     * See {@link C0ControlFunction#ETB}.
     */
    C0ControlFunction END_OF_TRANSMISSION_BLOCK = C0ControlFunction.ETB;

    /**
     * See {@link C0ControlFunction#ETX}.
     */
    C0ControlFunction END_OF_TEXT = C0ControlFunction.ETX;

    /**
     * See {@link C0ControlFunction#FF}.
     */
    C0ControlFunction FORM_FEED = C0ControlFunction.FF;

    /**
     * See {@link C0ControlFunction#HT}.
     */
    C0ControlFunction CHARACTER_TABULATION = C0ControlFunction.HT;

    /**
     * See {@link C0ControlFunction#IS1}.
     */
    C0ControlFunction INFORMATION_SEPARATOR_ONE = C0ControlFunction.IS1;

    /**
     * See {@link C0ControlFunction#IS2}.
     */
    C0ControlFunction INFORMATION_SEPARATOR_TWO = C0ControlFunction.IS2;

    /**
     * See {@link C0ControlFunction#IS3}.
     */
    C0ControlFunction INFORMATION_SEPARATOR_THREE = C0ControlFunction.IS3;

    /**
     * See {@link C0ControlFunction#IS4}.
     */
    C0ControlFunction INFORMATION_SEPARATOR_FOUR = C0ControlFunction.IS4;

    /**
     * See {@link C0ControlFunction#LF}.
     */
    C0ControlFunction LINE_FEED = C0ControlFunction.LF;

    /**
     * See {@link C0ControlFunction#LS0}.
     */
    C0ControlFunction LOCKING_SHIFT_ZERO = C0ControlFunction.LS0;

    /**
     * See {@link C0ControlFunction#LS1}.
     */
    C0ControlFunction LOCKING_SHIFT_ONE = C0ControlFunction.LS1;

    /**
     * See {@link C0ControlFunction#NAK}.
     */
    C0ControlFunction NEGATIVE_ACKNOWLEDGE = C0ControlFunction.NAK;

    /**
     * See {@link C0ControlFunction#NUL}.
     */
    C0ControlFunction NULL = C0ControlFunction.NUL;

    /**
     * See {@link C0ControlFunction#SI}.
     */
    C0ControlFunction SHIFT_IN = C0ControlFunction.SI;

    /**
     * See {@link C0ControlFunction#SO}.
     */
    C0ControlFunction SHIFT_OUT = C0ControlFunction.SO;

    /**
     * See {@link C0ControlFunction#SOH}.
     */
    C0ControlFunction START_OF_HEADING = C0ControlFunction.SOH;

    /**
     * See {@link C0ControlFunction#STX}.
     */
    C0ControlFunction START_OF_TEXT = C0ControlFunction.STX;

    /**
     * See {@link C0ControlFunction#SUB}.
     */
    C0ControlFunction SUBSTITUTE = C0ControlFunction.SUB;

    /**
     * See {@link C0ControlFunction#SYN}.
     */
    C0ControlFunction SYNCHRONOUS_IDLE = C0ControlFunction.SYN;

    /**
     * See {@link C0ControlFunction#VT}.
     */
    C0ControlFunction LINE_TABULATION = C0ControlFunction.VT;
}
