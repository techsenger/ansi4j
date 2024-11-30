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

package com.techsenger.ansi4j.css.api.text;

import com.techsenger.ansi4j.css.api.TargetControl;
import java.util.List;

/**
 *
 * @author Pavel Castornii
 */
public class TextFlowStyleGenerator extends AbstractTextStyleGenerator {

    @Override
    public TargetControl getTargetControl() {
        return TargetControl.TEXT_FLOW;
    }

    @Override
    protected void doWeightBold(List<String> declarations) {
        declarations.add("-fx-font-weight: bold");
    }

    @Override
    protected void doWeightFaint(List<String> declarations) {
        declarations.add("-fx-font-weight: 300");
    }

    @Override
    protected void doWeightNormal(List<String> declarations) {
        declarations.add("-fx-font-weight: normal");
    }

    @Override
    protected void doItalicOn(List<String> declarations) {
        declarations.add("-fx-font-style: italic");
    }

    @Override
    protected void doItalicOff(List<String> declarations) {
        declarations.add("-fx-font-style: normal");
    }

    @Override
    protected void doUnderlineSingle(List<String> declarations) {
        declarations.add("-fx-underline: true");
    }

    @Override
    protected void doUnderlineDouble(List<String> declarations) {
        declarations.add("-fx-underline: true");
    }

    @Override
    protected void doUnderlineOff(List<String> declarations) {
        declarations.add("-fx-underline: false");
    }

    @Override
    protected void doVisibilityOff(List<String> declarations) {
        declarations.add("visibility:hidden"); //no -fx- prefix
    }

    @Override
    protected void doVisibilityOn(List<String> declarations) {
        declarations.add("visibility:visible"); //no -fx- prefix
    }

    @Override
    protected void doStrikethroughOn(List<String> declarations) {
        declarations.add("-fx-strikethrough: true");
    }

    @Override
    protected void doStrikethroughOff(List<String> declarations) {
        declarations.add("-fx-strikethrough: false");
    }

    @Override
    protected void doFont(String font, List<String> declarations) {
        declarations.add("-fx-font-family: " + font);
    }

    @Override
    protected void doBlinkingSlow(List<String> declarations) {

    }

    @Override
    protected void doBlinkingRapid(List<String> declarations) {

    }

    @Override
    protected void doBlinkingOff(List<String> declarations) {

    }

    @Override
    protected void doFgColor(int color, List<String> declarations) {
        declarations.add("-fx-fill: #" + toHexColor(color));
    }

    @Override
    protected void doBgColor(int color, List<String> declarations) {
        declarations.add("-fx-background-color: #" + toHexColor(color));
    }

}
