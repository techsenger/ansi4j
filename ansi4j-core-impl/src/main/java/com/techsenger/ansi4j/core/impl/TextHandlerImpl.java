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

package com.techsenger.ansi4j.core.impl;

import java.util.Optional;
import com.techsenger.ansi4j.core.api.Environment;
import com.techsenger.ansi4j.core.api.TextHandler;
import com.techsenger.ansi4j.core.api.TextHandlerResult;

/**
 * Default implementation doesn't do any modifications with text and simply returns fragment with input text.
 *
 * @author Pavel Castornii
 */
public class TextHandlerImpl implements TextHandler {

    private Environment environment;

    @Override
    public TextHandlerResult handle(String text, int currentIndex) {
        return new TextHandlerResultImpl(Optional.of(new TextFragmentImpl(text, currentIndex)), null);
    }

    @Override
    public void initialize(Environment environment) {
        this.environment = environment;
    }

}
