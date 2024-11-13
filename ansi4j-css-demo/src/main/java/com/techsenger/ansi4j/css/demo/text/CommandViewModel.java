/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
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
