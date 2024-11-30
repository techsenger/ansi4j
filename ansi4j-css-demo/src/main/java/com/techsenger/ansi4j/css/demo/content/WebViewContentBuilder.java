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

import com.google.common.escape.Escaper;
import com.google.common.html.HtmlEscapers;
import com.techsenger.ansi4j.css.api.TargetControl;
import com.techsenger.ansi4j.css.demo.utils.ColorUtils;
import com.techsenger.ansi4j.css.demo.Constants;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author Pavel Castornii
 */
public class WebViewContentBuilder implements ContentBuilder<String> {

    private final Escaper escaper = HtmlEscapers.htmlEscaper();

    private static final String HTML_BASE =
            "<html><head><style>"
            + "body{background-color:${bgColor};color:${fgColor};"
            + "white-space: pre;font-family:" + Constants.FONT_FAMILY + ";"
            + "font-size:" + Constants.FONT_SIZE + "px;"
            + "${defaultStyle}}"
            + "@keyframes ansi4j-blinker {50% { opacity: 0; }}"
            + "</style><script>"
            + "${script}"
            + "</script></head>"
            + "<body ${bodyClick}>"
            + "${content}"
            + "</body></html>";

    private final StringBuilder stringBuilder = new StringBuilder();

    private String defaultStyle = "";

    private String script = "";

    private String bodyClick = "";

    private String fgColor = ColorUtils.toHex(Constants.FG_COLOR);

    private String bgColor = ColorUtils.toHex(Constants.BG_COLOR);

    @Override
    public TargetControl getTargetControl() {
        return TargetControl.WEB_VIEW;
    }

    @Override
    public void startStyle(String style) {
        stringBuilder.append("<span style=\"" + style + "\">");
    }

    @Override
    public void endStyle() {
        stringBuilder.append("</span>");
    }

    @Override
    public void appendText(String text) {
        stringBuilder.append(escaper.escape(text));
    }

    @Override
    public void terminateLine() {
        stringBuilder.append("<br>");
    }

    @Override
    public String build() {
        var content = this.stringBuilder.toString();
        var html = replace(Map.of(
                "bgColor", this.bgColor,
                "fgColor", this.fgColor,
                "defaultStyle", this.defaultStyle,
                "script", this.script,
                "bodyClick", this.bodyClick,
                "content", content));
        return html;
    }

    public void setDefaultStyle(String defaultStyles) {
        this.defaultStyle = defaultStyles;
    }

    public void setBodyClick(String bodyClick) {
        this.bodyClick = bodyClick;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public void setFgColor(String fgColor) {
        this.fgColor = fgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    private String replace(Map<String, String> values) {
        return Pattern.compile("\\$\\{(\\w+)}")
                .matcher(HTML_BASE)
                .replaceAll(matchResult -> {
                    String replacement = values.getOrDefault(matchResult.group(1), "");
                    return replacement.replace("$", "\\$");
                });
    }
}
