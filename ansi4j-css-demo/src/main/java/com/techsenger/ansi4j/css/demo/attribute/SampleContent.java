/*
 * Copyright 2024 Pavel Castornii.
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
