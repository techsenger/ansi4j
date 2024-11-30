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
