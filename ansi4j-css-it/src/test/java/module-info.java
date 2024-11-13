/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

module com.techsenger.ansi4j.css.it {
    requires com.techsenger.ansi4j.core.api;
    requires com.techsenger.ansi4j.core.impl;
    requires com.techsenger.ansi4j.css.api;
    requires com.techsenger.ansi4j.css.impl;
    requires org.slf4j;

    requires org.junit.jupiter.api;
    requires org.junit.jupiter.params;
    requires org.assertj.core;

    opens com.techsenger.ansi4j.css.it;
}
