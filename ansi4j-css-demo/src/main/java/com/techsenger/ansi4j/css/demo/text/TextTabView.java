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

package com.techsenger.ansi4j.css.demo.text;

import atlantafx.base.controls.ModalPane;
import atlantafx.base.theme.Styles;
import com.techsenger.ansi4j.css.demo.AbstractTabView;
import com.techsenger.ansi4j.css.demo.Constants;
import com.techsenger.ansi4j.css.demo.material.Dialog;
import com.techsenger.ansi4j.css.demo.material.StatusBar;
import com.techsenger.ansi4j.css.demo.utils.ColorUtils;
import com.techsenger.ansi4j.css.demo.utils.FxUtils;
import java.util.List;
import java.util.regex.Pattern;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.util.Pair;
import netscape.javascript.JSObject;
import org.fxmisc.richtext.InlineCssTextArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignE;
import org.kordamp.ikonli.materialdesign2.MaterialDesignH;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.kordamp.ikonli.materialdesign2.MaterialDesignT;

/**
 *
 * @author Pavel Castornii
 */
public class TextTabView extends AbstractTabView<TextTabViewModel> {

    private static final double CONTROL_PANE_WIDTH = 300;

    public class JavaFxBridge {

        public void printSpanInfo(String text, String style) {
            TextTabView.this.printSpanInfo(text, style);
        }
    }

    /**
     * Bridge can't be a local variable because of GC.
     */
    private final JavaFxBridge fxBridge = new JavaFxBridge();

    private final ModalPane modalPane = new ModalPane();

    private final StatusBar statusBar = new StatusBar();

    private InlineCssTextArea inputTextArea;

    private final VBox outputNodeWrapper = new VBox();

    private final ComboBox<String> textComboBox = new ComboBox<>();

    private final Button commandButton = new Button("Command", new FontIcon(MaterialDesignC.CONSOLE_LINE));

    private final Button showEscapeButton = new Button("ESC", new FontIcon(MaterialDesignE.EYE_OUTLINE));

    private final Button hideEscapeButton = new Button("ESC", new FontIcon(MaterialDesignE.EYE_OFF_OUTLINE));

    private final Button sgrHelpButton = new Button("SGR", new FontIcon(MaterialDesignH.HELP_RHOMBUS_OUTLINE));

    private final ComboBox<String> controlComboBox = new ComboBox<>();

    private final ColorPicker fgColorPicker = new ColorPicker(Color.web(ColorUtils.toHex(Constants.FG_COLOR)));

    private final ColorPicker bgColorPicker = new ColorPicker(Color.web(ColorUtils.toHex(Constants.BG_COLOR)));

    private final ComboBox<String> paletteComboBox = new ComboBox<>();

    private final Button runButton = new Button("Run", new FontIcon(MaterialDesignP.PLAY_OUTLINE));

    private final TextField spanTextTextField = new TextField();

    private final TextField spanStyleTextField = new TextField();

    public TextTabView(TextTabViewModel viewModel) {
        super(viewModel);
        viewModel.setComponentService(new TextComponentService(this));
        var splitPane = new SplitPane(createInputPane(), createOutputPane());
        splitPane.setOrientation(Orientation.VERTICAL);
        VBox.setVgrow(splitPane, Priority.ALWAYS);
        var vBox = new VBox(splitPane, statusBar);
        this.statusBar.getProgressBar().visibleProperty().bind(viewModel.progressBarVisibleProperty());
        this.modalPane.setPersistent(true);
        this.modalPane.setId(Constants.MODAL_PANE_ID);
        getNode().setContent(new StackPane(modalPane, vBox));
        getNode().setText("Text Samples");
        getNode().setGraphic(new FontIcon(MaterialDesignT.TEXT_BOX_OUTLINE));
        textComboBox.getSelectionModel().select(0);
    }

    public void printSpanInfo(String text, String style) {
        this.spanTextTextField.clear();
        this.spanStyleTextField.clear();
        if (style.isBlank()) {
            return;
        }
        this.spanTextTextField.appendText(text);
        var styles = style.split(Pattern.quote(";"));
        for (var s : styles) {
            this.spanStyleTextField.appendText(s.trim() + ";\n");
        }
    }

    void openDialog(Dialog dialog) {
        this.modalPane.show(dialog);
    }

    private Pane createInputPane() {
        textComboBox.setItems(getViewModel().getTexts());
        getViewModel().selectedTextIndexProperty().bind(textComboBox.getSelectionModel().selectedIndexProperty());
        textComboBox.getStyleClass().add(Styles.DENSE);
        this.commandButton.setTooltip(new Tooltip("Output from Command"));
        this.showEscapeButton.setTooltip(new Tooltip("Replace U+001B with 'ESC'"));
        this.hideEscapeButton.setTooltip(new Tooltip("Replace 'ESC' with U+001B"));
        this.sgrHelpButton.setTooltip(new Tooltip("SGR Parameters Help"));
        var l = new Label("Text:");
        HBox.setMargin(l, new Insets(0, 0, 0, Constants.INSET));
        var toolbar = new ToolBar(
                l,
                this.textComboBox,
                this.commandButton,
                this.showEscapeButton,
                this.hideEscapeButton,
                this.sgrHelpButton);
        this.commandButton.setOnAction(e -> getViewModel().showCommandDialog());
        this.showEscapeButton.setOnAction(e -> getViewModel().showEscape());
        this.hideEscapeButton.setOnAction(e -> getViewModel().hideEscape());
        this.sgrHelpButton.setOnAction(e -> getViewModel().showSgrHelp());
        var textScrollPane = FxUtils.createRtfxTextArea(Constants.FG_COLOR, Constants.BG_COLOR);
        textScrollPane.setMinHeight(60);
        this.inputTextArea = (InlineCssTextArea) textScrollPane.getContent();
        getViewModel().newInputTextProperty().addListener((ov, oldV, newV) -> FxUtils.setText(inputTextArea, newV));
        getViewModel().currentInputTextProperty().bind(inputTextArea.textProperty());
        VBox.setVgrow(textScrollPane, Priority.ALWAYS);
        var content = new VBox(toolbar, textScrollPane);
        VBox.setMargin(textScrollPane, new Insets(Constants.INSET));
        VBox.setVgrow(content, Priority.ALWAYS);
        var wrapper = new VBox(content);
        return wrapper;
    }

    private Pane createOutputPane() {
        controlComboBox.setItems(getViewModel().getControls());
        getViewModel().selectedControlIndexProperty().bind(controlComboBox.getSelectionModel().selectedIndexProperty());
        controlComboBox.getSelectionModel().select(0);
        paletteComboBox.setItems(getViewModel().getPalettes());
        getViewModel().selectedPaletteIndexProperty().bind(paletteComboBox.getSelectionModel().selectedIndexProperty());
        paletteComboBox.getSelectionModel().select(0);
        var l = new Label("Control:");
        HBox.setMargin(l, new Insets(0, 0, 0, Constants.INSET));
        this.runButton.setTooltip(new Tooltip("Run Processor Style & Show"));
        var toolbar = new ToolBar(
                l,
                controlComboBox,
                new Label("FG Color:"),
                this.fgColorPicker,
                new Label("BG Color:"),
                this.bgColorPicker,
                new Label("Palette:"),
                paletteComboBox,
                this.runButton);

        getViewModel().fgColorProperty().bind(this.fgColorPicker.valueProperty());
        getViewModel().bgColorProperty().bind(this.bgColorPicker.valueProperty());
        this.runButton.setOnAction(e ->  {
            var iBgColor = ColorUtils.toInt(this.bgColorPicker.getValue());
            this.outputNodeWrapper.setStyle("-fx-background-color:" + ColorUtils.toHex(iBgColor));
            getViewModel().run();
        });
        getViewModel().contentProperty().addListener((ov, oldV, newV) -> showContent(newV));
        this.runButton.disableProperty().bind(getViewModel().runButtonDisableProperty());
        VBox.setMargin(this.outputNodeWrapper, new Insets(Constants.INSET, 0, Constants.INSET, 0));
        this.outputNodeWrapper.getStyleClass().add(Styles.BORDER_DEFAULT);
        this.outputNodeWrapper.setStyle("-fx-background-color:" + ColorUtils.toHex(Constants.BG_COLOR));
        this.outputNodeWrapper.getChildren().addListener((ListChangeListener<Node>) change -> {
            this.outputNodeWrapper.getStyleClass().remove(Styles.BORDER_DEFAULT);
        });
        VBox.setVgrow(this.outputNodeWrapper, Priority.ALWAYS);
        this.outputNodeWrapper.setMinHeight(60);

        var spanGridPane = new GridPane();
        spanGridPane.setVgap(Constants.INSET);
        spanGridPane.setHgap(Constants.INSET);
        spanGridPane.add(new Label("Span Text"), 0, 0);
        this.spanTextTextField.setEditable(false);
        spanGridPane.add(this.spanTextTextField, 1, 0);
        GridPane.setHgrow(this.spanTextTextField, Priority.ALWAYS);
        spanGridPane.add(new Label("Span Style"), 0, 1);
        this.spanStyleTextField.setEditable(false);
        spanGridPane.add(this.spanStyleTextField, 1, 1);
        GridPane.setHgrow(this.spanStyleTextField, Priority.ALWAYS);

        var content = new VBox(toolbar, this.outputNodeWrapper, spanGridPane);
        VBox.setMargin(this.outputNodeWrapper, new Insets(Constants.INSET));
        VBox.setMargin(spanGridPane, new Insets(0, Constants.INSET, Constants.INSET, Constants.INSET));
        return content;
    }

    private void showContent(Object content) {
        if (content == null) {
            return;
        }
        Node outputNode = null;
        var defFgColor = ColorUtils.toInt(this.fgColorPicker.getValue());
        var defBgColor = ColorUtils.toInt(this.bgColorPicker.getValue());
        switch (this.controlComboBox.getSelectionModel().getSelectedIndex()) {
            case 0:
                var webContent = (String) content;
                    var webViewBox = FxUtils.createWebView();
                    WebView webView = (WebView) webViewBox.getChildren().get(0);
                    var engine = webView.getEngine();
                    engine.setJavaScriptEnabled(true);
                    engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
                        if (newState == javafx.concurrent.Worker.State.SUCCEEDED) {
                            JSObject window = (JSObject) engine.executeScript("window");
                            window.setMember("fxBridge", fxBridge);
                        }
                    });
                    engine.loadContent(webContent);
                    outputNode = webViewBox;
                break;
            case 1:
                var textFlowContent = (List<Text>) content;
                    EventHandler<MouseEvent> handler = event -> {
                        Text source = (Text) event.getSource();
                        printSpanInfo(source.getText(), source.getStyle());
                    };
                    for (var t : textFlowContent) {
                        t.setOnMouseClicked(handler);
                    }
                    outputNode = FxUtils.createTextFlow(defFgColor, defBgColor, textFlowContent.toArray(Text[]::new));
                break;
            case 2:
                var rtfxContent = (Pair<String, StyleSpans<String>>) content;
                    var textScrollPane = FxUtils.createRtfxTextArea(defFgColor, defBgColor);
                    InlineCssTextArea textArea = (InlineCssTextArea) textScrollPane.getContent();
                    textArea.caretPositionProperty().addListener((ov, oldV, newV) -> {
                        var text = "";
                        var style = "";
                        var ss = textArea.getStyleSpans(0, textArea.getLength());
                        var range = ss.getStyleRange(newV);
                        ss = textArea.getStyleSpans(range.getStart(), range.getEnd());
                        var iterator = ss.iterator();
                        if (iterator.hasNext()) {
                            style = ss.iterator().next().getStyle();
                            text = textArea.getText().substring(range.getStart(), range.getEnd());
                        }
                        printSpanInfo(text, style);
                    });
                    FxUtils.setText(textArea, rtfxContent.getKey());
                    textArea.setStyleSpans(0, rtfxContent.getValue());
                    outputNode = textScrollPane;
                break;
            default:
                throw new AssertionError();
        }
        this.outputNodeWrapper.getChildren().clear();
        VBox.setVgrow(outputNode, Priority.ALWAYS);
        this.outputNodeWrapper.getChildren().add(outputNode);
    }
}
