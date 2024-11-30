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
