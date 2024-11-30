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
