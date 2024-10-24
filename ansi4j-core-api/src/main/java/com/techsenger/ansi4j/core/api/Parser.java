/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
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
