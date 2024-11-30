/*
 * Copyright 2024 Pavel Castornii.
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

package com.techsenger.ansi4j.core.impl;

import com.techsenger.ansi4j.core.api.Fragment;
import com.techsenger.ansi4j.core.api.FragmentType;
import com.techsenger.ansi4j.core.api.utils.Characters;

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

    @Override
    public FragmentType getType() {
        return type;
    }

    @Override
    public int getStartIndex() {
        return startIndex;
    }

    @Override
    public int getEndIndex() {
        return endIndex;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        var t = this.text;
        if (t != null) {
            t = Characters.invisibleToUnicode(t);
        }
        return "AbstractFragmentImpl{" + "type=" + type + ", text=" + t + ", startIndex=" + startIndex
                + ", endIndex=" + endIndex + '}';
    }
}
