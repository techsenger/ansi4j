/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.iso6429;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pavel Castornii
 */
final class ControlSequenceUtils {

    /**
     * I ... I are Intermediate Bytes, which, if present, consist of bit combinations from 02/00=32 to 02/15=47.
     * Together with the Final Byte F, they identify the control function;
     *
     * @param codepoint
     * @return
     */
    protected static boolean isIntermediateByte(int codepoint) {
        return codepoint >= 32 && codepoint <= 47;
    }

    /**
     * F is the Final Byte; it consists of a bit combination from 04/00=64 to 07/14=126; it terminates the control
     * sequence and together with the Intermediate Bytes, if present, identifies the control function. Bit
     * combinations 07/00 to 07/14 are available as Final Bytes of control sequences for private (or
     * experimental) use.
     *
     * @param codepoint
     * @return
     */
    protected static boolean isFinalByte(int codepoint) {
        return codepoint >= 64 && codepoint <= 126;
    }

    /**
     * Parses arguments separated with semicolon (;) with possible default values. Default values are nulls in
     * result list.
     * @param text
     * @return list of null if there are no arguments.
     */
    protected static List<String> parseArguments(String text) {
        List<String> arguments = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (int offset = 0; offset < text.length();) {
            final int codepoint = text.codePointAt(offset);
            if (isSemicolon(codepoint)) {
                if (builder.length() > 0) {
                    arguments.add(builder.toString());
                    builder.setLength(0);
                } else {
                    arguments.add(null);
                }
                //is it last semicolon
                if (offset == text.length() - 1) {
                    arguments.add(null);
                }
            } else {
                builder.append(Character.toChars(codepoint));
            }
            offset += Character.charCount(codepoint);
        }
        if (builder.length() > 0) {
            arguments.add(builder.toString());
        }
        if (arguments.isEmpty()) {
            return null;
        } else {
            return arguments;
        }
    }

    protected static boolean isDigit(int codepoint) {
        return codepoint >= 48 && codepoint <= 57;
    }

    protected static boolean isSemicolon(int codepoint) {
        return codepoint == 59;
    }

    protected static boolean isNumber(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private ControlSequenceUtils() {
        //empty
    }
}
