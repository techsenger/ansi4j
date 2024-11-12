/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

module com.techsenger.ansi4j.core.api {
    requires org.slf4j;
    requires jsr305;

    exports com.techsenger.ansi4j.core.api;
    exports com.techsenger.ansi4j.core.api.function;
    exports com.techsenger.ansi4j.core.api.iso6429;
    exports com.techsenger.ansi4j.core.api.spi to com.techsenger.ansi4j.core.impl;
    exports com.techsenger.ansi4j.core.api.utils;

    uses com.techsenger.ansi4j.core.api.spi.ParserFactoryService;
}