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

package com.techsenger.ansi4j.core.api.function;

import javax.annotation.concurrent.Immutable;

/**
 *
 * @author Pavel Castornii
 */
@Immutable
public interface FunctionArgument {

    /**
     * Returns the value of the parameter. It can be either an explicit value or a default value.
     *
     */
    Object getValue();

    /**
     * This flag allows to check if parameter value is default (escape code didn't contain explicit value).
     *
     * @return
     */
    boolean isDefault();
}
