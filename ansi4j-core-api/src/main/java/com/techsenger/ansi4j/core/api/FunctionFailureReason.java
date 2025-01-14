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
