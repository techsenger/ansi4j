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

package com.techsenger.ansi4j.css.demo.text;

/**
 *
 * @author Pavel Castornii
 */
final class TextSamples {

    static final String ALL_PARAMETERS = "Single Attribute Samples\n"
            + "This is a ESC[1msample stringESC[22m for bold\n"
            + "This is a ESC[2msample stringESC[22m for faint\n"
            + "This is a ESC[3msample stringESC[23m for italic\n"
            + "This is a ESC[4msample stringESC[24m for underline\n"
            + "This is a ESC[5msample stringESC[25m for slow blink\n"
            + "This is a ESC[6msample stringESC[25m for rapid blink\n"
            + "This is a ESC[7msample stringESC[27m for reverse video\n"
            + "This is a ESC[8msample stringESC[28m for hidden\n"
            + "This is a ESC[9msample stringESC[29m for crossed-out\n"
            + "This is a ESC[10msample stringESC[0m for primary font\n"
            + "This is a ESC[11msample stringESC[0m for alternative font 1\n"
            + "This is a ESC[21msample stringESC[0m for double underline\n"
            + "This is a ESC[30msample stringESC[39m for foreground color black\n"
            + "This is a ESC[31msample stringESC[39m for foreground color red\n"
            + "This is a ESC[32msample stringESC[39m for foreground color green\n"
            + "This is a ESC[33msample stringESC[39m for foreground color yellow\n"
            + "This is a ESC[34msample stringESC[39m for foreground color blue\n"
            + "This is a ESC[35msample stringESC[39m for foreground color magenta\n"
            + "This is a ESC[36msample stringESC[39m for foreground color cyan\n"
            + "This is a ESC[37msample stringESC[39m for foreground color white\n"
            + "This is a ESC[38;5;5msample stringESC[39m for foreground color extended (5)\n"
            + "This is a ESC[38;2;255;0;0msample stringESC[39m for foreground color RGB (255, 0, 0)\n"
            + "This is a ESC[40msample stringESC[49m for background color black\n"
            + "This is a ESC[41msample stringESC[49m for background color red\n"
            + "This is a ESC[42msample stringESC[49m for background color green\n"
            + "This is a ESC[43msample stringESC[49m for background color yellow\n"
            + "This is a ESC[44msample stringESC[49m for background color blue\n"
            + "This is a ESC[45msample stringESC[49m for background color magenta\n"
            + "This is a ESC[46msample stringESC[49m for background color cyan\n"
            + "This is a ESC[47msample stringESC[49m for background color white\n"
            + "This is a ESC[48;5;5msample stringESC[49m for background color extended (5)\n"
            + "This is a ESC[48;2;0;0;255msample stringESC[49m for background color RGB (0, 0, 255)\n"
            + "This is a ESC[90msample stringESC[39m for foreground color bright black\n"
            + "This is a ESC[91msample stringESC[39m for foreground color bright red\n"
            + "This is a ESC[92msample stringESC[39m for foreground color bright green\n"
            + "This is a ESC[93msample stringESC[39m for foreground color bright yellow\n"
            + "This is a ESC[94msample stringESC[39m for foreground color bright blue\n"
            + "This is a ESC[95msample stringESC[39m for foreground color bright magenta\n"
            + "This is a ESC[96msample stringESC[39m for foreground color bright cyan\n"
            + "This is a ESC[97msample stringESC[39m for foreground color bright white\n"
            + "This is a ESC[100msample stringESC[49m for background color bright black\n"
            + "This is a ESC[101msample stringESC[49m for background color bright red\n"
            + "This is a ESC[102msample stringESC[49m for background color bright green\n"
            + "This is a ESC[103msample stringESC[49m for background color bright yellow\n"
            + "This is a ESC[104msample stringESC[49m for background color bright blue\n"
            + "This is a ESC[105msample stringESC[49m for background color bright magenta\n"
            + "This is a ESC[106msample stringESC[49m for background color bright cyan\n"
            + "This is a ESC[107msample stringESC[49m for background color bright white\n\n"
            + "Multiple Attribute Samples\n"
            + "This is a ESC[35msample ESC[4mstrESC[24mingESC[0m for color and sinle underline\n"
            + "This is a ESC[35msample ESC[5mstrESC[25mingESC[0m for color and blinking";

    static final String LOG_MESSAGES = "2022-03-26 22:51:28.753 [main] ESC[35;1m[INFO]ESC[m "
                    + "org.springframework.context.support"
                    + ".PostProcessorRegistrationDelegate$BeanPostProcessorChecker - Bean 'subjectDAO' of type "
                    + "[org.apache.shiro.mgt.DefaultSubjectDAO] is not eligible for getting processed by all "
                    + "BeanPostProcessors (for example: not eligible for auto-proxying)\n"
            + "2022-03-26 22:51:28.753 [main] ESC[32m[DEBUG]ESC[m org.springframework.beans.factory.support"
                    + ".DefaultListableBeanFactory - Creating shared instance of singleton bean 'subjectFactory'\n"
            + "2022-03-26 22:51:28.754 [main] ESC[35;1m[INFO]ESC[m org.springframework.context.support."
                    + "PostProcessorRegistrationDelegate$BeanPostProcessorChecker - Bean 'subjectFactory' of type "
                    + "[org.apache.shiro.mgt.DefaultSubjectFactory] is not eligible for getting processed by all "
                    + "BeanPostProcessors (for example: not eligible for auto-proxying)\n"
            + "2022-03-26 22:51:28.754 [main] ESC[32m[DEBUG]ESC[m org.springframework.beans.factory.support."
                    + "DefaultListableBeanFactory - Creating shared instance of singleton bean "
                    + "'rememberMeManager'\n"
            + "2022-03-26 22:51:28.755 [main] ESC[35;1m[INFO]ESC[m org.springframework.context.support"
                    + ".PostProcessorRegistrationDelegate$BeanPostProcessorChecker - Bean 'rememberMeManager' "
                    + "of type [org.springframework.beans.factory.support.NullBean] is not eligible for getting "
                    + "processed by all BeanPostProcessors (for example: not eligible for auto-proxying)\n"
            + "2022-03-26 22:51:28.755 [main] ESC[32m[DEBUG]ESC[m org.springframework.context.annotation"
                    + ".ConfigurationClassEnhancer - @Bean method ShiroConfiguration.rememberMeManager called as "
                    + "bean reference for type [org.apache.shiro.mgt.RememberMeManager] returned null bean; "
                    + "resolving to null value.";

    private TextSamples() {

    }
}
