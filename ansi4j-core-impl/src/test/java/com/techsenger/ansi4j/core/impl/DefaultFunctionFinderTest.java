/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.impl;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeAll;
import com.techsenger.ansi4j.core.api.Environment;
import com.techsenger.ansi4j.core.api.iso6429.C0ControlFunction;
import com.techsenger.ansi4j.core.api.iso6429.C1ControlFunction;
import com.techsenger.ansi4j.core.api.iso6429.ControlFunctionType;
import com.techsenger.ansi4j.core.api.iso6429.IndependentControlFunction;
import com.techsenger.ansi4j.core.api.utils.Characters;

/**
 *
 * @author Pavel Castornii
 */
public class DefaultFunctionFinderTest {

    private static FunctionFinderImpl finder7Bit;

    private static FunctionFinderImpl finder8Bit;

    @BeforeAll
    public static void init() {
        finder7Bit = new FunctionFinderImpl();
        finder7Bit.initialize(Environment._7_BIT);

        finder8Bit = new FunctionFinderImpl();
        finder8Bit.initialize(Environment._8_BIT);
    }

    /* 7 BIT */

    @Test
    public void find_c0Set7BitEsc_success() {
        var result = finder7Bit.find(0, "" + Characters.ESC).get();
        assertThat(result.getFunctionIndex()).isEqualTo(0);
        assertThat(result.getFunctionType()).isSameAs(ControlFunctionType.C0_SET);
        assertThat(result.getFunction()).isSameAs(C0ControlFunction.ESC);
    }

    @Test
    public void find_c0Set7BitVt_success() {
        var result = finder7Bit.find(0, "\u000B").get();
        assertThat(result.getFunctionIndex()).isEqualTo(0);
        assertThat(result.getFunctionType()).isSameAs(ControlFunctionType.C0_SET);
        assertThat(result.getFunction()).isSameAs(C0ControlFunction.VT);
    }

    @Test
    public void find_c1Set7BitNel_success() {
        var result = finder7Bit.find(0, Characters.ESC + "E").get();
        assertThat(result.getFunctionIndex()).isEqualTo(0);
        assertThat(result.getFunctionType()).isSameAs(ControlFunctionType.C1_SET);
        assertThat(result.getFunction()).isSameAs(C1ControlFunction.NEL);
    }

    @Test
    public void find_c1Set7BitCsi_success() {
        var result = finder7Bit.find(0, Characters.ESC + "[").get();
        assertThat(result.getFunctionIndex()).isEqualTo(0);
        assertThat(result.getFunctionType()).isSameAs(ControlFunctionType.CONTROL_SEQUENCE);
        assertThat(result.getFunction()).isSameAs(C1ControlFunction.CSI);
    }

    @Test
    public void find_independent7BitCmd_success() {
        var result = finder7Bit.find(0, "abc" + Characters.ESC + "d").get();
        assertThat(result.getFunctionIndex()).isEqualTo(3);
        assertThat(result.getFunctionType()).isSameAs(ControlFunctionType.INDEPENDENT_FUNCTION);
        assertThat(result.getFunction()).isSameAs(IndependentControlFunction.CMD);
    }

    @Test
    public void find_controlSequence7Bit_success() {
        var result = finder7Bit.find(2, "ab123" + Characters.ESC + "[20m").get();
        assertThat(result.getFunctionIndex()).isEqualTo(5);
        assertThat(result.getFunctionType()).isSameAs(ControlFunctionType.CONTROL_SEQUENCE);
        assertThat(result.getFunction()).isSameAs(C1ControlFunction.CSI);
    }

    @Test
    public void find_controlString7Bit_success() {
        var result = finder7Bit.find(0, Characters.ESC + "^").get();
        assertThat(result.getFunctionIndex()).isEqualTo(0);
        assertThat(result.getFunctionType()).isSameAs(ControlFunctionType.CONTROL_STRING);
        assertThat(result.getFunction()).isSameAs(C1ControlFunction.PM);
    }

    /* 8 BIT */

    @Test
    public void find_c0Set8BitEsc_success() {
        var result = finder8Bit.find(0, "" + Characters.ESC).get();
        assertThat(result.getFunctionIndex()).isEqualTo(0);
        assertThat(result.getFunctionType()).isSameAs(ControlFunctionType.C0_SET);
        assertThat(result.getFunction()).isSameAs(C0ControlFunction.ESC);
    }

    @Test
    public void find_c0Set8BitVt_success() {
        var result = finder8Bit.find(0, "\u000B").get();
        assertThat(result.getFunctionIndex()).isEqualTo(0);
        assertThat(result.getFunctionType()).isSameAs(ControlFunctionType.C0_SET);
        assertThat(result.getFunction()).isSameAs(C0ControlFunction.VT);
    }

    @Test
    public void find_c1Set8BitNel_success() {
        var result = finder8Bit.find(0, "\u0085").get();
        assertThat(result.getFunctionIndex()).isEqualTo(0);
        assertThat(result.getFunctionType()).isSameAs(ControlFunctionType.C1_SET);
        assertThat(result.getFunction()).isSameAs(C1ControlFunction.NEL);
    }

    @Test
    public void find_c1Set8BitCsi_success() {
        var result = finder8Bit.find(0, "\u009b").get();
        assertThat(result.getFunctionIndex()).isEqualTo(0);
        assertThat(result.getFunctionType()).isSameAs(ControlFunctionType.CONTROL_SEQUENCE);
        assertThat(result.getFunction()).isSameAs(C1ControlFunction.CSI);
    }

    @Test
    public void find_independent8BitCmd_success() {
        var result = finder8Bit.find(0, "abc" + Characters.ESC + "d").get();
        assertThat(result.getFunctionIndex()).isEqualTo(3);
        assertThat(result.getFunctionType()).isSameAs(ControlFunctionType.INDEPENDENT_FUNCTION);
        assertThat(result.getFunction()).isSameAs(IndependentControlFunction.CMD);
    }

    @Test
    public void find_controlSequence8Bit_success() {
        var result = finder8Bit.find(2, "ab123\u009b20m").get();
        assertThat(result.getFunctionIndex()).isEqualTo(5);
        assertThat(result.getFunctionType()).isSameAs(ControlFunctionType.CONTROL_SEQUENCE);
        assertThat(result.getFunction()).isSameAs(C1ControlFunction.CSI);
    }

    @Test
    public void find_controlString8Bit_success() {
        var result = finder8Bit.find(0, "\u009e").get();
        assertThat(result.getFunctionIndex()).isEqualTo(0);
        assertThat(result.getFunctionType()).isSameAs(ControlFunctionType.CONTROL_STRING);
        assertThat(result.getFunction()).isSameAs(C1ControlFunction.PM);
    }

}
