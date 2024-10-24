/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.impl;

/**
 *
 * @author Pavel Castornii
 */
public interface FunctionMatcher {

    /**
     * Matches function in text to one of the supported functions of certain type.
     * @param startIndex
     * @param text
     * @return descriptor if function was matched, otherwise null.
     */
    FunctionDescriptor match(int startIndex, String text);

}
