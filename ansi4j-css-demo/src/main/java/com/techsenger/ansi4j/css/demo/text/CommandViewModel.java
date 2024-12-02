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

import com.techsenger.ansi4j.css.demo.mvvm.ComponentService;
import com.techsenger.ansi4j.css.demo.mvvm.ViewModel;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Pavel Castornii
 */
public class CommandViewModel implements ViewModel {

    public enum ButtonType {
        CLOSE, OK
    }

    private final ObservableList<String> commands = FXCollections.observableArrayList(List.of(
            "git log --color",
            "source-highlight -f esc256 -i pom.xml",
            "mvn help:help -Dstyle.color=always",
            "ls -la / --color"));

    private final StringProperty command = new SimpleStringProperty();

    private final ObjectProperty<Runnable> onClose = new SimpleObjectProperty<>();

    private ButtonType result = ButtonType.CLOSE;

    @Override
    public void setComponentService(ComponentService s) {

    }

    public ObservableList<String> getCommands() {
        return commands;
    }

    public StringProperty commandProperty() {
        return command;
    }

    public ObjectProperty<Runnable> onCloseProperty() {
        return onClose;
    }

    public ButtonType getResult() {
        return result;
    }

    public void setResult(ButtonType result) {
        this.result = result;
    }
}
