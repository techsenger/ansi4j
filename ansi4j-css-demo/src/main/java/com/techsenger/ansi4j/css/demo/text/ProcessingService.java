/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.demo.text;

import com.techsenger.ansi4j.core.api.Fragment;
import com.techsenger.ansi4j.core.api.FragmentType;
import com.techsenger.ansi4j.core.api.FunctionFragment;
import com.techsenger.ansi4j.core.api.ParserFactory;
import com.techsenger.ansi4j.core.api.TextFragment;
import com.techsenger.ansi4j.core.api.iso6429.C0ControlFunction;
import com.techsenger.ansi4j.core.api.iso6429.ControlSequenceFunction;
import com.techsenger.ansi4j.css.api.StyleProcessor;
import com.techsenger.ansi4j.css.api.color.Palette16;
import com.techsenger.ansi4j.css.demo.content.ContentBuilder;
import com.techsenger.ansi4j.css.demo.content.RtfxTextAreaContentBuilder;
import com.techsenger.ansi4j.css.demo.content.TextFlowContentBuilder;
import com.techsenger.ansi4j.css.demo.content.WebViewContentBuilder;
import com.techsenger.ansi4j.css.demo.utils.Ansi4jUtils;
import com.techsenger.ansi4j.css.demo.utils.ColorUtils;
import java.util.List;
import javafx.scene.text.Text;
import javafx.util.Pair;
import org.fxmisc.richtext.model.StyleSpans;

/**
 *
 * @author Pavel Castornii
 */
class ProcessingService {

    private final ParserFactory parserFactory = Ansi4jUtils.createParserFactory();

    String getWebViewContent(String inputText, int defFgColor, int defBgColor, Palette16 palette) {
        var contentBuilder = new WebViewContentBuilder();
        contentBuilder.setScript("function handleBodyClick(event){"
                + "  let el = event.target;"
                + "  if (el.tagName === 'SPAN') {"
                + "    fxBridge.printSpanInfo(el.textContent, el.style.cssText);"
                + "  } else {"
                + "    fxBridge.printSpanInfo('', '');"
                + "  }"
                + "}");
        contentBuilder.setBodyClick("onclick=\"handleBodyClick(event)\"");
        contentBuilder.setFgColor(ColorUtils.toHex(defFgColor));
        contentBuilder.setBgColor(ColorUtils.toHex(defBgColor));
        return getContent(inputText, defFgColor, defBgColor, palette, contentBuilder);
    }

    public List<Text> getTextFlowContent(String inputText, int defFgColor, int defBgColor, Palette16 palette) {
        var contentBuilder = new TextFlowContentBuilder();
        return getContent(inputText, defFgColor, defBgColor, palette, contentBuilder);
    }

    public Pair<String, StyleSpans<String>> getRtfxTextAreaContent(String inputText, int defFgColor, int defBgColor,
            Palette16 palette) {
        var contentBuilder = new RtfxTextAreaContentBuilder();
        return getContent(inputText, defFgColor, defBgColor, palette, contentBuilder);
    }

    private <T> T getContent(String inputText, int defFgColor, int defBgColor,
            Palette16 palette, ContentBuilder<T> contentBuilder) {
        var styleProcessor = Ansi4jUtils.createProcessor(defFgColor, defBgColor, palette);
        parse(inputText, styleProcessor, contentBuilder);
        return contentBuilder.build();
    }

    private void parse(String inputText, StyleProcessor styleProcessor, ContentBuilder<?> contentBuilder) {
        var parser = this.parserFactory.createParser(inputText);
        Fragment fragment = null;
        boolean styleStarted = false;
        while ((fragment = parser.parse()) != null) {
            if (fragment.getType() == FragmentType.FUNCTION) {
                String style;
                FunctionFragment functionFragment = (FunctionFragment) fragment;
                if (functionFragment.getFunction() == ControlSequenceFunction.SGR_SELECT_GRAPHIC_RENDITION) {
                    if (styleStarted) {
                        contentBuilder.endStyle();
                        styleStarted = false;
                    }
                    var result = styleProcessor.process(functionFragment, contentBuilder.getTargetControl());
                    if (!result.getStyleDeclarations().isEmpty()) {
                        style = String.join(";", result.getStyleDeclarations());
                        styleStarted = true;
                        contentBuilder.startStyle(style);
                    }
                } else if (functionFragment.getFunction() == C0ControlFunction.LF_LINE_FEED) {
                    contentBuilder.terminateLine();
                }
            } else if (fragment.getType() == FragmentType.TEXT) {
                TextFragment textFragment = (TextFragment) fragment;
                contentBuilder.appendText(textFragment.getText());
            }
        }
        if (styleStarted) {
            contentBuilder.endStyle();
        }
    }
}
