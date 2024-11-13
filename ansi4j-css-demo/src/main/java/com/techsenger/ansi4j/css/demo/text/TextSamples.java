/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.demo.text;

/**
 *
 * @author Pavel Castornii
 */
final class TextSamples {

    static final String ALL_PARAMETERS = "Single Attribute Samples\n"
            + "This is a ESC[1mtest stringESC[22m for bold\n"
            + "This is a ESC[2mtest stringESC[22m for faint\n"
            + "This is a ESC[3mtest stringESC[23m for italic\n"
            + "This is a ESC[4mtest stringESC[24m for underline\n"
            + "This is a ESC[5mtest stringESC[25m for slow blink\n"
            + "This is a ESC[6mtest stringESC[25m for rapid blink\n"
            + "This is a ESC[7mtest stringESC[27m for reverse video\n"
            + "This is a ESC[8mtest stringESC[28m for hidden\n"
            + "This is a ESC[9mtest stringESC[29m for crossed-out\n"
            + "This is a ESC[10mtest stringESC[0m for primary font\n"
            + "This is a ESC[11mtest stringESC[0m for alternative font 1\n"
            + "This is a ESC[21mtest stringESC[0m for double underline\n"
            + "This is a ESC[30mtest stringESC[39m for foreground color black\n"
            + "This is a ESC[31mtest stringESC[39m for foreground color red\n"
            + "This is a ESC[32mtest stringESC[39m for foreground color green\n"
            + "This is a ESC[33mtest stringESC[39m for foreground color yellow\n"
            + "This is a ESC[34mtest stringESC[39m for foreground color blue\n"
            + "This is a ESC[35mtest stringESC[39m for foreground color magenta\n"
            + "This is a ESC[36mtest stringESC[39m for foreground color cyan\n"
            + "This is a ESC[37mtest stringESC[39m for foreground color white\n"
            + "This is a ESC[38;5;5mtest stringESC[39m for foreground color extended (5)\n"
            + "This is a ESC[38;2;255;0;0mtest stringESC[39m for foreground color RGB (255, 0, 0)\n"
            + "This is a ESC[40mtest stringESC[49m for background color black\n"
            + "This is a ESC[41mtest stringESC[49m for background color red\n"
            + "This is a ESC[42mtest stringESC[49m for background color green\n"
            + "This is a ESC[43mtest stringESC[49m for background color yellow\n"
            + "This is a ESC[44mtest stringESC[49m for background color blue\n"
            + "This is a ESC[45mtest stringESC[49m for background color magenta\n"
            + "This is a ESC[46mtest stringESC[49m for background color cyan\n"
            + "This is a ESC[47mtest stringESC[49m for background color white\n"
            + "This is a ESC[48;5;5mtest stringESC[49m for background color extended (5)\n"
            + "This is a ESC[48;2;0;0;255mtest stringESC[49m for background color RGB (0, 0, 255)\n"
            + "This is a ESC[90mtest stringESC[39m for foreground color bright black\n"
            + "This is a ESC[91mtest stringESC[39m for foreground color bright red\n"
            + "This is a ESC[92mtest stringESC[39m for foreground color bright green\n"
            + "This is a ESC[93mtest stringESC[39m for foreground color bright yellow\n"
            + "This is a ESC[94mtest stringESC[39m for foreground color bright blue\n"
            + "This is a ESC[95mtest stringESC[39m for foreground color bright magenta\n"
            + "This is a ESC[96mtest stringESC[39m for foreground color bright cyan\n"
            + "This is a ESC[97mtest stringESC[39m for foreground color bright white\n"
            + "This is a ESC[100mtest stringESC[49m for background color bright black\n"
            + "This is a ESC[101mtest stringESC[49m for background color bright red\n"
            + "This is a ESC[102mtest stringESC[49m for background color bright green\n"
            + "This is a ESC[103mtest stringESC[49m for background color bright yellow\n"
            + "This is a ESC[104mtest stringESC[49m for background color bright blue\n"
            + "This is a ESC[105mtest stringESC[49m for background color bright magenta\n"
            + "This is a ESC[106mtest stringESC[49m for background color bright cyan\n"
            + "This is a ESC[107mtest stringESC[49m for background color bright white\n\n"
            + "Multiple Attribute Samples\n"
            + "This is a ESC[35mtest ESC[4mstrESC[24mingESC[0m for color and sinle underline\n"
            + "This is a ESC[35mtest ESC[5mstrESC[25mingESC[0m for color and blinking";

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
