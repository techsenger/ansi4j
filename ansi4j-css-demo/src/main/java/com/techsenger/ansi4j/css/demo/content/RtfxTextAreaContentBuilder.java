/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
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
