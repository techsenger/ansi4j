/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.api.iso6429;

import java.util.List;
import java.util.Set;
import com.techsenger.ansi4j.core.api.utils.Characters;

/**
 * C1 set functions. This file was created on the base of the fifth edition of ECMA-48. All the descriptions of
 * the functions (that are used as Java documentation comments here) were taken from the file available at:
 * <a href="https://ecma-international.org/publications-and-standards/standards/ecma-48/">ECMA-48 Standard</a>.
 *
 * @author Pavel Castornii
 */
public enum C1ControlFunction implements ControlFunction {

    /**
     * APPLICATION PROGRAM COMMAND.
     *
     * <p>Notation: (C1).<br>
     * Representation: 09/15 = 0x9f = '' or ESC 05/15 = 0x5f = '_'.
     *
     * <p>APC is used as the opening delimiter of a control string for application program use. The command
     * string following may consist of bit combinations in the range 00/08 to 00/13 and 02/00 to 07/14. The
     * control string is closed by the terminating delimiter STRING TERMINATOR (ST). The interpretation of
     * the command string depends on the relevant application program.
     */
    APC(Characters.ESC + "_", 0x9f),

    /**
     * BREAK PERMITTED HERE.
     *
     * <p>Notation: (C1).<br>
     * Representation: 08/02 = 0x82 = '' or ESC 04/02 = 0x42 = 'B'.
     *
     * <p>BPH is used to indicate a point where a line break may occur when text is formatted. BPH may occur
     * between two graphic characters, either or both of which may be SPACE.
     */
    BPH(Characters.ESC + "B", 0x82),

    /**
     * CANCEL CHARACTER.
     *
     * <p>Notation: (C1).<br>
     * Representation: 09/04 = 0x94 = '' or ESC 05/04 = 0x54 = 'T'.
     *
     * <p>CCH is used to indicate that both the preceding graphic character in the data stream, (represented by one
     * or more bit combinations) including SPACE, and the control function CCH itself are to be ignored for
     * further interpretation of the data stream.
     * If the character preceding CCH in the data stream is a control function (represented by one or more bit
     * combinations), the effect of CCH is not defined by this Standard.
     */
    CCH(Characters.ESC + "T", 0x94),

    /**
     * CONTROL SEQUENCE INTRODUCER.
     *
     * <p>Notation: (C1).<br>
     * Representation: 09/11 = 0x9b = '' or ESC 05/11 = 0x5b = '['.
     *
     * <p>CSI is used as the first character of a control sequence, see 5.4.
     */
    CSI(Characters.ESC + "[", 0x9b),

    /**
     * DEVICE CONTROL STRING.
     *
     * <p>Notation: (C1).<br>
     * Representation: 09/00 = 0x90 = '' or ESC 05/00 = 0x50 = 'P'.
     *
     * <p>DCS is used as the opening delimiter of a control string for device control use. The command string
     * following may consist of bit combinations in the range 00/08 to 00/13 and 02/00 to 07/14. The control
     * string is closed by the terminating delimiter STRING TERMINATOR (ST).
     * The command string represents either one or more commands for the receiving device, or one or more
     * status reports from the sending device. The purpose and the format of the command string are specified
     * by the most recent occurrence of IDENTIFY DEVICE CONTROL STRING (IDCS), if any, or depend on
     * the sending and/or the receiving device.
     */
    DCS(Characters.ESC + "P", 0x90),

    /**
     * END OF GUARDED AREA.
     *
     * <p>Notation: (C1).<br>
     * Representation: 09/07 = 0x97 = '' or ESC 05/07 = 0x57 = 'W'.
     *
     * <p>EPA is used to indicate that the active presentation position is the last of a string of character positions
     * in the presentation component, the contents of which are protected against manual alteration, are
     * guarded against transmission or transfer, depending on the setting of the GUARDED AREA TRANSFER
     * MODE (GATM), and may be protected against erasure, depending on the setting of the ERASURE
     * MODE (ERM). The beginning of this string is indicated by START OF GUARDED AREA (SPA).
     * NOTE
     * The control functions for area definition (DAQ, EPA, ESA, SPA, SSA) should not be used within an SRS
     * string or an SDS string.
     */
    EPA(Characters.ESC + "W", 0x97),

    /**
     * END OF SELECTED AREA.
     *
     * <p>Notation: (C1).<br>
     * Representation: 08/07 = 0x87 = '' or ESC 04/07 = 0x47 = 'G'.
     *
     * <p>ESA is used to indicate that the active presentation position is the last of a string of character positions
     * in the presentation component, the contents of which are eligible to be transmitted in the form of a data
     * stream or transferred to an auxiliary input/output device. The beginning of this string is indicated by
     * START OF SELECTED AREA (SSA).
     * NOTE
     * The control function for area definition (DAQ, EPA, ESA, SPA, SSA) should not be used within an SRS
     * string or an SDS string.
     */
    ESA(Characters.ESC + "G", 0x87),

    /**
     * CHARACTER TABULATION WITH JUSTIFICATION.
     *
     * <p>Notation: (C1).<br>
     * Representation: 08/09 = 0x89 = '' or ESC 04/09 = 0x49 = 'I'.
     *
     * <p>HTJ causes the contents of the active field (the field in the presentation component that contains the
     * active presentation position) to be shifted forward so that it ends at the character position preceding the
     * following character tabulation stop. The active presentation position is moved to that following character
     * tabulation stop. The character positions which precede the beginning of the shifted string are put into the
     * erased state.
     */
    HTJ(Characters.ESC + "I", 0x89),

    /**
     * CHARACTER TABULATION SET.
     *
     * <p>Notation: (C1).<br>
     * Representation: 08/08 = 0x88 = '' or ESC 04/08 = 0x48 = 'H'.
     *
     * <p>HTS causes a character tabulation stop to be set at the active presentation position in the presentation
     * component.
     * The number of lines affected depends on the setting of the TABULATION STOP MODE (TSM).
     */
    HTS(Characters.ESC + "H", 0x88),

    /**
     * MESSAGE WAITING.
     *
     * <p>Notation: (C1).<br>
     * Representation: 09/05 = 0x95 = '' or ESC 05/05 = 0x55 = 'U'.
     *
     * <p>MW is used to set a message waiting indicator in the receiving device. An appropriate acknowledgement
     * to the receipt of MW may be given by using DEVICE STATUS REPORT (DSR).
     */
    MW(Characters.ESC + "U", 0x95),

    /**
     * NO BREAK HERE.
     *
     * <p>Notation: (C1).<br>
     * Representation: 08/03 = 0x83 = '' or ESC 04/03 = 0x43 = 'C'.
     *
     * <p>NBH is used to indicate a point where a line break shall not occur when text is formatted. NBH may
     * occur between two graphic characters either or both of which may be SPACE.
     */
    NBH(Characters.ESC + "C", 0x83),

    /**
     * NEXT LINE.
     *
     * <p>Notation: (C1).<br>
     * Representation: 08/05 = 0x85 = '' or ESC 04/05 = 0x45 = 'E'.
     *
     * <p>The effect of NEL depends on the setting of the DEVICE COMPONENT SELECT MODE (DCSM) and
     * on the parameter value of SELECT IMPLICIT MOVEMENT DIRECTION (SIMD).
     * If the DEVICE COMPONENT SELECT MODE (DCSM) is set to PRESENTATION and with a
     * parameter value of SIMD equal to 0, NEL causes the active presentation position to be moved to the line
     * home position of the following line in the presentation component. The line home position is established
     * by the parameter value of SET LINE HOME (SLH).
     * With a parameter value of SIMD equal to 1, NEL causes the active presentation position to be moved to
     * the line limit position of the following line in the presentation component. The line limit position is
     * established by the parameter value of SET LINE LIMIT (SLL).
     * If the DEVICE COMPONENT SELECT MODE (DCSM) is set to DATA and with a parameter value of
     * SIMD equal to 0, NEL causes the active data position to be moved to the line home position of the
     * following line in the data component. The line home position is established by the parameter value of
     * SET LINE HOME (SLH).
     * With a parameter value of SIMD equal to 1, NEL causes the active data position to be moved to the line
     * limit position of the following line in the data component. The line limit position is established by the
     * parameter value of SET LINE LIMIT (SLL).
     */
    NEL(Characters.ESC + "E", 0x85),

    /**
     * OPERATING SYSTEM COMMAND.
     *
     * <p>Notation: (C1).<br>
     * Representation: 09/13 = 0x9d = '' or ESC 05/13 = 0x5d = ']'.
     *
     * <p>OSC is used as the opening delimiter of a control string for operating system use. The command string
     * following may consist of a sequence of bit combinations in the range 00/08 to 00/13 and 02/00 to 07/14.
     * The control string is closed by the terminating delimiter STRING TERMINATOR (ST). The
     * interpretation of the command string depends on the relevant operating system.
     */
    OSC(Characters.ESC + "]", 0x9d),

    /**
     * PARTIAL LINE FORWARD.
     *
     * <p>Notation: (C1).<br>
     * Representation: 08/11 = 0x8b = '' or ESC 04/11 = 0x4b = 'K'.
     *
     * <p>PLD causes the active presentation position to be moved in the presentation component to the
     * corresponding position of an imaginary line with a partial offset in the direction of the line progression.
     * This offset should be sufficient either to image following characters as subscripts until the first
     * following occurrence of PARTIAL LINE BACKWARD (PLU) in the data stream, or, if preceding
     * characters were imaged as superscripts, to restore imaging of following characters to the active line (the
     * line that contains the active presentation position).
     * Any interactions between PLD and format effectors other than PLU are not defined by this Standard.
     */
    PLD(Characters.ESC + "K", 0x8b),

    /**
     * PARTIAL LINE BACKWARD.
     *
     * <p>Notation: (C1).<br>
     * Representation: 08/12 = 0x8c = '' or ESC 04/12 = 0x4c = 'L'.
     *
     * <p>PLU causes the active presentation position to be moved in the presentation component to the
     * corresponding position of an imaginary line with a partial offset in the direction opposite to that of the
     * line progression. This offset should be sufficient either to image following characters as superscripts
     * until the first following occurrence of PARTIAL LINE FORWARD (PLD) in the data stream, or, if
     * preceding characters were imaged as subscripts, to restore imaging of following characters to the active
     * line (the line that contains the active presentation position).
     * Any interactions between PLU and format effectors other than PLD are not defined by this Standard.
     */
    PLU(Characters.ESC + "L", 0x8c),

    /**
     * PRIVACY MESSAGE.
     *
     * <p>Notation: (C1).<br>
     * Representation: 09/14 = 0x9e = '' or ESC 05/14 = 0x5e = '^'.
     *
     * <p>PM is used as the opening delimiter of a control string for privacy message use. The command string
     * following may consist of a sequence of bit combinations in the range 00/08 to 00/13 and 02/00 to 07/14.
     * The control string is closed by the terminating delimiter STRING TERMINATOR (ST). The
     * interpretation of the command string depends on the relevant privacy discipline.
     */
    PM(Characters.ESC + "^", 0x9e),

    /**
     * PRIVATE USE ONE.
     *
     * <p>Notation: (C1).<br>
     * Representation: 09/01 = 0x91 = '' or ESC 05/01 = 0x51 = 'Q'.
     *
     * <p>PU1 is reserved for a function without standardized meaning for private use as required, subject to the
     * prior agreement between the sender and the recipient of the data.
     */
    PU1(Characters.ESC + "Q", 0x91),

    /**
     * PRIVATE USE TWO.
     *
     * <p>Notation: (C1).<br>
     * Representation: 09/02 = 0x92 = '' or ESC 05/02 = 0x52 = 'R'.
     *
     * <p>PU2 is reserved for a function without standardized meaning for private use as required, subject to the
     * prior agreement between the sender and the recipient of the data.
     */
    PU2(Characters.ESC + "R", 0x92),

    /**
     * REVERSE LINE FEED.
     *
     * <p>Notation: (C1).<br>
     * Representation: 08/13 = 0x8d = '' or ESC 04/13 = 0x4d = 'M'.
     *
     * <p>If the DEVICE COMPONENT SELECT MODE (DCSM) is set to PRESENTATION, RI causes the
     * active presentation position to be moved in the presentation component to the corresponding character
     * position of the preceding line.
     * If the DEVICE COMPONENT SELECT MODE (DCSM) is set to DATA, RI causes the active data
     * position to be moved in the data component to the corresponding character position of the preceding
     * line.
     */
    RI(Characters.ESC + "M", 0x8d),

    /**
     * SINGLE CHARACTER INTRODUCER.
     *
     * <p>Notation: (C1).<br>
     * Representation: 09/10 = 0x9a = '' or ESC 05/10 = 0x5a = 'Z'.
     *
     * <p>SCI and the bit combination following it are used to represent a control function or a graphic character.
     * The bit combination following SCI must be from 00/08 to 00/13 or 02/00 to 07/14. The use of SCI is
     * reserved for future standardization.
     */
    SCI(Characters.ESC + "Z", 0x9a),

    /**
     * START OF STRING.
     *
     * <p>Notation: (C1).<br>
     * Representation: 09/08 = 0x98 = '' or ESC 05/08 = 0x58 = 'X'.
     *
     * <p>SOS is used as the opening delimiter of a control string. The character string following may consist of
     * any bit combination, except those representing SOS or STRING TERMINATOR (ST). The control string
     * is closed by the terminating delimiter STRING TERMINATOR (ST). The interpretation of the character
     * string depends on the application.
     */
    SOS(Characters.ESC + "X", 0x98),

    /**
     * START OF GUARDED AREA.
     *
     * <p>Notation: (C1).<br>
     * Representation: 09/06 = 0x96 = '' or ESC 05/06 = 0x56 = 'V'.
     *
     * <p>SPA is used to indicate that the active presentation position is the first of a string of character positions
     * in the presentation component, the contents of which are protected against manual alteration, are
     * guarded against transmission or transfer, depending on the setting of the GUARDED AREA TRANSFER
     * MODE (GATM) and may be protected against erasure, depending on the setting of the ERASURE
     * MODE (ERM). The end of this string is indicated by END OF GUARDED AREA (EPA).
     * NOTE
     * The control functions for area definition (DAQ, EPA, ESA, SPA, SSA) should not be used within an SRS
     * string or an SDS string.
     */
    SPA(Characters.ESC + "V", 0x96),

    /**
     * START OF SELECTED AREA.
     *
     * <p>Notation: (C1).<br>
     * Representation: 08/06 = 0x86 = '' or ESC 04/06 = 0x46 = 'F'.
     *
     * <p>SSA is used to indicate that the active presentation position is the first of a string of character positions
     * in the presentation component, the contents of which are eligible to be transmitted in the form of a data
     * stream or transferred to an auxiliary input/output device.
     * The end of this string is indicated by END OF SELECTED AREA (ESA). The string of characters
     * actually transmitted or transferred depends on the setting of the GUARDED AREA TRANSFER MODE
     * (GATM) and on any guarded areas established by DEFINE AREA QUALIFICATION (DAQ), or by
     * START OF GUARDED AREA (SPA) and END OF GUARDED AREA (EPA).
     * NOTE
     * The control functions for area definition (DAQ, EPA, ESA, SPA, SSA) should not be used within an SRS
     * string or an SDS string.
     */
    SSA(Characters.ESC + "F", 0x86),

    /**
     * SINGLE SHIFT TWO.
     *
     * <p>Notation: (C1).<br>
     * Representation: 08/14 = 0x8e = '' or ESC 04/14 = 0x4e = 'N'.
     *
     * <p>SS2 is used for code extension purposes. It causes the meanings of the bit combinations following it in
     * the data stream to be changed.
     * The use of SS2 is defined in Standard ECMA-35.
     */
    SS2(Characters.ESC + "N", 0x8e),

    /**
     * SINGLE SHIFT THREE.
     *
     * <p>Notation: (C1).<br>
     * Representation: 08/15 = 0x8f = '' or ESC 04/15 = 0x4f = 'O'.
     *
     * <p>SS3 is used for code extension purposes. It causes the meanings of the bit combinations following it in
     * the data stream to be changed.
     * The use of SS3 is defined in Standard ECMA-35.
     */
    SS3(Characters.ESC + "O", 0x8f),

    /**
     * STRING TERMINATOR.
     *
     * <p>Notation: (C1).<br>
     * Representation: 09/12 = 0x9c = '' or ESC 05/12 = 0x5c = '\'.
     *
     * <p>ST is used as the closing delimiter of a control string opened by APPLICATION PROGRAM
     * COMMAND (APC), DEVICE CONTROL STRING (DCS), OPERATING SYSTEM COMMAND
     * (OSC), PRIVACY MESSAGE (PM), or START OF STRING (SOS).
     */
    ST(Characters.ESC + "\\", 0x9c),

    /**
     * SET TRANSMIT STATE.
     *
     * <p>Notation: (C1).<br>
     * Representation: 09/03 = 0x93 = '' or ESC 05/03 = 0x53 = 'S'.
     *
     * <p>STS is used to establish the transmit state in the receiving device. In this state the transmission of data
     * from the device is possible.
     * The actual initiation of transmission of data is performed by a data communication or input/output
     * interface control procedure which is outside the scope of this Standard.
     * The transmit state is established either by STS appearing in the received data stream or by the operation
     * of an appropriate key on a keyboard.
     */
    STS(Characters.ESC + "S", 0x93),

    /**
     * LINE TABULATION SET.
     *
     * <p>Notation: (C1).<br>
     * Representation: 08/10 = 0x8a = '' or ESC 04/10 = 0x4a = 'J'.
     *
     * <p>VTS causes a line tabulation stop to be set at the active line (the line that contains the active presentation
     * position).
     */
    VTS(Characters.ESC + "J", 0x8a);

    /**
     * Returns C1 functions which are opening delimiters for functions of control string type.
     * @return
     */
    public static Set<C1ControlFunction> getControlStringOpeningDelimiters() {
        return Set.of(APC, DCS, OSC, PM, SOS);
    }

    private final String pattern;

    /**
     * This pattern is used in 8 bit environment.
     */
    private final String pattern8Bit;

    C1ControlFunction(String pattern, int pattern8Bit) {
        this.pattern = pattern;
        char c = (char) pattern8Bit;
        this.pattern8Bit = String.valueOf(c);
    }

    @Override
    public String getPattern() {
        return pattern;
    }

    @Override
    public ControlFunctionType getType() {
        return ControlFunctionType.C1_SET;
    }

    @Override
    public List<Object> getDefaultValues() {
        return null;
    }

    public String get8BitPattern() {
        return pattern8Bit;
    }
}

