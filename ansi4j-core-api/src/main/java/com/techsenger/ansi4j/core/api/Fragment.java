/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.api;

import javax.annotation.concurrent.Immutable;

/**
 *
 * @author Pavel Castornii
 */
@Immutable
public interface Fragment {

    /**
     * Returns type of this fragment.
     *
     * @return
     */
    FragmentType getType();

    /**
     * Text on which base this fragment was created (this is a piece of the whole text).
     *
     * @return
     */
    String getText();

    /**
     * Start index of the fragment text in the whole text, inclusive.
     *
     * @return
     */
    int getStartIndex();

    /**
     * End index of the fragment text in the whole text, exclusive.
     *
     * @return
     */
    int getEndIndex();
}
