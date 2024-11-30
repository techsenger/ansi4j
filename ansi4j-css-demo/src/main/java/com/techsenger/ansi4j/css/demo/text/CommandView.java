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

package com.techsenger.ansi4j.css.demo.text;

import com.techsenger.ansi4j.css.demo.material.Dialog;
import com.techsenger.ansi4j.css.demo.mvvm.View;
import com.techsenger.ansi4j.css.demo.utils.FxUtils;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Pavel Castornii
 */
public class CommandView implements View<CommandViewModel> {

    private final CommandViewModel viewModel;

    private final Dialog dialog = new Dialog("Command Input", 400, 10);

    private final ComboBox<String> comboBox = new ComboBox<>();

    private final Button okButton = new Button("OK");

    public CommandView(CommandViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.commandProperty().bind(this.comboBox.getEditor().textProperty());
        this.comboBox.setItems(viewModel.getCommands());
        this.comboBox.setEditable(true);
        this.comboBox.setMaxWidth(Double.MAX_VALUE);
        var content = new VBox(this.comboBox);
        this.dialog.addContent(content);
        this.dialog.addButton(okButton);
        this.dialog.setOnClose(e -> this.viewModel.onCloseProperty().get().run());
        this.okButton.setDefaultButton(true);
        this.okButton.setOnAction(e -> {
            this.viewModel.setResult(CommandViewModel.ButtonType.OK);
            this.dialog.close();
        });
        FxUtils.requestFocus(comboBox.getEditor());
    }

    @Override
    public CommandViewModel getViewModel() {
        return this.viewModel;
    }

    @Override
    public Dialog getNode() {
        return this.dialog;
    }

}
