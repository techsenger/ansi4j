/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.api;

import java.util.Optional;
import javax.annotation.concurrent.Immutable;

/**
 *
 * @author Pavel Castornii
 */
@Immutable
public interface TextHandlerResult extends FragmentHandlerResult {

    /**
     * Parsed fragment or empty optional.
     *
     * @return
     */
    Optional<TextFragment> getFragment();
}