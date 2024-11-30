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

package com.techsenger.ansi4j.core.it;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.techsenger.ansi4j.core.api.ParserFactory;
import com.techsenger.ansi4j.core.api.Fragment;
import com.techsenger.ansi4j.core.api.TextFragment;
import com.techsenger.ansi4j.core.api.utils.Characters;
import com.techsenger.ansi4j.core.api.FragmentType;
import com.techsenger.ansi4j.core.api.FunctionFragment;
import com.techsenger.ansi4j.core.api.iso6429.ControlSequenceFunction;
import com.techsenger.ansi4j.core.api.Environment;
import com.techsenger.ansi4j.core.api.iso6429.C0ControlFunction;
import com.techsenger.ansi4j.core.api.iso6429.C1ControlFunction;
import com.techsenger.ansi4j.core.api.iso6429.IndependentControlFunction;
import com.techsenger.ansi4j.core.api.Parser;
import com.techsenger.ansi4j.core.api.StreamParser;
import com.techsenger.ansi4j.core.api.iso6429.ControlFunctionType;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Pavel Castornii
 */
public class ParserIT {

    private static final Logger logger = LoggerFactory.getLogger(ParserIT.class);

    private static final String _7_BIT_PARSER_PROVIDER = "provide7BitParsers";

    private static final String _8_BIT_PARSER_PROVIDER = "provide8BitParsers";

    @FunctionalInterface
    private interface ParserProvider {

        Parser provide(String text);
    }

    private static ParserFactory factory7Bit;

    private static ParserFactory factory8Bit;

    @BeforeAll
    public static void init() {
        factory7Bit = new ParserFactory.Builder()
            .environment(Environment._7_BIT)
            .functionTypes(
                    ControlFunctionType.C0_SET,
                    ControlFunctionType.C1_SET,
                    ControlFunctionType.CONTROL_SEQUENCE,
                    ControlFunctionType.INDEPENDENT_FUNCTION,
                    ControlFunctionType.CONTROL_STRING)
            .build();

        factory8Bit = new ParserFactory.Builder()
            .environment(Environment._8_BIT)
            .functionTypes(
                    ControlFunctionType.C0_SET,
                    ControlFunctionType.C1_SET,
                    ControlFunctionType.CONTROL_SEQUENCE,
                    ControlFunctionType.INDEPENDENT_FUNCTION,
                    ControlFunctionType.CONTROL_STRING)
            .build();
    }

    protected static List<ParserProvider> provide7BitParsers() {
        return List.of((text) -> factory7Bit.createParser(text),
                        (text) -> factory7Bit.createParser(
                                new ByteArrayInputStream(text.getBytes()),
                                StandardCharsets.UTF_8,
                                1024));
    }

    protected static List<ParserProvider> provide8BitParsers() {
        return List.of((text) -> factory8Bit.createParser(text),
                        (text) -> factory8Bit.createParser(
                                new ByteArrayInputStream(text.getBytes()),
                                StandardCharsets.UTF_8,
                                1024));
    }

    @ParameterizedTest
    @MethodSource(_7_BIT_PARSER_PROVIDER)
    public void parse_textFnTextFn_success(ParserProvider parserProvider) {
        var text = "2022-03-14 02:32:24.130 [main] \u001b[33;1m[WARN]\u001b[5;R abc.def.0123.ghi";
        var parser = parserProvider.provide(text);
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment = null;
        while ((fragment = parser.parse()) != null) {
            fragments.add(fragment);
        }
        assertThat(fragments).hasSize(5);

        TextFragment f0 = (TextFragment) fragments.get(0);
        assertThat(f0.getType()).isSameAs(FragmentType.TEXT);
        var f0Text = "2022-03-14 02:32:24.130 [main] ";
        assertThat(f0.getText()).isEqualTo(f0Text);
        assertThat(text.substring(f0.getStartIndex(), f0.getEndIndex())).isEqualTo(f0Text);

        FunctionFragment f1 = (FunctionFragment) fragments.get(1);
        this.checkMFunctionFragment(text, f1);

        TextFragment f2 = (TextFragment) fragments.get(2);
        assertThat(f2.getType()).isSameAs(FragmentType.TEXT);
        var f2Text = "[WARN]";
        assertThat(f2.getText()).isEqualTo(f2Text);
        assertThat(text.substring(f2.getStartIndex(), f2.getEndIndex())).isEqualTo(f2Text);

        FunctionFragment f3 = (FunctionFragment) fragments.get(3);
        this.checkRFunctionFragment(text, f3);

        TextFragment f4 = (TextFragment) fragments.get(4);
        assertThat(f4.getType()).isSameAs(FragmentType.TEXT);
        var f4Text = " abc.def.0123.ghi";
        assertThat(f4.getText()).isEqualTo(f4Text);
        assertThat(text.substring(f4.getStartIndex(), f4.getEndIndex())).isEqualTo(f4Text);
        this.closeParser(parser);
    }


    @ParameterizedTest
    @MethodSource(_7_BIT_PARSER_PROVIDER)
    public void parse_FnTextFn_success(ParserProvider parserProvider) {
        var text = "\u001b[33;1m2022-03-14 02:32:24.130 [main] [WARN] abc.def.0123.ghi\u001b[5;R";
        var parser = parserProvider.provide(text);
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment = null;
        while ((fragment = parser.parse()) != null) {
            fragments.add(fragment);
        }
        assertThat(fragments).hasSize(3);

        FunctionFragment f0 = (FunctionFragment) fragments.get(0);
        this.checkMFunctionFragment(text, f0);

        TextFragment f1 = (TextFragment) fragments.get(1);
        assertThat(f1.getType()).isSameAs(FragmentType.TEXT);
        var f1Text = "2022-03-14 02:32:24.130 [main] [WARN] abc.def.0123.ghi";
        assertThat(f1.getText()).isEqualTo(f1Text);
        assertThat(text.substring(f1.getStartIndex(), f1.getEndIndex())).isEqualTo(f1Text);

        FunctionFragment f2 = (FunctionFragment) fragments.get(2);
        this.checkRFunctionFragment(text, f2);
        this.closeParser(parser);
    }

    @ParameterizedTest
    @MethodSource(_7_BIT_PARSER_PROVIDER)
    public void parse_FnFnText_success(ParserProvider parserProvider) {
        var text = "\u001b[33;1m\u001b[5;R2022-03-14 02:32:24.130 [main] [WARN] abc.def.0123.ghi";
        var parser = parserProvider.provide(text);
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment = null;
        while ((fragment = parser.parse()) != null) {
            fragments.add(fragment);
        }
        assertThat(fragments).hasSize(3);

        FunctionFragment f0 = (FunctionFragment) fragments.get(0);
        this.checkMFunctionFragment(text, f0);

        FunctionFragment f1 = (FunctionFragment) fragments.get(1);
        this.checkRFunctionFragment(text, f1);

        TextFragment f2 = (TextFragment) fragments.get(2);
        assertThat(f2.getType()).isSameAs(FragmentType.TEXT);
        var f2Text = "2022-03-14 02:32:24.130 [main] [WARN] abc.def.0123.ghi";
        assertThat(f2.getText()).isEqualTo(f2Text);
        assertThat(text.substring(f2.getStartIndex(), f2.getEndIndex())).isEqualTo(f2Text);
        this.closeParser(parser);
    }

    @ParameterizedTest
    @MethodSource(_7_BIT_PARSER_PROVIDER)
    public void parse_textFnFn_success(ParserProvider parserProvider) {
        var text = "2022-03-14 02:32:24.130 [main] [WARN] abc.def.0123.ghi\u001b[33;1m\u001b[5;R";

        var parser = parserProvider.provide(text);
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment = null;
        while ((fragment = parser.parse()) != null) {
            fragments.add(fragment);
        }
        assertThat(fragments).hasSize(3);

        TextFragment f0 = (TextFragment) fragments.get(0);
        assertThat(f0.getType()).isSameAs(FragmentType.TEXT);
        var f0Text = "2022-03-14 02:32:24.130 [main] [WARN] abc.def.0123.ghi";
        assertThat(f0.getText()).isEqualTo(f0Text);
        assertThat(text.substring(f0.getStartIndex(), f0.getEndIndex())).isEqualTo(f0Text);

        FunctionFragment f1 = (FunctionFragment) fragments.get(1);
        this.checkMFunctionFragment(text, f1);

        FunctionFragment f2 = (FunctionFragment) fragments.get(2);
        this.checkRFunctionFragment(text, f2);
        this.closeParser(parser);
    }

    @ParameterizedTest
    @MethodSource(_7_BIT_PARSER_PROVIDER)
    public void parse_defaultArgument_success(ParserProvider parserProvider) {
        var text = "\u001b[m";
        var parser = parserProvider.provide(text);
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment = null;
        while ((fragment = parser.parse()) != null) {
            fragments.add(fragment);
        }
        assertThat(fragments).hasSize(1);
        var f0 = (FunctionFragment) fragments.get(0);
        assertThat(f0.getArguments()).isNotNull();
        assertThat(f0.getArguments()).hasSize(1);
        assertThat(f0.getArguments().get(0).getValue()).isEqualTo(0);
        assertThat(f0.getArguments().get(0).isDefault()).isEqualTo(true);
        this.closeParser(parser);
    }

    @ParameterizedTest
    @MethodSource(_7_BIT_PARSER_PROVIDER)
    public void parse_c0In7bitEnv_success(ParserProvider parserProvider) {
        var text = "2022-03-14 02:32:24.130 \u000B[main] \u000e [WARN] \u000f abc.def.0123.ghi";
        var parser = parserProvider.provide(text);
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment = null;
        while ((fragment = parser.parse()) != null) {
            fragments.add(fragment);
        }
        assertThat(fragments).hasSize(7);

        TextFragment ft = (TextFragment) fragments.get(0);
        assertThat(ft.getText()).isEqualTo("2022-03-14 02:32:24.130 ");

        FunctionFragment ff = (FunctionFragment) fragments.get(1);
        assertThat(ff.getFunction()).isSameAs(C0ControlFunction.VT);
        assertThat(ff.getArguments()).hasSize(0);
        assertThat(ff.getText()).isEqualTo("\u000B");

        ft = (TextFragment) fragments.get(2);
        assertThat(ft.getText()).isEqualTo("[main] ");

        ff = (FunctionFragment) fragments.get(3);
        assertThat(ff.getFunction()).isSameAs(C0ControlFunction.SO);
        assertThat(ff.getArguments()).hasSize(0);
        assertThat(ff.getText()).isEqualTo("\u000e");

        ft = (TextFragment) fragments.get(4);
        assertThat(ft.getText()).isEqualTo(" [WARN] ");

        ff = (FunctionFragment) fragments.get(5);
        assertThat(ff.getFunction()).isSameAs(C0ControlFunction.SI);
        assertThat(ff.getArguments()).hasSize(0);
        assertThat(ff.getText()).isEqualTo("\u000f");

        ft = (TextFragment) fragments.get(6);
        assertThat(ft.getText()).isEqualTo(" abc.def.0123.ghi");
        this.closeParser(parser);
    }

    @ParameterizedTest
    @MethodSource(_8_BIT_PARSER_PROVIDER)
    public void parse_c0In8bitEnv_success(ParserProvider parserProvider) {
        var text = "2022-03-14 02:32:24.130 \u000B[main] \u000e [WARN] \u000f abc.def.0123.ghi";
        var parser = parserProvider.provide(text);
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment = null;
        while ((fragment = parser.parse()) != null) {
            fragments.add(fragment);
        }
        assertThat(fragments).hasSize(7);

        TextFragment ft = (TextFragment) fragments.get(0);
        assertThat(ft.getText()).isEqualTo("2022-03-14 02:32:24.130 ");

        FunctionFragment ff = (FunctionFragment) fragments.get(1);
        assertThat(ff.getFunction()).isSameAs(C0ControlFunction.VT);
        assertThat(ff.getArguments()).hasSize(0);
        assertThat(ff.getText()).isEqualTo("\u000B");

        ft = (TextFragment) fragments.get(2);
        assertThat(ft.getText()).isEqualTo("[main] ");

        ff = (FunctionFragment) fragments.get(3);
        assertThat(ff.getFunction()).isSameAs(C0ControlFunction.LS1);
        assertThat(ff.getArguments()).hasSize(0);
        assertThat(ff.getText()).isEqualTo("\u000e");

        ft = (TextFragment) fragments.get(4);
        assertThat(ft.getText()).isEqualTo(" [WARN] ");

        ff = (FunctionFragment) fragments.get(5);
        assertThat(ff.getFunction()).isSameAs(C0ControlFunction.LS0);
        assertThat(ff.getArguments()).hasSize(0);
        assertThat(ff.getText()).isEqualTo("\u000f");

        ft = (TextFragment) fragments.get(6);
        assertThat(ft.getText()).isEqualTo(" abc.def.0123.ghi");
        this.closeParser(parser);
    }

    @ParameterizedTest
    @MethodSource(_7_BIT_PARSER_PROVIDER)
    public void parse_c1In7bitEnv_success(ParserProvider parserProvider) {
        var text = "2022-03-14 02:32:24.130 \u001bG[main] \u001bV [WARN] \u001bQ abc.def.0123.ghi";
        var parser = parserProvider.provide(text);
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment = null;
        while ((fragment = parser.parse()) != null) {
            fragments.add(fragment);
        }
        assertThat(fragments).hasSize(7);

        TextFragment ft = (TextFragment) fragments.get(0);
        assertThat(ft.getText()).isEqualTo("2022-03-14 02:32:24.130 ");

        FunctionFragment ff = (FunctionFragment) fragments.get(1);
        assertThat(ff.getFunction()).isSameAs(C1ControlFunction.ESA);
        assertThat(ff.getArguments()).hasSize(0);
        assertThat(ff.getText()).isEqualTo("\u001bG");

        ft = (TextFragment) fragments.get(2);
        assertThat(ft.getText()).isEqualTo("[main] ");

        ff = (FunctionFragment) fragments.get(3);
        assertThat(ff.getFunction()).isSameAs(C1ControlFunction.SPA);
        assertThat(ff.getArguments()).hasSize(0);
        assertThat(ff.getText()).isEqualTo("\u001bV");

        ft = (TextFragment) fragments.get(4);
        assertThat(ft.getText()).isEqualTo(" [WARN] ");

        ff = (FunctionFragment) fragments.get(5);
        assertThat(ff.getFunction()).isSameAs(C1ControlFunction.PU1);
        assertThat(ff.getArguments()).hasSize(0);
        assertThat(ff.getText()).isEqualTo("\u001bQ");

        ft = (TextFragment) fragments.get(6);
        assertThat(ft.getText()).isEqualTo(" abc.def.0123.ghi");
        this.closeParser(parser);
    }

    @ParameterizedTest
    @MethodSource(_8_BIT_PARSER_PROVIDER)
    public void parse_c1In8bitEnv_success(ParserProvider parserProvider) {
        var text = "2022-03-14 02:32:24.130 \u0087[main] \u0096 [WARN] \u0091 abc.def.0123.ghi";
        var parser = parserProvider.provide(text);
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment = null;
        while ((fragment = parser.parse()) != null) {
            fragments.add(fragment);
        }
        assertThat(fragments).hasSize(7);

        TextFragment ft = (TextFragment) fragments.get(0);
        assertThat(ft.getText()).isEqualTo("2022-03-14 02:32:24.130 ");

        FunctionFragment ff = (FunctionFragment) fragments.get(1);
        assertThat(ff.getFunction()).isSameAs(C1ControlFunction.ESA);
        assertThat(ff.getArguments()).hasSize(0);
        assertThat(ff.getText()).isEqualTo("\u0087");

        ft = (TextFragment) fragments.get(2);
        assertThat(ft.getText()).isEqualTo("[main] ");

        ff = (FunctionFragment) fragments.get(3);
        assertThat(ff.getFunction()).isSameAs(C1ControlFunction.SPA);
        assertThat(ff.getArguments()).hasSize(0);
        assertThat(ff.getText()).isEqualTo("\u0096");

        ft = (TextFragment) fragments.get(4);
        assertThat(ft.getText()).isEqualTo(" [WARN] ");

        ff = (FunctionFragment) fragments.get(5);
        assertThat(ff.getFunction()).isSameAs(C1ControlFunction.PU1);
        assertThat(ff.getArguments()).hasSize(0);
        assertThat(ff.getText()).isEqualTo("\u0091");

        ft = (TextFragment) fragments.get(6);
        assertThat(ft.getText()).isEqualTo(" abc.def.0123.ghi");
        this.closeParser(parser);
    }

    @ParameterizedTest
    @MethodSource(_7_BIT_PARSER_PROVIDER)
    public void parse_independentIn7bitEnv_success(ParserProvider parserProvider) {
         var text = "2022-03-14 02:32:24.130 \u001bo[main] \u001b| [WARN] \u001b` abc.def.0123.ghi";
        var parser = parserProvider.provide(text);
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment = null;
        while ((fragment = parser.parse()) != null) {
            fragments.add(fragment);
        }
        assertThat(fragments).hasSize(7);

        TextFragment ft = (TextFragment) fragments.get(0);
        assertThat(ft.getText()).isEqualTo("2022-03-14 02:32:24.130 ");

        FunctionFragment ff = (FunctionFragment) fragments.get(1);
        assertThat(ff.getFunction()).isSameAs(IndependentControlFunction.LS3);
        assertThat(ff.getArguments()).hasSize(0);
        assertThat(ff.getText()).isEqualTo("\u001bo");

        ft = (TextFragment) fragments.get(2);
        assertThat(ft.getText()).isEqualTo("[main] ");

        ff = (FunctionFragment) fragments.get(3);
        assertThat(ff.getFunction()).isSameAs(IndependentControlFunction.LS3R);
        assertThat(ff.getArguments()).hasSize(0);
        assertThat(ff.getText()).isEqualTo("\u001b|");

        ft = (TextFragment) fragments.get(4);
        assertThat(ft.getText()).isEqualTo(" [WARN] ");

        ff = (FunctionFragment) fragments.get(5);
        assertThat(ff.getFunction()).isSameAs(IndependentControlFunction.DMI);
        assertThat(ff.getArguments()).hasSize(0);
        assertThat(ff.getText()).isEqualTo("\u001b`");

        ft = (TextFragment) fragments.get(6);
        assertThat(ft.getText()).isEqualTo(" abc.def.0123.ghi");
        this.closeParser(parser);
    }

    @ParameterizedTest
    @MethodSource(_8_BIT_PARSER_PROVIDER)
    public void parse_independentIn8bitEnv_success(ParserProvider parserProvider) {
         var text = "2022-03-14 02:32:24.130 \u001bo[main] \u001b| [WARN] \u001b` abc.def.0123.ghi";
        var parser = parserProvider.provide(text);
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment = null;
        while ((fragment = parser.parse()) != null) {
            fragments.add(fragment);
        }
        assertThat(fragments).hasSize(7);

        TextFragment ft = (TextFragment) fragments.get(0);
        assertThat(ft.getText()).isEqualTo("2022-03-14 02:32:24.130 ");

        FunctionFragment ff = (FunctionFragment) fragments.get(1);
        assertThat(ff.getFunction()).isSameAs(IndependentControlFunction.LS3);
        assertThat(ff.getArguments()).hasSize(0);
        assertThat(ff.getText()).isEqualTo("\u001bo");

        ft = (TextFragment) fragments.get(2);
        assertThat(ft.getText()).isEqualTo("[main] ");

        ff = (FunctionFragment) fragments.get(3);
        assertThat(ff.getFunction()).isSameAs(IndependentControlFunction.LS3R);
        assertThat(ff.getArguments()).hasSize(0);
        assertThat(ff.getText()).isEqualTo("\u001b|");

        ft = (TextFragment) fragments.get(4);
        assertThat(ft.getText()).isEqualTo(" [WARN] ");

        ff = (FunctionFragment) fragments.get(5);
        assertThat(ff.getFunction()).isSameAs(IndependentControlFunction.DMI);
        assertThat(ff.getArguments()).hasSize(0);
        assertThat(ff.getText()).isEqualTo("\u001b`");

        ft = (TextFragment) fragments.get(6);
        assertThat(ft.getText()).isEqualTo(" abc.def.0123.ghi");
        this.closeParser(parser);
    }

    @ParameterizedTest
    @MethodSource(_7_BIT_PARSER_PROVIDER)
    public void parse_controlStringIn7bitEnv_success(ParserProvider parserProvider) {
         var text = "one two three \u001b]4;6;some text\u001b\\ abc.def.0123.ghi";
        var parser = parserProvider.provide(text);
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment = null;
        while ((fragment = parser.parse()) != null) {
            fragments.add(fragment);
        }
        assertThat(fragments).hasSize(3);

        TextFragment ft = (TextFragment) fragments.get(0);
        assertThat(ft.getText()).isEqualTo("one two three ");

        FunctionFragment ff = (FunctionFragment) fragments.get(1);
        assertThat(ff.getFunction()).isSameAs(C1ControlFunction.OSC);
        assertThat(ff.getArguments()).hasSize(3);
        assertThat(ff.getText()).isEqualTo("\u001b]4;6;some text\u001b\\");

        ft = (TextFragment) fragments.get(2);
        assertThat(ft.getText()).isEqualTo(" abc.def.0123.ghi");
        this.closeParser(parser);
    }

    @ParameterizedTest
    @MethodSource(_8_BIT_PARSER_PROVIDER)
    public void parse_controlStringIn8bitEnv_success(ParserProvider parserProvider) {
         var text = "one two three \u009d4;6;some text\u009c abc.def.0123.ghi";
        var parser = parserProvider.provide(text);
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment = null;
        while ((fragment = parser.parse()) != null) {
            fragments.add(fragment);
        }
        assertThat(fragments).hasSize(3);

        TextFragment ft = (TextFragment) fragments.get(0);
        assertThat(ft.getText()).isEqualTo("one two three ");

        FunctionFragment ff = (FunctionFragment) fragments.get(1);
        assertThat(ff.getFunction()).isSameAs(C1ControlFunction.OSC);
        assertThat(ff.getArguments()).hasSize(3);
        assertThat(ff.getText()).isEqualTo("\u009d4;6;some text\u009c");

        ft = (TextFragment) fragments.get(2);
        assertThat(ft.getText()).isEqualTo(" abc.def.0123.ghi");
        this.closeParser(parser);
    }

    @Test
    public void parse_controlStringIn7bitEnvAsStream_success() {
        var text = "one two three \u001b]4;6;some text\u001b\\ abc.def.0123.ghi";
        var parser = factory7Bit.createParser(new ByteArrayInputStream(text.getBytes()), StandardCharsets.UTF_8, 6);
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment = null;
        while ((fragment = parser.parse()) != null) {
            fragments.add(fragment);
        }
        assertThat(fragments).hasSize(7);

        TextFragment f0 = (TextFragment) fragments.get(0);
        assertThat(f0.getText()).isEqualTo("one tw");
        assertThat(text.substring(f0.getStartIndex(), f0.getEndIndex())).isEqualTo(f0.getText());

        TextFragment f1 = (TextFragment) fragments.get(1);
        assertThat(f1.getText()).isEqualTo("o thre");
        assertThat(text.substring(f1.getStartIndex(), f1.getEndIndex())).isEqualTo(f1.getText());

        TextFragment f2 = (TextFragment) fragments.get(2);
        assertThat(f2.getText()).isEqualTo("e ");
        assertThat(text.substring(f2.getStartIndex(), f2.getEndIndex())).isEqualTo(f2.getText());

        FunctionFragment f3 = (FunctionFragment) fragments.get(3);
        assertThat(f3.getFunction()).isSameAs(C1ControlFunction.OSC);
        assertThat(f3.getArguments()).hasSize(3);
        assertThat(f3.getText()).isEqualTo("\u001b]4;6;some text\u001b\\");
        assertThat(text.substring(f3.getStartIndex(), f3.getEndIndex())).isEqualTo(f3.getText());

        TextFragment f4 = (TextFragment) fragments.get(4);
        assertThat(f4.getText()).isEqualTo(" abc."); // "\\" escaping character is used
        assertThat(text.substring(f4.getStartIndex(), f4.getEndIndex())).isEqualTo(f4.getText());

        TextFragment f5 = (TextFragment) fragments.get(5);
        assertThat(f5.getText()).isEqualTo("def.01");
        assertThat(text.substring(f5.getStartIndex(), f5.getEndIndex())).isEqualTo(f5.getText());

        TextFragment f6 = (TextFragment) fragments.get(6);
        assertThat(f6.getText()).isEqualTo("23.ghi");
        assertThat(text.substring(f6.getStartIndex(), f6.getEndIndex())).isEqualTo(f6.getText());
        this.closeParser(parser);
    }

    @Test
    public void parse_controlStringIn8bitEnvAsStream_success() {
         var text = "one two three \u009d4;6;some text\u009c abc.def.0123.ghi";
        var parser = factory8Bit.createParser(new ByteArrayInputStream(text.getBytes()), StandardCharsets.UTF_8, 6);
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment = null;
        while ((fragment = parser.parse()) != null) {
            fragments.add(fragment);
        }
        assertThat(fragments).hasSize(8);

        TextFragment f0 = (TextFragment) fragments.get(0);
        assertThat(f0.getText()).isEqualTo("one tw");
        assertThat(text.substring(f0.getStartIndex(), f0.getEndIndex())).isEqualTo(f0.getText());

        TextFragment f1 = (TextFragment) fragments.get(1);
        assertThat(f1.getText()).isEqualTo("o thre");
        assertThat(text.substring(f1.getStartIndex(), f1.getEndIndex())).isEqualTo(f1.getText());

        TextFragment f2 = (TextFragment) fragments.get(2);
        assertThat(f2.getText()).isEqualTo("e ");
        assertThat(text.substring(f2.getStartIndex(), f2.getEndIndex())).isEqualTo(f2.getText());

        FunctionFragment f3 = (FunctionFragment) fragments.get(3);
        assertThat(f3.getFunction()).isSameAs(C1ControlFunction.OSC);
        assertThat(f3.getArguments()).hasSize(3);
        assertThat(f3.getText()).isEqualTo("\u009d4;6;some text\u009c");
        assertThat(text.substring(f3.getStartIndex(), f3.getEndIndex())).isEqualTo(f3.getText());

        TextFragment f4 = (TextFragment) fragments.get(4);
        assertThat(f4.getText()).isEqualTo(" "); // "\\" escaping character is used
        assertThat(text.substring(f4.getStartIndex(), f4.getEndIndex())).isEqualTo(f4.getText());

        TextFragment f5 = (TextFragment) fragments.get(5);
        assertThat(f5.getText()).isEqualTo("abc.de");
        assertThat(text.substring(f5.getStartIndex(), f5.getEndIndex())).isEqualTo(f5.getText());

        TextFragment f6 = (TextFragment) fragments.get(6);
        assertThat(f6.getText()).isEqualTo("f.0123");
        assertThat(text.substring(f6.getStartIndex(), f6.getEndIndex())).isEqualTo(f6.getText());

        TextFragment f7 = (TextFragment) fragments.get(7);
        assertThat(f7.getText()).isEqualTo(".ghi");
        assertThat(text.substring(f7.getStartIndex(), f7.getEndIndex())).isEqualTo(f7.getText());
        this.closeParser(parser);
    }

    private void checkMFunctionFragment(String text, FunctionFragment mFragment) {
        assertThat(mFragment.getType()).isSameAs(FragmentType.FUNCTION);
        var mFragmentText = Characters.ESC + "[33;1m";
        assertThat(mFragment.getText()).isEqualTo(mFragmentText);
        assertThat(text.substring(mFragment.getStartIndex(), mFragment.getEndIndex())).isEqualTo(mFragmentText);
        assertThat(mFragment.getFunction()).isSameAs(ControlSequenceFunction.SGR);
        assertThat(mFragment.getArguments().size()).isEqualTo(2);
        assertThat(mFragment.getArguments().get(0).getValue()).isEqualTo(33);
        assertThat(mFragment.getArguments().get(0).isDefault()).isEqualTo(false);
        assertThat(mFragment.getArguments().get(1).getValue()).isEqualTo(1);
        assertThat(mFragment.getArguments().get(1).isDefault()).isEqualTo(false);
    }

    private void checkRFunctionFragment(String text, FunctionFragment rFragment) {
        assertThat(rFragment.getType()).isSameAs(FragmentType.FUNCTION);
        var rFragmentText = Characters.ESC + "[5;R";
        assertThat(rFragment.getText()).isEqualTo(rFragmentText);
        assertThat(text.substring(rFragment.getStartIndex(), rFragment.getEndIndex())).isEqualTo(rFragmentText);
        assertThat(rFragment.getFunction()).isSameAs(ControlSequenceFunction.CPR);
        assertThat(rFragment.getArguments().size()).isEqualTo(2);
        assertThat(rFragment.getArguments().get(0).getValue()).isEqualTo(5);
        assertThat(rFragment.getArguments().get(0).isDefault()).isEqualTo(false);
        assertThat(rFragment.getArguments().get(1).getValue()).isEqualTo(1);
        assertThat(rFragment.getArguments().get(1).isDefault()).isEqualTo(true);
    }

    private void closeParser(Parser parser) {
        if (parser instanceof StreamParser) {
            var streamParser = (StreamParser) parser;
            try {
                streamParser.close();
            } catch (IOException ex) {
                logger.error("Error closing stream parser", ex);
            }
        }
    }
}


