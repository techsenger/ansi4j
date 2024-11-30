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

import atlantafx.base.theme.Styles;
import com.techsenger.ansi4j.css.demo.material.Dialog;
import com.techsenger.ansi4j.css.demo.mvvm.View;
import com.techsenger.ansi4j.css.demo.utils.FxUtils;
import com.techsenger.ansi4j.css.demo.utils.TextUtils;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author Pavel Castornii
 */
class SgrHelpView implements View<SgrHelpViewModel> {

    private final SgrHelpViewModel viewModel;

    private final Dialog dialog;

    private final Button okButton = new Button("OK");

    SgrHelpView(SgrHelpViewModel viewModel) {
        this.viewModel = viewModel;
        var table = createTableView();
        var content = new VBox(table);
        this.dialog = new Dialog("SGR Parameters", 800, 450);
        this.dialog.addContent(content);
        this.okButton.setDefaultButton(true);
        this.okButton.setOnAction(e -> this.dialog.close());
        this.dialog.addButton(okButton);
        table.getSelectionModel().select(0);
        FxUtils.requestFocus(table);
    }

    public Dialog getNode() {
        return dialog;
    }

    @Override
    public SgrHelpViewModel getViewModel() {
        return this.viewModel;
    }

    private TableView<SgrParameter> createTableView() {
        TableView<SgrParameter> table = new TableView<>(this.viewModel.getParameters());
        TableColumn<SgrParameter, Integer> valueColumn = new TableColumn<>("Value");
        valueColumn.setMinWidth(75);
        valueColumn.setMaxWidth(75);
        valueColumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getCode()));
        TableColumn<SgrParameter, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getDescription()));
        TableColumn<SgrParameter, Boolean> supportedColumn = new TableColumn<>("Supported");
        supportedColumn.setCellValueFactory(data
                -> new SimpleObjectProperty(TextUtils.suppotedIcon(data.getValue().isSupported())));
        supportedColumn.setMinWidth(120);
        supportedColumn.setMaxWidth(120);
        table.getColumns().addAll(valueColumn, descriptionColumn, supportedColumn);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getStyleClass().add(Styles.DENSE);
        VBox.setVgrow(table, Priority.ALWAYS);
        return table;
    }
}
