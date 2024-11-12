/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.api.utils;

/**
 *
 * @author Pavel Castornii
 */
public final class Characters {

    /**
     * Escape.
     */
    public static final char ESC = '\u001b';

    /**
     * Left square bracket.
     */
    public static final char LEFT_SB = '[';

    /**
     * Replaces invisible characters with unicodes in a string.
     *
     * @param input
     * @return
     */
    public static String invisibleToUnicode(String input) {
        StringBuilder result = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (Character.isISOControl(ch) || (ch >= 0x200B && ch <= 0x200D) || (ch >= 0x202A && ch <= 0x202E)) {
                result.append(String.format("\\u%04x", (int) ch));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    private Characters() {
        //empty
    }
}
