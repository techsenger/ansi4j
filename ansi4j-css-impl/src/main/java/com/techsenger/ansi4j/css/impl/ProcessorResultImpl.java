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
