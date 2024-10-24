/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.impl;

import com.techsenger.ansi4j.core.api.FragmentType;
import com.techsenger.ansi4j.core.api.TextFragment;

/**
 *
 * @author Pavel Castornii
 */
public class TextFragmentImpl extends AbstractFragment implements TextFragment {

    public TextFragmentImpl(String text, int currentIndex) {
        super(FragmentType.TEXT, text, currentIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "TextFragmentImpl{" + '}' + "->" + super.toString();
    }
}
