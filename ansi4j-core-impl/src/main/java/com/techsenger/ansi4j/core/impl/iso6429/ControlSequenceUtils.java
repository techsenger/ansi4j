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

package com.techsenger.ansi4j.core.impl.iso6429;

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
