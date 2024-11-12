/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.impl.iso6429;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Pavel Castornii
 */
public class ControlSequenceUtilsTest {

    @Test
    public void parseArguments_string_noArg_success() {
        var arguments = ControlSequenceUtils.parseArguments("");
        assertThat(arguments).isNull();
    }

    @Test
    public void parseArguments_string_oneArgNoDefaultValues_success() {
        var arguments = ControlSequenceUtils.parseArguments("20");
        assertThat(arguments).containsExactly("20");
    }

    @Test
    public void parseArguments_string_manyArgsWithoutDefaultValues_success() {
        var arguments = ControlSequenceUtils.parseArguments("20;30");
        assertThat(arguments).containsExactly("20", "30");
    }

    @Test
    public void parseArguments_string_manyArgsWithOneDefaultValues_success() {
        var arguments = ControlSequenceUtils.parseArguments(";20;30");
        assertThat(arguments).containsExactly(null, "20", "30");
    }

    @Test
    public void parseArguments_string_manyArgsWithTwoDefaultValues_success() {
        var arguments = ControlSequenceUtils.parseArguments("20;;30;");
        assertThat(arguments).containsExactly("20", null, "30", null);
    }

    @Test
    public void parseArguments_string_manyArgsWithThreeDefaultValues_success() {
        var arguments = ControlSequenceUtils.parseArguments(";20;foo;;30;");
        assertThat(arguments).containsExactly(null, "20", "foo", null, "30", null);
    }

    @Test
    public void parseArguments_string_manyArgsWithFourDefaultValues_success() {
        var arguments = ControlSequenceUtils.parseArguments(";20;foo;;;30;");
        assertThat(arguments).containsExactly(null, "20", "foo", null, null, "30", null);
    }

}
