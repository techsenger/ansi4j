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
import javafx.util.Pair;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

/**
 *
 * @author Pavel Castornii
 */
public class RtfxTextAreaContentBuilder implements ContentBuilder<Pair<String, StyleSpans<String>>> {

    private final StringBuilder sb = new StringBuilder();

    private final StyleSpansBuilder<String> ssb = new StyleSpansBuilder<>();

    private String style;

    @Override
    public TargetControl getTargetControl() {
        return TargetControl.RTFX_TEXT_AREA;
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
        sb.append(text);
        if (style != null) {
            ssb.add(style, text.length());
        } else {
            ssb.add("", text.length());
        }
    }

    @Override
    public void terminateLine() {
        var text = "\n";
        sb.append(text);
        ssb.add("", text.length());
    }

    @Override
    public Pair<String, StyleSpans<String>> build() {
        return new Pair<>(sb.toString(), ssb.create());
    }
}
