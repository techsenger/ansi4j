/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.api;

/**
 *
 * @author Pavel Castornii
 */
public enum FunctionFailureReason implements FailureReason {

    /**
     * Parser can't parse function as it doesn't know how to parse it.
     */
    UNKNOWN_FUNCTION,

    /**
     * This reason happens when text for parsing is read from stream. Using this reason parser understands
     * if it can parse function or parsing must be repeated when whole function is read from the stream.
     */
    NO_END_OF_FUNCTION
}
