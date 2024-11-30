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
