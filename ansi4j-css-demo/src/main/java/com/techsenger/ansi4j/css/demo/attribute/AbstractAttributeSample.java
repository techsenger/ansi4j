/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
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
