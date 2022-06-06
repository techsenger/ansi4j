/*
 * Copyright 2022 Pavel Kastornyy.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pk.ansi4j.core;

import java.util.Optional;
import pk.ansi4j.core.api.Environment;
import pk.ansi4j.core.api.TextParser;
import pk.ansi4j.core.api.TextParserResult;
import pk.ansi4j.core.impl.TextFragmentImpl;
import pk.ansi4j.core.impl.TextParserResultImpl;

/**
 * Default implementation doesn't do any modifications with text and simply returns fragment with input text.
 *
 * @author Pavel Kastornyy
 */
public class DefaultTextParser implements TextParser {

    private Environment environment;

    /**
     * {@inheritDoc}
     */
    @Override
    public TextParserResult parse(String text, int currentIndex) {
        return new TextParserResultImpl(Optional.of(new TextFragmentImpl(text, currentIndex)), null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(Environment environment) {
        this.environment = environment;
    }

}
