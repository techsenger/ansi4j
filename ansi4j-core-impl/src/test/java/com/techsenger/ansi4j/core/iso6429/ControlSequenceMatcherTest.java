/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.iso6429;

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
        var descriptor = matcher.createDescriptor(ControlSequenceFunction.CBT_CURSOR_BACKWARD_TABULATION);
        assertThat(descriptor.getFunction()).isSameAs(ControlSequenceFunction.CBT_CURSOR_BACKWARD_TABULATION);
        assertThat(descriptor.getParameters()).containsExactly("{s}");
        assertThat(descriptor.getCodes()).containsExactly("{s}", "Z");
    }

    @Test
    public void createDescriptor_manyParameters_success() {
        var descriptor = matcher.createDescriptor(ControlSequenceFunction.DTA_DIMENSION_TEXT_AREA);
        assertThat(descriptor.getFunction()).isSameAs(ControlSequenceFunction.DTA_DIMENSION_TEXT_AREA);
        assertThat(descriptor.getParameters()).containsExactly("{s}", "{s}");
        assertThat(descriptor.getCodes()).containsExactly("{s}", ";", "{s}", " ", "T");
    }

    @Test
    public void match_oneParameter_success() {
        var descriptor = matcher.match(0, Characters.ESC + "[12Z");
        assertThat(descriptor.getFunction()).isSameAs(ControlSequenceFunction.CBT_CURSOR_BACKWARD_TABULATION);
    }

    @Test
    public void match_manyParameters_success() {
        var descriptor = matcher.match(3, "abc" + Characters.ESC + "[28;14 T");
        assertThat(descriptor.getFunction()).isSameAs(ControlSequenceFunction.DTA_DIMENSION_TEXT_AREA);
    }
}
