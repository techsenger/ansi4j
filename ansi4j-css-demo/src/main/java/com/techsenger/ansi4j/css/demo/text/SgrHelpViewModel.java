/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.demo.text;

import com.techsenger.ansi4j.css.demo.mvvm.ComponentService;
import com.techsenger.ansi4j.css.demo.mvvm.ViewModel;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Pavel Castornii
 */
class SgrHelpViewModel implements ViewModel {

    private final ObservableList<SgrParameter> parameters = FXCollections.observableArrayList(List.of(
            new SgrParameter(0, "Reset all attributes", true),
            new SgrParameter(1, "Bold text", true),
            new SgrParameter(2, "Faint text", true),
            new SgrParameter(3, "Italic text", true),
            new SgrParameter(4, "Underline text", true),
            new SgrParameter(5, "Slow Blink", true),
            new SgrParameter(6, "Rapid Blink", true),
            new SgrParameter(7, "Inverse colors", true),
            new SgrParameter(8, "Hidden text", true),
            new SgrParameter(9, "Strikethrough", true),
            new SgrParameter(10, "Primary font", true),
            new SgrParameter(11, "Alternative font 1", true),
            new SgrParameter(12, "Alternative font 2", true),
            new SgrParameter(13, "Alternative font 3", true),
            new SgrParameter(14, "Alternative font 4", true),
            new SgrParameter(15, "Alternative font 5", true),
            new SgrParameter(16, "Alternative font 6", true),
            new SgrParameter(17, "Alternative font 7", true),
            new SgrParameter(18, "Alternative font 8", true),
            new SgrParameter(19, "Alternative font 9", true),
            new SgrParameter(20, "Fraktur (rarely supported)", false),
            new SgrParameter(21, "Bold off or Double underline", true),
            new SgrParameter(22, "Normal color or intensity", true),
            new SgrParameter(23, "Not italic, not Fraktur", true),
            new SgrParameter(24, "Underline off", true),
            new SgrParameter(25, "Blink off", true),
            new SgrParameter(26, "Reserved", false),
            new SgrParameter(27, "Inverse off", true),
            new SgrParameter(28, "Reveal (not hidden)", true),
            new SgrParameter(29, "Not struck through", true),
            new SgrParameter(30, "Text color: black", true),
            new SgrParameter(31, "Text color: red", true),
            new SgrParameter(32, "Text color: green", true),
            new SgrParameter(33, "Text color: yellow", true),
            new SgrParameter(34, "Text color: blue", true),
            new SgrParameter(35, "Text color: magenta", true),
            new SgrParameter(36, "Text color: cyan", true),
            new SgrParameter(37, "Text color: white", true),
            new SgrParameter(38, "Text color: 256 or 24-bit color (38;5;n or 38;2;r;g;b)", true),
            new SgrParameter(39, "Default text color", true),
            new SgrParameter(40, "Background color: black", true),
            new SgrParameter(41, "Background color: red", true),
            new SgrParameter(42, "Background color: green", true),
            new SgrParameter(43, "Background color: yellow", true),
            new SgrParameter(44, "Background color: blue", true),
            new SgrParameter(45, "Background color: magenta", true),
            new SgrParameter(46, "Background color: cyan", true),
            new SgrParameter(47, "Background color: white", true),
            new SgrParameter(48, "Background color: 256 or 24-bit color (48;5;n or 48;2;r;g;b)", true),
            new SgrParameter(49, "Default background color", true),
            new SgrParameter(50, "Reserved", false),
            new SgrParameter(51, "Framed", false),
            new SgrParameter(52, "Encircled", false),
            new SgrParameter(53, "Overlined", false),
            new SgrParameter(54, "Not framed or encircled", false),
            new SgrParameter(55, "Not overlined", false),
            new SgrParameter(56, "Reserved", false),
            new SgrParameter(57, "Reserved", false),
            new SgrParameter(58, "Reserved", false),
            new SgrParameter(59, "Reserved", false),
            new SgrParameter(60, "Ideogram underline or right side line", false),
            new SgrParameter(61, "Ideogram double underline or double line on the right side", false),
            new SgrParameter(62, "Ideogram overline or left side line", false),
            new SgrParameter(63, "Ideogram double overline or double line on the left side", false),
            new SgrParameter(64, "Ideogram stress marking", false),
            new SgrParameter(65, "Ideogram attributes off", false),
            new SgrParameter(90, "Text color: bright black", true),
            new SgrParameter(91, "Text color: bright red", true),
            new SgrParameter(92, "Text color: bright green", true),
            new SgrParameter(93, "Text color: bright yellow", true),
            new SgrParameter(94, "Text color: bright blue", true),
            new SgrParameter(95, "Text color: bright magenta", true),
            new SgrParameter(96, "Text color: bright cyan", true),
            new SgrParameter(97, "Text color: bright white", true),
            new SgrParameter(100, "Background color: bright black", true),
            new SgrParameter(101, "Background color: bright red", true),
            new SgrParameter(102, "Background color: bright green", true),
            new SgrParameter(103, "Background color: bright yellow", true),
            new SgrParameter(104, "Background color: bright blue", true),
            new SgrParameter(105, "Background color: bright magenta", true),
            new SgrParameter(106, "Background color: bright cyan", true),
            new SgrParameter(107, "Background color: bright white", true)));

    SgrHelpViewModel() {

    }

    public ObservableList<SgrParameter> getParameters() {
        return parameters;
    }

    @Override
    public void setComponentService(ComponentService s) {

    }
}
