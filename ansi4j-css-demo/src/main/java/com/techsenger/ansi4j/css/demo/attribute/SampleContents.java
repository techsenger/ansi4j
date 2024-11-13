/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.demo.attribute;

import java.util.List;
import javafx.scene.text.Text;
import javafx.util.Pair;
import org.fxmisc.richtext.model.StyleSpans;

/**
 *
 * @author Pavel Castornii
 */
class SampleContents {

    private final SampleContent<String> webViewContent;

    private final SampleContent<List<Text>> textFlowContent;

    private final SampleContent<Pair<String, StyleSpans<String>>> rtfxTextAreaContent;

    SampleContents(SampleContent<String> webViewContent, SampleContent<List<Text>> textFlowContent,
            SampleContent<Pair<String, StyleSpans<String>>> rtfxTextAreaContent) {
        this.webViewContent = webViewContent;
        this.textFlowContent = textFlowContent;
        this.rtfxTextAreaContent = rtfxTextAreaContent;
    }

    public SampleContent<String> getWebViewContent() {
        return webViewContent;
    }

    public SampleContent<List<Text>> getTextFlowContent() {
        return textFlowContent;
    }

    public SampleContent<Pair<String, StyleSpans<String>>> getRtfxTextAreaContent() {
        return rtfxTextAreaContent;
    }
}
