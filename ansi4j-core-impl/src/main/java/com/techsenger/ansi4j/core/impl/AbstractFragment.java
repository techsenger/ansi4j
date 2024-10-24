/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.impl;

import com.techsenger.ansi4j.core.api.Fragment;
import com.techsenger.ansi4j.core.api.FragmentType;

/**
 *
 * @author Pavel Castornii
 */
abstract class AbstractFragment implements Fragment {

    private final FragmentType type;

    private final String text;

    private final int startIndex;

    private final int endIndex;

    AbstractFragment(FragmentType type, String text, int currentIndex) {
        this.type = type;
        this.text = text;
        this.startIndex = currentIndex;
        this.endIndex = currentIndex + text.length();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FragmentType getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText() {
        return text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "AbstractFragmentImpl{" + "type=" + type + ", text=" + text + ", startIndex=" + startIndex
                + ", endIndex=" + endIndex + '}';
    }
}
