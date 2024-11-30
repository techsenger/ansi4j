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

package com.techsenger.ansi4j.css.demo.attribute;

import atlantafx.base.theme.Styles;
import com.techsenger.ansi4j.css.demo.AbstractTabView;
import com.techsenger.ansi4j.css.demo.Constants;
import com.techsenger.ansi4j.css.demo.material.StatusBar;
import com.techsenger.ansi4j.css.demo.utils.FxUtils;
import com.techsenger.ansi4j.css.demo.utils.StylesheetStorage;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import org.fxmisc.richtext.InlineCssTextArea;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignS;
import static com.techsenger.ansi4j.css.demo.utils.TextUtils.suppotedIcon;
import java.util.List;
import javafx.util.Pair;
import org.fxmisc.richtext.model.StyleSpans;

/**
 *
 * @author Pavel Castornii
 */
public class AttributeTabView extends AbstractTabView<AttributeTabViewModel> {

    private WebView webView;

    private final TextField webViewDefaultTextField = new TextField();

    private final TextField webViewCurrentTextField = new TextField();

    private TextFlow textFlow;

    private final TextField textFlowDefaultTextField = new TextField();

    private final TextField textFlowCurrentTextField = new TextField();

    private final TextArea textFlowTextArea = new TextArea();

    private InlineCssTextArea rtfxTextArea;

    private final TextField rtfxDefaultTextField = new TextField();

    private final TextField rtfxCurrentTextField = new TextField();

    public AttributeTabView(AttributeTabViewModel viewModel) {
        super(viewModel);
        var sampleTableView = buildSampleTableView();
        viewModel.contentProperty().addListener((ov, oldV, newV) -> {
            if (newV != null) {
                this.showWebViewContent(newV.getWebViewContent());
                this.showTextFlowContent(newV.getTextFlowContent());
                this.showRtfxTextAreaContent(newV.getRtfxTextAreaContent());
            }
        });
        //when we do viewModel.selectedSampleProperty().bind(...) it doesn\t always work???
        sampleTableView.getSelectionModel().selectedItemProperty()
                .addListener((ov, oldV, newV) -> viewModel.selectedSampleProperty().set(newV));
        var controlVBox = new VBox(
            sampleTableView,
            new Label("WebView"),
            buildWebPane(),
            new Label("TextFlow"),
            buildTextFlowPane(),
            new Label("InlineCssTextArea"),
            buildTextAreaPane());
        controlVBox.setPadding(new Insets(Constants.INSET));
        controlVBox.setSpacing(Constants.INSET);
        sampleTableView.getSelectionModel().select(0);
        VBox.setVgrow(controlVBox, Priority.ALWAYS);
        var mainVBox = new VBox(controlVBox, new StatusBar());
        getNode().setContent(mainVBox);
        getNode().setText("Attribute Samples");
        getNode().setGraphic(new FontIcon(MaterialDesignS.SHAPE_OUTLINE));
    }

    private TableView<AbstractAttributeSample<?>> buildSampleTableView() {
        var sampleTableView = new TableView<AbstractAttributeSample<?>>(getViewModel().getSamples());
        TableColumn<AbstractAttributeSample<?>, ?> classColumn = new TableColumn<>("Sample");
        classColumn.setCellValueFactory(
                cellData -> new SimpleObjectProperty(cellData.getValue().getClass().getSimpleName()));
        TableColumn<AbstractAttributeSample<?>, ?> defaultValueColumn = new TableColumn<>("Default Value");
        defaultValueColumn.setCellValueFactory(cellData -> {
            var sample = cellData.getValue();
            var value = ((AbstractAttributeSample) sample).toString(sample.getDefaultValue());
            return new SimpleObjectProperty(value);
        });
        TableColumn<AbstractAttributeSample<?>, ?> currentValueColumn = new TableColumn<>("Current Value");
        currentValueColumn.setCellValueFactory(cellData -> {
            var sample = cellData.getValue();
            var value = ((AbstractAttributeSample) sample).toString(sample.getCurrentValue());
            return new SimpleObjectProperty(value);
        });
        TableColumn<AbstractAttributeSample<?>, ?> webViewSupportedColumn = new TableColumn<>("WebView");
        webViewSupportedColumn.setCellValueFactory(cellData
                -> new SimpleObjectProperty(suppotedIcon(cellData.getValue().isSupportedInWebView())));
        TableColumn<AbstractAttributeSample<?>, ?> textFlowSupportedColumn = new TableColumn<>("TextFlow");
        textFlowSupportedColumn.setCellValueFactory(cellData
                -> new SimpleObjectProperty(suppotedIcon(cellData.getValue().isSupportedInTextFlow())));
        TableColumn<AbstractAttributeSample<?>, ?> rtfxTextAreaSupportedColumn = new TableColumn<>("InlineCssTextArea");
        rtfxTextAreaSupportedColumn.setCellValueFactory(cellData
                -> new SimpleObjectProperty(suppotedIcon(cellData.getValue().isSupportedInRtfxTextArea())));
        sampleTableView.getColumns().addAll(classColumn, defaultValueColumn, currentValueColumn,
                webViewSupportedColumn, textFlowSupportedColumn, rtfxTextAreaSupportedColumn);
        sampleTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        sampleTableView.getStyleClass().add(Styles.DENSE);
        VBox.setVgrow(sampleTableView, Priority.ALWAYS);
        return sampleTableView;
    }

    private Pane buildWebPane() {
        var webViewBox = FxUtils.createWebView();
        this.webView = (WebView) webViewBox.getChildren().get(0);
        this.webViewDefaultTextField.setEditable(false);
        this.webViewCurrentTextField.setEditable(false);
        var gridPane = createGridPane(webViewDefaultTextField, webViewCurrentTextField);
        gridPane.add(webViewBox, 0, 0);
        GridPane.setVgrow(webViewBox, Priority.NEVER);
        GridPane.setRowSpan(webViewBox, 3);
        return gridPane;
    }

    private Pane buildTextFlowPane() {
        var scrollPane = FxUtils.createTextFlow(Constants.FG_COLOR, Constants.BG_COLOR, new Text[0]);
        this.textFlow = (TextFlow) scrollPane.getContent();
        this.textFlowDefaultTextField.setEditable(false);
        this.textFlowCurrentTextField.setEditable(false);
        var gridPane = createGridPane(this.textFlowDefaultTextField, this.textFlowCurrentTextField);
        GridPane.setVgrow(scrollPane, Priority.ALWAYS);
        gridPane.add(scrollPane, 0, 0);
        GridPane.setRowSpan(scrollPane, 3);
        return gridPane;
    }

    private Pane buildTextAreaPane() {
        var textScrollPane = FxUtils.createRtfxTextArea(Constants.FG_COLOR, Constants.BG_COLOR);
        this.rtfxTextArea = (InlineCssTextArea) textScrollPane.getContent();
        this.rtfxDefaultTextField.setEditable(false);
        this.rtfxCurrentTextField.setEditable(false);
        var gridPane = createGridPane(rtfxDefaultTextField, rtfxCurrentTextField);
        var wrapper = new VBox(textScrollPane);
        VBox.setVgrow(textScrollPane, Priority.ALWAYS);
        GridPane.setVgrow(wrapper, Priority.ALWAYS);
        gridPane.add(wrapper, 0, 0);
        GridPane.setRowSpan(wrapper, 3);
        return gridPane;
    }

    private GridPane createGridPane(TextField textField1, TextField textField2) {
        var l1 = new Label("Default Style:");
        var l2 = new Label("Current Style:");
        var gridPane = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMaxWidth(300);
        col1.setMinWidth(300);
        ColumnConstraints col2 = new ColumnConstraints();
        gridPane.getColumnConstraints().addAll(col1, col2);
        gridPane.setVgap(Constants.INSET);
        gridPane.setHgap(Constants.INSET);
        gridPane.add(l1, 1, 0);
        gridPane.add(l2, 1, 1);
        gridPane.add(textField1, 2, 0);
        gridPane.add(textField2, 2, 1);
        GridPane.setHgrow(textField1, Priority.ALWAYS);
        GridPane.setHgrow(textField2, Priority.ALWAYS);

        RowConstraints rowConstraints1 = new RowConstraints();
        rowConstraints1.setVgrow(Priority.NEVER);
        RowConstraints rowConstraints2 = new RowConstraints();
        rowConstraints2.setVgrow(Priority.NEVER);
        RowConstraints rowConstraints3 = new RowConstraints();
        rowConstraints3.setVgrow(Priority.ALWAYS);
        gridPane.getRowConstraints().addAll(rowConstraints1, rowConstraints2, rowConstraints3);
        VBox.setVgrow(gridPane, Priority.ALWAYS);
        gridPane.setMaxHeight(0);
        return gridPane;
    }

    private void showWebViewContent(SampleContent<String> c) {
        this.webView.getEngine().loadContent(c.getValue());
        printStyleInfo(c.getDefaultStyle(), c.getCurrentStyle(), webViewDefaultTextField, webViewCurrentTextField);
    }

    private void showTextFlowContent(SampleContent<List<Text>> c) {
        textFlow.getChildren().clear();
        textFlow.getStylesheets().clear();
        var css1 = FxUtils.createTextFlowCss(Constants.FG_COLOR);
        textFlow.getStylesheets().add(StylesheetStorage.addCssResource(css1));
        var css2 = ".text {" + c.getDefaultStyle() + ";}";
        textFlow.getStylesheets().add(StylesheetStorage.addCssResource(css2));
        textFlow.getChildren().addAll(c.getValue());
        printStyleInfo(c.getDefaultStyle(), c.getCurrentStyle(), textFlowDefaultTextField,
                textFlowCurrentTextField);
    }

    private void showRtfxTextAreaContent(SampleContent<Pair<String, StyleSpans<String>>> c) {
        rtfxTextArea.clear();
        rtfxTextArea.getStylesheets().clear();
        var css = FxUtils.createRtfxCss(Constants.FG_COLOR, c.getDefaultStyle());
        rtfxTextArea.getStylesheets().add(StylesheetStorage.addCssResource(css));
        rtfxTextArea.appendText(c.getValue().getKey());
        rtfxTextArea.setStyleSpans(0, c.getValue().getValue());
        printStyleInfo(c.getDefaultStyle(), c.getCurrentStyle(), rtfxDefaultTextField,
                rtfxCurrentTextField);
    }

    private void printStyleInfo(String defaultStyle, String currentStyle, TextField defultTextField,
            TextField currentTextField) {
        defultTextField.setText(defaultStyle);
        currentTextField.setText(currentStyle);
    }

}
