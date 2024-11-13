/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.demo.content;

import com.techsenger.ansi4j.css.api.TargetControl;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.text.Text;

/**
 *
 * @author Pavel Castornii
 */
public class TextFlowContentBuilder implements ContentBuilder<List<Text>> {

    private final List<Text> texts = new ArrayList<>();

    private String style;

    private String styleClass;

    @Override
    public TargetControl getTargetControl() {
        return TargetControl.TEXT_FLOW;
    }

    @Override
    public void startStyle(String style) {
        this.style = style;
    }

    @Override
    public void endStyle() {
        this.style = null;
    }

    @Override
    public void appendText(String text) {
        var t = new Text(text);
        if (this.style != null) {
            t.setStyle(this.style);
        }
        if (styleClass != null) {
            t.getStyleClass().add(styleClass);
        }
        texts.add(t);
    }

    @Override
    public void terminateLine() {
        texts.add(new Text("\n"));
    }

    @Override
    public List<Text> build() {
        return texts;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }
}
