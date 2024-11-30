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

package com.techsenger.ansi4j.css.api.text;

import com.techsenger.ansi4j.css.api.TargetControl;
import java.util.List;

/**
 *
 * @author Pavel Castornii
 */
public class WebViewStyleGenerator extends AbstractTextStyleGenerator {

    public WebViewStyleGenerator() {

    }

    @Override
    public TargetControl getTargetControl() {
        return TargetControl.WEB_VIEW;
    }

    @Override
    protected void doWeightBold(List<String> declarations) {
        declarations.add("font-weight: bold");
    }

    @Override
    protected void doWeightFaint(List<String> declarations) {
        declarations.add("font-weight: lighter");
    }

    @Override
    protected void doWeightNormal(List<String> declarations) {
        declarations.add("font-weight: normal");
    }

    @Override
    protected void doItalicOn(List<String> declarations) {
        declarations.add("font-style: italic");
    }

    @Override
    protected void doItalicOff(List<String> declarations) {
        declarations.add("font-style: normal");
    }

    /**
     * We use "border-bottom" property here, but not "text-decoration-line: underline" because
     * "text-decoration-style: solid; text-decoration-line: line-through;" we use for STRIKETHROUGH. If we
     * set "text-decoration-style: double" for double underline then STRIKETHROUGH will be also double.
     */
    @Override
    protected void doUnderlineSingle(List<String> declarations) {
        declarations.add("border-bottom-style: solid");
        declarations.add("border-bottom-color: #" + this.toHexColor(resolveFgColor()));
        declarations.add("border-bottom-width: 1px");
    }

    /**
     * See {@link #doUnderlineSingle(java.util.List)}.
     * @param declarations
     */
    @Override
    protected void doUnderlineDouble(List<String> declarations) {
        declarations.add("border-bottom-style: double");
        declarations.add("border-bottom-color: #" + this.toHexColor(resolveFgColor()));
    }

    /**
     * See {@link #doUnderlineSingle(java.util.List)}.
     * @param declarations
     */
    @Override
    protected void doUnderlineOff(List<String> declarations) {
        declarations.add("border-bottom-style:none");
    }

    /**
     * To support blinking it is required to add the following @keyframes to CSS style sheet:
     * <code>@keyframes ansi4j-blinker {50% { opacity: 0; }}</code>.
     */
    @Override
    protected void doBlinkingSlow(List<String> declarations) {
        declarations.add("animation: ansi4j-blinker 1s step-start infinite");
    }

    /**
     * See {@link #doBlinkingSlow(java.util.List)}.
     * @param declarations
     */
    @Override
    protected void doBlinkingRapid(List<String> declarations) {
        declarations.add("animation: ansi4j-blinker 0.35s step-start infinite");
    }

    /**
     * See {@link #doBlinkingSlow(java.util.List)}.
     * @param declarations
     */
    @Override
    protected void doBlinkingOff(List<String> declarations) {
        declarations.add("animation: none");
    }

    @Override
    protected void doVisibilityOff(List<String> declarations) {
        declarations.add("visibility:hidden");
    }

    @Override
    protected void doVisibilityOn(List<String> declarations) {
        declarations.add("visibility:visible");
    }

    @Override
    protected void doStrikethroughOn(List<String> declarations) {
        declarations.add("text-decoration-line: line-through");
    }

    @Override
    protected void doStrikethroughOff(List<String> declarations) {
        declarations.add("text-decoration-line: none");
        declarations.add("display: inline-block");
    }

    @Override
    protected void doFont(String font, List<String> declarations) {
        declarations.add("font-family: " + font);
    }

    @Override
    protected void doFgColor(int color, List<String> declarations) {
        declarations.add("color: #" + toHexColor(color));
    }

    @Override
    protected void doBgColor(int color, List<String> declarations) {
        declarations.add("background-color: #" + toHexColor(color));
    }
}
