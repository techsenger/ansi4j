/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.demo.material;

import com.techsenger.ansi4j.css.demo.Constants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

/**
 *
 * @author Pavel Castornii
 */
public class StatusBar extends HBox {

    private final ProgressBar progressBar = new ProgressBar();

    public StatusBar() {
        getStyleClass().add("status-bar");
        getChildren().add(progressBar);
        progressBar.setPadding(new Insets(0, Constants.INSET, 0, Constants.INSET));
        progressBar.setVisible(false);
        setAlignment(Pos.CENTER_RIGHT);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
}
