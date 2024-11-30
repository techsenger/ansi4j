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

package com.techsenger.ansi4j.css.demo.attribute;

import com.techsenger.ansi4j.css.api.attribute.Attribute;
import com.techsenger.ansi4j.css.api.text.TextAttributeGroup;
import com.techsenger.ansi4j.css.impl.attribute.AttributeImpl;

/**
 *
 * @author Pavel Castornii
 */
abstract class AbstractAttributeSample<T> {

    private final T defaultValue;

    private final T currentValue;

    private final boolean supportedInWebView;

    private final boolean supportedInTextFlow;

    private final boolean supportedInRtfxTextArea;

    private AttributeImpl<T> attribute;

    AbstractAttributeSample(T defaultValue, T currentValue, boolean supportedInWebView,
            boolean supportedInTextFlow, boolean supportedInRtfxTextArea) {
        this.defaultValue = defaultValue;
        this.currentValue = currentValue;
        this.supportedInWebView = supportedInWebView;
        this.supportedInTextFlow = supportedInTextFlow;
        this.supportedInRtfxTextArea = supportedInRtfxTextArea;
    }

    public abstract Attribute<T> resolveAttribute(TextAttributeGroup group);

    public void prepareForDefaultStyle() {
        attribute.setDefaultValue(currentValue);
        attribute.setValue(defaultValue);
    }

    public void prepareForCurrentStyle() {
        attribute.setDefaultValue(defaultValue);
        attribute.setValue(currentValue);
    }

    public Attribute<T> getAttribute() {
        return attribute;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public T getCurrentValue() {
        return currentValue;
    }

    public String toString(T value) {
        return value.toString();
    }

    public boolean isSupportedInWebView() {
        return supportedInWebView;
    }

    public boolean isSupportedInTextFlow() {
        return supportedInTextFlow;
    }

    public boolean isSupportedInRtfxTextArea() {
        return supportedInRtfxTextArea;
    }

    protected void setAttribute(Attribute<T> attribute) {
        this.attribute = (AttributeImpl<T>) attribute;
    }
}
