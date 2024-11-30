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
