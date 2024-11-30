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

package com.techsenger.ansi4j.css.demo.utils;

import atlantafx.base.theme.Styles;
import com.techsenger.ansi4j.css.demo.Constants;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.InlineCssTextArea;

/**
 *
 * @author Pavel Castornii
 */
public final class FxUtils {

    /**
     * A workaround not to scroll to bottom on text append.
     *
     * @param textArea
     * @param text
     */
    public static void setText(InlineCssTextArea textArea, String text) {
        textArea.clear();
        textArea.appendText("\n");
        textArea.selectRange(0, 0);
        var changes = textArea.createMultiChange(2);
        changes.insertTextAbsolutely(0, 1, text);
        changes.deleteTextAbsolutely(0, 1);
        changes.commit();
    }

    public static VBox createWebView() {
        WebView webView = new WebView();
        VBox.setVgrow(webView, Priority.ALWAYS);
        var borderVBox = new VBox(webView);
        borderVBox.getStyleClass().add(Styles.BORDER_DEFAULT);
        return borderVBox;
    }

    public static ScrollPane createTextFlow(int fgColor, int bgColor, Text[] texts) {
        TextFlow textFlow = new TextFlow(texts);
        var cssFileContent = createTextFlowCss(fgColor);
        textFlow.getStylesheets().add(StylesheetStorage.addCssResource(cssFileContent));
        textFlow.setPadding(new Insets(Constants.INSET));
        var scrollPane = new ScrollPane(textFlow);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background-color:" + ColorUtils.toHex(bgColor));
        scrollPane.getStyleClass().add(Styles.BORDER_DEFAULT);
        return scrollPane;
    }

    public static String createTextFlowCss(int fgColor) {
        var cssFileContent = "Text { "
                + "-fx-fill:" + ColorUtils.toHex(fgColor) + ";"
                + "-fx-font-family:" + Constants.FONT_FAMILY + ";"
                + "-fx-font-size:" + Constants.FONT_SIZE + "px;"
                + "}";
        return cssFileContent;
    }

    public static VirtualizedScrollPane createRtfxTextArea(int fgColor, int bgColor) {
        var textArea = new InlineCssTextArea();
        textArea.setPadding(new Insets(Constants.INSET));
        var cssFileContent = createRtfxCss(fgColor, "");
        textArea.setStyle("-fx-background-color:" + ColorUtils.toHex(bgColor));
        textArea.getStylesheets().add(StylesheetStorage.addCssResource(cssFileContent));
        VirtualizedScrollPane textScrollPane = new VirtualizedScrollPane(textArea);
        textArea.getStyleClass().add(Styles.BORDER_DEFAULT);
        return textScrollPane;
    }

    public static String createRtfxCss(int fgColor, String extraStyles) {
        if (!extraStyles.isBlank()) {
            extraStyles = extraStyles + ";";
        }
        var cssFileContent = ".text { "
                + "-fx-fill:" + ColorUtils.toHex(fgColor) + ";"
                + "-fx-font-family:" + Constants.FONT_FAMILY + ";"
                + "-fx-font-size:" + Constants.FONT_SIZE + "px;"
                + extraStyles
                + "}"
                + ".caret {"
                + " -fx-stroke: " + ColorUtils.toHex(fgColor) + ";"
                + "}";
        return cssFileContent;
    }

    public static void requestFocus(Node node) {
        Platform.runLater(() -> {
            if (!node.isFocused()) {
                node.requestFocus();
                requestFocus(node);
            }
        });
    }

    private FxUtils() {
        //empty
    }
}
