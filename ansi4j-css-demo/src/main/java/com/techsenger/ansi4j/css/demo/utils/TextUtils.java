/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.demo.utils;

/**
 *
 * @author Pavel Castornii
 */
public final class TextUtils {

    public static String hideEsc(String text) {
        return text.replace("ESC[", "\u001b[");
    }

    public static String showEsc(String text) {
        return text.replace("\u001b[", "ESC[");
    }

    public static boolean isEscHidden(String text) {
        return text.contains("\u001b[");
    }

    public static String suppotedIcon(boolean supported) {
        if (supported) {
            return "\u2713";
        } else {
            return "\u2717";
        }
    }

    private TextUtils() {
        //empty
    }
}
