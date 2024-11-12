/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

module com.techsenger.ansi4j.core.impl {
    requires com.techsenger.ansi4j.core.api;
    requires org.slf4j;

    provides com.techsenger.ansi4j.core.api.spi.ParserFactoryService
            with com.techsenger.ansi4j.core.impl.ParserFactoryProvider;
}
