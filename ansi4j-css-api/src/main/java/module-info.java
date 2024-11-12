/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

module com.techsenger.ansi4j.css.api {
    requires com.techsenger.ansi4j.core.api;
    requires org.slf4j;
    requires jsr305;

    exports com.techsenger.ansi4j.css.api;
    exports com.techsenger.ansi4j.css.api.attribute;
    exports com.techsenger.ansi4j.css.api.color;
    exports com.techsenger.ansi4j.css.api.spi to com.techsenger.ansi4j.css.impl;
    exports com.techsenger.ansi4j.css.api.text;

    uses com.techsenger.ansi4j.css.api.spi.StyleProcessorService;
}