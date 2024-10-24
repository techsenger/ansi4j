/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.api;

import java.util.Optional;
import javax.annotation.concurrent.ThreadSafe;

/**
 *
 * @author Pavel Castornii
 */
@ThreadSafe
public interface FunctionFinder extends Initializable {

    /**
     * Finds function in text.
     *
     * @param startIndex
     * @param text
     * @return finder result or null if function isn't found.
     */
    Optional<FunctionFinderResult> find(int startIndex, String text);
}
