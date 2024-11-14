/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.api.iso6429;

import java.util.List;

/**
 * C0 set functions. This file was created on the base of the fifth edition of ECMA-48. All the descriptions of
 * the functions (that are used as Java documentation comments here) were taken from the file available at:
 * <a href="https://ecma-international.org/publications-and-standards/standards/ecma-48/">ECMA-48 Standard</a>.
 *
 * @author Pavel Castornii
 */
public enum C0ControlFunction implements ControlFunction {

    /**
     * ACKNOWLEDGE.
     *
     * <p>Notation: (C0).<br>
     * Representation: 00/06 = 0x6 = ''.
     *
     * <p>ACK is transmitted by a receiver as an affirmative response to the sender.
     * The use of ACK is defined in ISO 1745.
     */
    ACK(0x06),

    /**
     * BELL.
     *
     * <p>Notation: (C0).<br>
     * Representation: 00/07 = 0x7 = ''.
     *
     * <p>BEL is used when there is a need to call for attention; it may control alarm or attention devices.
     */
    BEL(0x07),

    /**
     * BACKSPACE.
     *
     * <p>Notation: (C0).<br>
     * Representation: 00/08 = 0x8 = ''.
     *
     * <p>BS causes the active data position to be moved one character position in the data component in the
     * direction opposite to that of the implicit movement.
     * The direction of the implicit movement depends on the parameter value of SELECT IMPLICIT
     * MOVEMENT DIRECTION (SIMD).
     */
    BS(0x08),

    /**
     * CANCEL.
     *
     * <p>Notation: (C0).<br>
     * Representation: 01/08 = 0x18 = ''.
     *
     * <p>CAN is used to indicate that the data preceding it in the data stream is in error. As a result, this data
     * shall be ignored. The specific meaning of this control function shall be defined for each application
     * and/or between sender and recipient.
     */
    CAN(0x18),

    /**
     * CARRIAGE RETURN.
     *
     * <p>Notation: (C0).<br>
     * Representation: 00/13 = 0xd = ''.
     *
     * <p>The effect of CR depends on the setting of the DEVICE COMPONENT SELECT MODE (DCSM) and
     * on the parameter value of SELECT IMPLICIT MOVEMENT DIRECTION (SIMD).
     * If the DEVICE COMPONENT SELECT MODE (DCSM) is set to PRESENTATION and with the
     * parameter value of SIMD equal to 0, CR causes the active presentation position to be moved to the line
     * home position of the same line in the presentation component. The line home position is established by
     * the parameter value of SET LINE HOME (SLH).
     * With a parameter value of SIMD equal to 1, CR causes the active presentation position to be moved to
     * the line limit position of the same line in the presentation component. The line limit position is
     * established by the parameter value of SET LINE LIMIT (SLL).
     * If the DEVICE COMPONENT SELECT MODE (DCSM) is set to DATA and with a parameter value of
     * SIMD equal to 0, CR causes the active data position to be moved to the line home position of the same
     * line in the data component. The line home position is established by the parameter value of SET LINE
     * HOME (SLH).
     * With a parameter value of SIMD equal to 1, CR causes the active data position to be moved to the line
     * limit position of the same line in the data component. The line limit position is established by the
     * parameter value of SET LINE LIMIT (SLL).
     */
    CR(0x0d),

    /**
     * DEVICE CONTROL ONE.
     *
     * <p>Notation: (C0).<br>
     * Representation: 01/01 = 0x11 = ''.
     *
     * <p>DC1 is primarily intended for turning on or starting an ancillary device. If it is not required for this
     * purpose, it may be used to restore a device to the basic mode of operation (see also DC2 and DC3), or
     * any other device control function not provided by other DCs.
     * NOTE
     * When used for data flow control, DC1 is sometimes called "X-ON".
     */
    DC1(0x11),

    /**
     * DEVICE CONTROL TWO.
     *
     * <p>Notation: (C0).<br>
     * Representation: 01/02 = 0x12 = ''.
     *
     * <p>DC2 is primarily intended for turning on or starting an ancillary device. If it is not required for this
     * purpose, it may be used to set a device to a special mode of operation (in which case DC1 is used to
     * restore the device to the basic mode), or for any other device control function not provided by other
     * DCs.
     */
    DC2(0x12),

    /**
     * DEVICE CONTROL THREE.
     *
     * <p>Notation: (C0).<br>
     * Representation: 01/03 = 0x13 = ''.
     *
     * <p>DC3 is primarily intended for turning off or stopping an ancillary device. This function may be a
     * secondary level stop, for example wait, pause, stand-by or halt (in which case DC1 is used to restore
     * normal operation). If it is not required for this purpose, it may be used for any other device control
     * function not provided by other DCs.
     * NOTE
     * When used for data flow control, DC3 is sometimes called "X-OFF".
     */
    DC3(0x13),

    /**
     * DEVICE CONTROL FOUR.
     *
     * <p>Notation: (C0).<br>
     * Representation: 01/04 = 0x14 = ''.
     *
     * <p>DC4 is primarily intended for turning off, stopping or interrupting an ancillary device. If it is not
     * required for this purpose, it may be used for any other device control function not provided by other
     * DCs.
     */
    DC4(0x14),

    /**
     * DELETE.
     *
     * <p>Notation: (C0).<br>
     * Representation: 07/15 = 0x7f = ''.
     *
     * <p>This function is not present in ECMA-48, but it is included in ISO 6429.
     */
    DEL(0x7f),

    /**
     * DATA LINK ESCAPE.
     *
     * <p>Notation: (C0).<br>
     * Representation: 01/00 = 0x10 = ''.
     *
     * <p>DLE is used exclusively to provide supplementary transmission control functions.
     * The use of DLE is defined in ISO 1745.
     */
    DLE(0x10),

    /**
     * END OF MEDIUM.
     *
     * <p>Notation: (C0).<br>
     * Representation: 01/09 = 0x19 = ''.
     *
     * <p>EM is used to identify the physical end of a medium, or the end of the used portion of a medium, or the
     * end of the wanted portion of data recorded on a medium.
     */
    EM(0x19),

    /**
     * ENQUIRY.
     *
     * <p>Notation: (C0).<br>
     * Representation: 00/05 = 0x5 = ''.
     *
     * <p>ENQ is transmitted by a sender as a request for a response from a receiver.
     * The use of ENQ is defined in ISO 1745.
     */
    ENQ(0x05),

    /**
     * END OF TRANSMISSION.
     *
     * <p>Notation: (C0).<br>
     * Representation: 00/04 = 0x4 = ''.
     *
     * <p>EOT is used to indicate the conclusion of the transmission of one or more texts.
     * The use of EOT is defined in ISO 1745.
     */
    EOT(0x04),

    /**
     * ESCAPE.
     *
     * <p>Notation: (C0).<br>
     * Representation: 01/11 = 0x1b = ''.
     *
     * <p>ESC is used for code extension purposes. It causes the meanings of a limited number of bit combinations
     * following it in the data stream to be changed.
     * The use of ESC is defined in Standard ECMA-35.
     */
    ESC(0x1b),

    /**
     * END OF TRANSMISSION BLOCK.
     *
     * <p>Notation: (C0).<br>
     * Representation: 01/07 = 0x17 = ''.
     *
     * <p>ETB is used to indicate the end of a block of data where the data are divided into such blocks for
     * transmission purposes.
     * The use of ETB is defined in ISO 1745.
     */
    ETB(0x17),

    /**
     * END OF TEXT.
     *
     * <p>Notation: (C0).<br>
     * Representation: 00/03 = 0x3 = ''.
     *
     * <p>ETX is used to indicate the end of a text.
     * The use of ETX is defined in ISO 1745.
     */
    ETX(0x03),

    /**
     * FORM FEED.
     *
     * <p>Notation: (C0).<br>
     * Representation: 00/12 = 0xc = ''.
     *
     * <p>FF causes the active presentation position to be moved to the corresponding character position of the
     * line at the page home position of the next form or page in the presentation component. The page home
     * position is established by the parameter value of SET PAGE HOME (SPH).
     */
    FF(0x0c),

    /**
     * CHARACTER TABULATION.
     *
     * <p>Notation: (C0).<br>
     * Representation: 00/09 = 0x9 = 'TAB'.
     *
     * <p>HT causes the active presentation position to be moved to the following character tabulation stop in the
     * presentation component.
     * In addition, if that following character tabulation stop has been set by TABULATION ALIGN CENTRE
     * (TAC), TABULATION ALIGN LEADING EDGE (TALE), TABULATION ALIGN TRAILING EDGE
     * (TATE) or TABULATION CENTRED ON CHARACTER (TCC), HT indicates the beginning of a string
     * of text which is to be positioned within a line according to the properties of that tabulation stop. The end
     * of the string is indicated by the next occurrence of HT or CARRIAGE RETURN (CR) or NEXT LINE
     * (NEL) in the data stream.
     */
    HT(0x09),

    /**
     * INFORMATION SEPARATOR ONE.
     *
     * <p>Notation: (C0).<br>
     * Representation: 01/15 = 0x1f = ''.<br>
     * Specific name: US - UNIT SEPARATOR
     *
     * <p>IS1 is used to separate and qualify data logically; its specific meaning has to be defined for each
     * application. If this control function is used in hierarchical order, it may delimit a data item called a unit,
     * see 8.2.10.
     */
    IS1(0x1f),

    /**
     * INFORMATION SEPARATOR TWO.
     *
     * <p>Notation: (C0).<br>
     * Representation: 01/14 = 0x1e = ''.<br>
     * Specific name: RS - RECORD SEPARATOR.
     *
     * <p>IS2 is used to separate and qualify data logically; its specific meaning has to be defined for each
     * application. If this control function is used in hierarchical order, it may delimit a data item called a
     * record, see 8.2.10.
     */
    IS2(0x1e),

    /**
     * INFORMATION SEPARATOR THREE.
     *
     * <p>Notation: (C0).<br>
     * Representation: 01/13 = 0x1d = ''.<br>
     * Specific name: GS - GROUP SEPARATOR.
     *
     * <p>IS3 is used to separate and qualify data logically; its specific meaning has to be defined for each
     * application. If this control function is used in hierarchical order, it may delimit a data item called a
     * group, see 8.2.10.
     */
    IS3(0x1d),

    /**
     * INFORMATION SEPARATOR FOUR.
     *
     * <p>Notation: (C0).<br>
     * Representation: 01/12 = 0x1c = ''.<br>
     * Specific name: FS - FILE SEPARATOR.
     *
     * <p>IS4 is used to separate and qualify data logically; its specific meaning has to be defined for each
     * application. If this control function is used in hierarchical order, it may delimit a data item called a file,
     * see 8.2.10.
     */
    IS4(0x1c),

    /**
     * LINE FEED.
     *
     * <p>Notation: (C0).<br>
     * Representation: 00/10 = 0xa = ''.
     *
     * <p>If the DEVICE COMPONENT SELECT MODE (DCSM) is set to PRESENTATION, LF causes the
     * active presentation position to be moved to the corresponding character position of the following line in
     * the presentation component.
     * If the DEVICE COMPONENT SELECT MODE (DCSM) is set to DATA, LF causes the active data
     * position to be moved to the corresponding character position of the following line in the data
     * component.
     */
    LF(0x0a),

    /**
     * LOCKING SHIFT ZERO.
     *
     * <p>Notation: (C0).<br>
     * Representation: 00/15 = 0xf = ''.
     *
     * <p>LS0 is used for code extension purposes. It causes the meanings of the bit combinations following it in
     * the data stream to be changed.
     * The use of LS0 is defined in Standard ECMA-35.
     * NOTE
     * LS0 is used in 8-bit environments only; in 7-bit environments SHIFT-IN (SI) is used instead.
     */
    LS0(0x0f),

    /**
     * LOCKING SHIFT ONE.
     *
     * <p>Notation: (C0).<br>
     * Representation: 00/14 = 0xe = ''.
     *
     * <p>LS1 is used for code extension purposes. It causes the meanings of the bit combinations following it in
     * the data stream to be changed.
     * The use of LS1 is defined in Standard ECMA-35.
     * NOTE
     * LS1 is used in 8-bit environments only; in 7-bit environments SHIFT-OUT (SO) is used instead.
     */
    LS1(0x0e),

    /**
     * NEGATIVE ACKNOWLEDGE.
     *
     * <p>Notation: (C0).<br>
     * Representation: 01/05 = 0x15 = ''.
     *
     * <p>NAK is transmitted by a receiver as a negative response to the sender.
     * The use of NAK is defined in ISO 1745.
     */
    NAK(0x15),

    /**
     * NULL.
     *
     * <p>Notation: (C0).<br>
     * Representation: 00/00 = 0x0 = ' '.
     *
     * <p>NUL is used for media-fill or time-fill. NUL characters may be inserted into, or removed from, a data
     * stream without affecting the information content of that stream, but such action may affect the
     * information layout and/or the control of equipment.
     */
    NUL(0x00),

    /**
     * SHIFT IN.
     *
     * <p>Notation: (C0).<br>
     * Representation: 00/15 = 0xf = ''.
     *
     * <p>SI is used for code extension purposes. It causes the meanings of the bit combinations following it in the
     * data stream to be changed.
     * The use of SI is defined in Standard ECMA-35.
     * NOTE
     * SI is used in 7-bit environments only; in 8-bit environments LOCKING-SHIFT ZERO (LS0) is used
     * instead.
     */
    SI(0x0f),

    /**
     * SHIFT OUT.
     *
     * <p>Notation: (C0).<br>
     * Representation: 00/14 = 0xe = ''.
     *
     * <p>SO is used for code extension purposes. It causes the meanings of the bit combinations following it in
     * the data stream to be changed.
     * The use of SO is defined in Standard ECMA-35.
     * NOTE
     * SO is used in 7-bit environments only; in 8-bit environments LOCKING-SHIFT ONE (LS1) is used
     * instead.
     */
    SO(0x0e),

    /**
     * START OF HEADING.
     *
     * <p>Notation: (C0).<br>
     * Representation: 00/01 = 0x1 = ''.
     *
     * <p>SOH is used to indicate the beginning of a heading.
     * The use of SOH is defined in ISO 1745.
     */
    SOH(0x01),

    /**
     * START OF TEXT.
     *
     * <p>Notation: (C0).<br>
     * Representation: 00/02 = 0x2 = ''.
     *
     * <p>STX is used to indicate the beginning of a text and the end of a heading.
     * The use of STX is defined in ISO 1745.
     */
    STX(0x02),

    /**
     * SUBSTITUTE.
     *
     * <p>Notation: (C0).<br>
     * Representation: 01/10 = 0x1a = ''.
     *
     * <p>SUB is used in the place of a character that has been found to be invalid or in error. SUB is intended to
     * be introduced by automatic means.
     */
    SUB(0x1a),

    /**
     * SYNCHRONOUS IDLE.
     *
     * <p>Notation: (C0).<br>
     * Representation: 01/06 = 0x16 = ''.
     *
     * <p>SYN is used by a synchronous transmission system in the absence of any other character (idle condition) to
     * provide a signal from which synchronism may be achieved or retained between data terminal equipment.
     * The use of SYN is defined in ISO 1745.
     */
    SYN(0x16),

    /**
     * LINE TABULATION.
     *
     * <p>Notation: (C0).<br>
     * Representation: 00/11 = 0xb = ''.
     *
     * <p>VT causes the active presentation position to be moved in the presentation component to the
     * corresponding character position on the line at which the following line tabulation stop is set.
     */
    VT(0x0b);

    private final String pattern;

    C0ControlFunction(int code) {
        char c = (char) code;
        this.pattern = String.valueOf(c);
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }

    @Override
    public ControlFunctionType getType() {
        return ControlFunctionType.C0_SET;
    }

    @Override
    public List<Object> getDefaultValues() {
        return null;
    }
}
