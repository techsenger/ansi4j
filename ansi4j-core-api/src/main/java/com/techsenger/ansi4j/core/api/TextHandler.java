/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.api;

import javax.annotation.concurrent.ThreadSafe;

/**
 * This parser can do any modification with text.
 *
 * @author Pavel Castornii
 */
@ThreadSafe
public interface TextHandler extends FragmentHandler {

    /**
     * Parses text (string that doesn't contain functions).
     *
     * @param text is a piece of the whole text and doesn't have any functions.
     * @param currentIndex index in the whole text (is equal to parsed text length). This parameter is required for
     * calculating start and end index as they are relative to the whole text.
     *
     * @return
     */
    TextHandlerResult handle(String text, int currentIndex);
}
