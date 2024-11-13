/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

module com.techsenger.ansi4j.css.demo {
    requires com.techsenger.ansi4j.core.api;
    requires com.techsenger.ansi4j.core.impl;
    requires com.techsenger.ansi4j.css.api;
    requires com.techsenger.ansi4j.css.impl;
    requires org.slf4j;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires org.apache.logging.log4j.slf4j2.impl;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;
    requires org.fxmisc.richtext;
    requires org.fxmisc.flowless;
    requires jdk.jsobject;
    requires atlantafx.base;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign2;
    requires com.google.common;

    exports com.techsenger.ansi4j.css.demo;
    opens com.techsenger.ansi4j.css.demo.text to javafx.web;
}
