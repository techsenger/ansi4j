/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.demo.attribute;

/**
 *
 * @author Pavel Castornii
 */
class SampleContent<T> {

    private final T value;

    private final String defaultStyle;

    private final String currentStyle;

    SampleContent(T value, String defaultStyle, String currentStyle) {
        this.value = value;
        this.defaultStyle = defaultStyle;
        this.currentStyle = currentStyle;
    }

    public T getValue() {
        return value;
    }

    public String getDefaultStyle() {
        return defaultStyle;
    }

    public String getCurrentStyle() {
        return currentStyle;
    }
}
