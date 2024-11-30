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
