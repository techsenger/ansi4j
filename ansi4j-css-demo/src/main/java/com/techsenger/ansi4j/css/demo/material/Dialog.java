/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.demo.material;

import atlantafx.base.layout.ModalBox;
import com.techsenger.ansi4j.css.demo.Constants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Pavel Castornii
 */
public class Dialog extends ModalBox {

    private final double width;

    private final double height;

    private final VBox mainBox;

    private final HBox titleBox;

    private VBox contentBox = new VBox();

    private final HBox buttonBox = new HBox();

    public Dialog(String title, double width, double height) {
        super("#" + Constants.MODAL_PANE_ID);
        this.getStyleClass().add("modal-dialog");
        this.width = width;
        this.height = height;
        this.setMaxWidth(width);
        this.setMaxHeight(height);
        this.titleBox = new HBox(new Label(title));
        this.titleBox.getStyleClass().add("title");
        this.contentBox.getStyleClass().add("content");
        mainBox = new VBox(titleBox, contentBox, buttonBox);
        mainBox.getStyleClass().add("main");
        mainBox.setPrefSize(width, height);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setPadding(new Insets(Constants.INSET));
        super.addContent(mainBox);
    }

    @Override
    public void addContent(Node node) {
        this.contentBox.getChildren().add(node);
    }

    public void addButton(Button button) {
        buttonBox.getChildren().add(button);
    }

}
