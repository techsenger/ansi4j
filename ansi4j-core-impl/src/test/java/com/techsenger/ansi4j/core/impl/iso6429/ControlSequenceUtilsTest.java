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
