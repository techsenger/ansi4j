/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.demo.text;

/**
 *
 * @author Pavel Castornii
 */
class SgrParameter {

    private final int code;

    private final String description;

    private final boolean supported;

    SgrParameter(int code, String description, boolean supported) {
        this.code = code;
        this.description = description;
        this.supported = supported;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSupported() {
        return supported;
    }
}
