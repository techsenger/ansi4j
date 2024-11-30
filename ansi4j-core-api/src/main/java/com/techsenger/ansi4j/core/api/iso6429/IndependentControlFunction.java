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

import java.util.List;
import com.techsenger.ansi4j.core.api.utils.Characters;

/**
 * Independent control functions. This file was created on the base of the fifth edition of ECMA-48. All the
 * descriptions of the functions (that are used as Java documentation comments here) were taken from the file available
 * at: <a href="https://ecma-international.org/publications-and-standards/standards/ecma-48/">ECMA-48 Standard</a>.
 *
 * @author Pavel Castornii
 */
public enum IndependentControlFunction implements ControlFunction {

    /**
     * CODING_METHOD_DELIMITER.
     *
     * <p>Notation: (Fs).<br>
     * Representation: ESC 06/04 = 0x64 = 'd'.
     *
     * <p>CMD is used as the delimiter of a string of data coded according to Standard ECMA-35 and to switch to
     * a general level of control.
     * The use of CMD is not mandatory if the higher level protocol defines means of delimiting the string, for
     * instance, by specifying the length of the string.
     */
    CMD(Characters.ESC + "d"),

    /**
     * DISABLE_MANUAL_INPUT.
     *
     * <p>Notation: (Fs).<br>
     * Representation: ESC 06/00 = 0x60 = '`'.
     *
     * <p>DMI causes the manual input facilities of a device to be disabled.
     */
    DMI(Characters.ESC + "`"),

    /**
     * ENABLE_MANUAL_INPUT.
     *
     * <p>Notation: (Fs).<br>
     * Representation: ESC 06/02 = 0x62 = 'b'.
     *
     * <p>EMI is used to enable the manual input facilities of a device.
     */
    EMI(Characters.ESC + "b"),

    /**
     * INTERRUPT.
     *
     * <p>Notation: (Fs).<br>
     * Representation: ESC 06/01 = 0x61 = 'a'.
     *
     * <p>INT is used to indicate to the receiving device that the current process is to be interrupted and an agreed
     * procedure is to be initiated. This control function is applicable to either direction of transmission.
     */
    INT(Characters.ESC + "a"),

    /**
     * LOCKING_SHIFT_ONE_RIGHT.
     *
     * <p>Notation: (Fs).<br>
     * Representation: ESC 07/14 = 0x7e = '~'.
     *
     * <p>LS1R is used for code extension purposes. It causes the meanings of the bit combinations following it in
     * the data stream to be changed.
     * The use of LS1R is defined in Standard ECMA-35.
     */
    LS1R(Characters.ESC + "~"),

    /**
     * LOCKING_SHIFT_TWO.
     *
     * <p>Notation: (Fs).<br>
     * Representation: ESC 06/14 = 0x6e = 'n'.
     *
     * <p>LS2 is used for code extension purposes. It causes the meanings of the bit combinations following it in
     * the data stream to be changed.
     * The use of LS2 is defined in Standard ECMA-35.
     */
    LS2(Characters.ESC + "n"),

    /**
     * LOCKING_SHIFT_TWO_RIGHT.
     *
     * <p>Notation: (Fs).<br>
     * Representation: ESC 07/13 = 0x7d = '}'.
     *
     * <p>LS2R is used for code extension purposes. It causes the meanings of the bit combinations following it in
     * the data stream to be changed.
     * The use of LS2R is defined in Standard ECMA-35.
     */
    LS2R(Characters.ESC + "}"),

    /**
     * LOCKING_SHIFT_THREE.
     *
     * <p>Notation: (Fs).<br>
     * Representation: ESC 06/15 = 0x6f = 'o'.
     *
     * <p>LS3 is used for code extension purposes. It causes the meanings of the bit combinations following it in
     * the data stream to be changed.
     * The use of LS3 is defined in Standard ECMA-35.
     */
    LS3(Characters.ESC + "o"),

    /**
     * LOCKING_SHIFT_THREE_RIGHT.
     *
     * <p>Notation: (Fs).<br>
     * Representation: ESC 07/12 = 0x7c = '|'.
     *
     * <p>LS3R is used for code extension purposes. It causes the meanings of the bit combinations following it in
     * the data stream to be changed.
     * The use of LS3R is defined in Standard ECMA-35.
     */
    LS3R(Characters.ESC + "|"),

    /**
     * RESET_TO_INITIAL_STATE.
     *
     * <p>Notation: (Fs).<br>
     * Representation: ESC 06/03 = 0x63 = 'c'.
     *
     * <p>RIS causes a device to be reset to its initial state, i.e. the state it has after it is made operational. This
     * may imply, if applicable: clear tabulation stops, remove qualified areas, reset graphic rendition, put all
     * character positions into the erased state, move the active presentation position to the first position of the
     * first line in the presentation component, move the active data position to the first character position of
     * the first line in the data component, set the modes into the reset state, etc.
     */
    RIS(Characters.ESC + "c");

    private final String pattern;

    IndependentControlFunction(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public ControlFunctionType getType() {
        return ControlFunctionType.INDEPENDENT_FUNCTION;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }

    @Override
    public List<Object> getDefaultValues() {
        return null;
    }
}
