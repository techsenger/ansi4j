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

package com.techsenger.ansi4j.core.impl.iso6429;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeAll;
import com.techsenger.ansi4j.core.api.iso6429.ControlSequenceFunction;
import com.techsenger.ansi4j.core.api.utils.Characters;

/**
 *
 * @author Pavel Castornii
 */
public class ControlSequenceMatcherTest {

    private static ControlSequenceMatcher matcher;

    @BeforeAll
    public static void init() {
        matcher = new ControlSequenceMatcher(Arrays.asList(ControlSequenceFunction.values()));
    }

    @Test
    public void createDescriptor_oneParameter_success() {
        var descriptor = matcher.createDescriptor(ControlSequenceFunction.CBT);
        assertThat(descriptor.getFunction()).isSameAs(ControlSequenceFunction.CBT);
        assertThat(descriptor.getParameters()).containsExactly("{s}");
        assertThat(descriptor.getCodes()).containsExactly("{s}", "Z");
    }

    @Test
    public void createDescriptor_manyParameters_success() {
        var descriptor = matcher.createDescriptor(ControlSequenceFunction.DTA);
        assertThat(descriptor.getFunction()).isSameAs(ControlSequenceFunction.DTA);
        assertThat(descriptor.getParameters()).containsExactly("{s}", "{s}");
        assertThat(descriptor.getCodes()).containsExactly("{s}", ";", "{s}", " ", "T");
    }

    @Test
    public void match_oneParameter_success() {
        var descriptor = matcher.match(0, Characters.ESC + "[12Z");
        assertThat(descriptor.getFunction()).isSameAs(ControlSequenceFunction.CBT);
    }

    @Test
    public void match_manyParameters_success() {
        var descriptor = matcher.match(3, "abc" + Characters.ESC + "[28;14 T");
        assertThat(descriptor.getFunction()).isSameAs(ControlSequenceFunction.DTA);
    }
}
