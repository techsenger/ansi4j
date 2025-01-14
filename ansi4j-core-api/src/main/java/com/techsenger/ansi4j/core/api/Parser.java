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

package com.techsenger.ansi4j.core.api;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * Parses text with control functions.
 *
 * @author Pavel Castornii
 */
@NotThreadSafe
public interface Parser {

    /**
     * Parses next fragment and returns it.
     *
     * @return fragment or null if there is no fragments to parse.
     */
    Fragment parse();

    /**
     * Returns current index in text. Everything before index has been parsed, everything after index hasn't been
     * parsed.
     *
     * @return
     */
    int getCurrentIndex();
}
