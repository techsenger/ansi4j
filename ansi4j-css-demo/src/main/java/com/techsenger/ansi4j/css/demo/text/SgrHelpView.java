/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
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
