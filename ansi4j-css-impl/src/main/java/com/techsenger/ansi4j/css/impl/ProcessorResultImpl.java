/*
 * Copyright 2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.impl;

import com.techsenger.ansi4j.css.api.attribute.AttributeChange;
import com.techsenger.ansi4j.css.api.ProcessorResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Pavel Castornii
 */
public class ProcessorResultImpl implements ProcessorResult {

    private List<AttributeChange<?>> attributeChanges = new ArrayList<>();

    private List<String> styleDeclarations = new ArrayList<>();

    @Override
    public List<AttributeChange<?>> getAttributeChanges() {
        return attributeChanges;
    }

    @Override
    public List<String> getStyleDeclarations() {
        return this.styleDeclarations;
    }

    void makeListsUnmodifiable() {
        this.attributeChanges = Collections.unmodifiableList(attributeChanges);
        this.styleDeclarations = Collections.unmodifiableList(styleDeclarations);
    }
}
