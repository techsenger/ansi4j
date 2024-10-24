/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.api.iso6429;

import java.util.List;
import com.techsenger.ansi4j.core.api.utils.Characters;

/**
 * Independent control functions. This file was created on the base of the fifth edition of ECMA-48. All descriptions of
 * the functions (that are used as Java documentation comments in this file) were taken from this ECMA-48 standard.
 *
 * @author Pavel Castornii
 */
public enum IndependentControlFunction implements ControlFunction {

    /**
     * Notation: (Fs)
     * Representation: ESC 06/04=0x64='d'
     * CMD is used as the delimiter of a string of data coded according to Standard ECMA-35 and to switch to
     * a general level of control.
     * The use of CMD is not mandatory if the higher level protocol defines means of delimiting the string, for
     * instance, by specifying the length of the string.
     */
    CMD_CODING_METHOD_DELIMITER(Characters.ESC + "d"),

    /**
     * Notation: (Fs)
     * Representation: ESC 06/00=0x60='`'
     * DMI causes the manual input facilities of a device to be disabled.
     */
    DMI_DISABLE_MANUAL_INPUT(Characters.ESC + "`"),

    /**
     * Notation: (Fs)
     * Representation: ESC 06/02=0x62='b'
     * EMI is used to enable the manual input facilities of a device.
     */
    EMI_ENABLE_MANUAL_INPUT(Characters.ESC + "b"),

    /**
     * Notation: (Fs)
     * Representation: ESC 06/01=0x61='a'
     * INT is used to indicate to the receiving device that the current process is to be interrupted and an agreed
     * procedure is to be initiated. This control function is applicable to either direction of transmission.
     */
    INT_INTERRUPT(Characters.ESC + "a"),

    /**
     * Notation: (Fs)
     * Representation: ESC 07/14=0x7e='~'
     * LS1R is used for code extension purposes. It causes the meanings of the bit combinations following it in
     * the data stream to be changed.
     * The use of LS1R is defined in Standard ECMA-35.
     */
    LS1R_LOCKING_SHIFT_ONE_RIGHT(Characters.ESC + "~"),

    /**
     * Notation: (Fs)
     * Representation: ESC 06/14=0x6e='n'
     * LS2 is used for code extension purposes. It causes the meanings of the bit combinations following it in
     * the data stream to be changed.
     * The use of LS2 is defined in Standard ECMA-35.
     */
    LS2_LOCKING_SHIFT_TWO(Characters.ESC + "n"),

    /**
     * Notation: (Fs)
     * Representation: ESC 07/13=0x7d='}'
     * LS2R is used for code extension purposes. It causes the meanings of the bit combinations following it in
     * the data stream to be changed.
     * The use of LS2R is defined in Standard ECMA-35.
     */
    LS2R_LOCKING_SHIFT_TWO_RIGHT(Characters.ESC + "}"),

    /**
     * Notation: (Fs)
     * Representation: ESC 06/15=0x6f='o'
     * LS3 is used for code extension purposes. It causes the meanings of the bit combinations following it in
     * the data stream to be changed.
     * The use of LS3 is defined in Standard ECMA-35.
     */
    LS3_LOCKING_SHIFT_THREE(Characters.ESC + "o"),

    /**
     * Notation: (Fs)
     * Representation: ESC 07/12=0x7c='|'
     * LS3R is used for code extension purposes. It causes the meanings of the bit combinations following it in
     * the data stream to be changed.
     * The use of LS3R is defined in Standard ECMA-35.
     */
    LS3R_LOCKING_SHIFT_THREE_RIGHT(Characters.ESC + "|"),

    /**
     * Notation: (Fs)
     * Representation: ESC 06/03=0x63='c'
     * RIS causes a device to be reset to its initial state, i.e. the state it has after it is made operational. This
     * may imply, if applicable: clear tabulation stops, remove qualified areas, reset graphic rendition, put all
     * character positions into the erased state, move the active presentation position to the first position of the
     * first line in the presentation component, move the active data position to the first character position of
     * the first line in the data component, set the modes into the reset state, etc.
     */
    RIS_RESET_TO_INITIAL_STATE(Characters.ESC + "c");

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
