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

import com.techsenger.ansi4j.css.api.color.Palette16;
import com.techsenger.ansi4j.css.api.color.VgaPalette16;
import com.techsenger.ansi4j.css.api.color.VsCodePalette16;
import com.techsenger.ansi4j.css.api.color.XtermPalette16;
import com.techsenger.ansi4j.css.api.color.XtermPalette256;
import com.techsenger.ansi4j.css.demo.AbstractTabViewModel;
import com.techsenger.ansi4j.css.demo.mvvm.ComponentService;
import com.techsenger.ansi4j.css.demo.utils.FxUtils;
import com.techsenger.ansi4j.css.demo.utils.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
/**
 *
 * @author Pavel Castornii
 */
public class TextTabViewModel extends AbstractTabViewModel {

    private final ProcessingService processingService = new ProcessingService();

    private final ObservableList<String> texts = FXCollections.observableArrayList(List.of(
            "All Parameters",
            "Log messages"));

    private final IntegerProperty selectedTextIndex = new SimpleIntegerProperty();

    private final StringProperty currentInputText = new SimpleStringProperty();

    private final StringProperty newInputText = new SimpleStringProperty();

    private final ObservableList<String> controls = FXCollections.observableArrayList(List.of(
            "WebView",
            "TextFlow",
            "InlineCssTextArea"));

    private final IntegerProperty selectedControlIndex = new SimpleIntegerProperty();

    private final ObservableList<String> palettes = FXCollections.observableArrayList(List.of(
            "Xterm 256",
            "VGA 16",
            "VS Code 16",
            "Xterm 16"));

    private final IntegerProperty selectedPaletteIndex = new SimpleIntegerProperty();

    private final ObjectProperty<Color> fgColor = new SimpleObjectProperty<>();

    private final ObjectProperty<Color> bgColor = new SimpleObjectProperty<>();

    private final BooleanProperty runButtonDisable = new SimpleBooleanProperty();

    private final BooleanProperty progressBarVisible = new SimpleBooleanProperty();

    private final ObjectProperty<Object> content = new SimpleObjectProperty<>();

    private TextComponentService componentService;

    public TextTabViewModel() {
        this.selectedTextIndex.addListener((ov, oldV, newV) -> showText(newV.intValue()));
    }

    public StringProperty currentInputTextProperty() {
        return currentInputText;
    }

    public StringProperty newInputTextProperty() {
        return newInputText;
    }

    public ObservableList<String> getTexts() {
        return texts;
    }

    public IntegerProperty selectedTextIndexProperty() {
        return selectedTextIndex;
    }

    public ObservableList<String> getControls() {
        return controls;
    }

    public IntegerProperty selectedControlIndexProperty() {
        return selectedControlIndex;
    }

    public ObservableList<String> getPalettes() {
        return palettes;
    }

    public IntegerProperty selectedPaletteIndexProperty() {
        return selectedPaletteIndex;
    }

    public ObjectProperty<Color> fgColorProperty() {
        return fgColor;
    }

    public ObjectProperty<Color> bgColorProperty() {
        return bgColor;
    }

    public BooleanProperty runButtonDisableProperty() {
        return runButtonDisable;
    }

    public BooleanProperty progressBarVisibleProperty() {
        return progressBarVisible;
    }

    @Override
    public void setComponentService(ComponentService s) {
        this.componentService = (TextComponentService) s;
    }

    public void showCommandDialog() {
        var viewModel = new CommandViewModel();
        viewModel.onCloseProperty().set(() -> {
            if (viewModel.getResult() == CommandViewModel.ButtonType.OK) {
                var t = viewModel.commandProperty().get();
                if (!t.isBlank()) {
                    var commands = Arrays.stream(t.split(" "))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .collect(Collectors.toList());
                    var task = createCommandTask(commands);
                    runCommandTask(task);
                }
            }
        });
        this.componentService.openCommandDialog(viewModel);
    }

    public void showEscape() {
        var text = currentInputText.get();
        text = TextUtils.showEsc(text);
        this.newInputText.set(text);
    }

    public void hideEscape() {
        var text = currentInputText.get();
        text = TextUtils.hideEsc(text);
        this.newInputText.set(text);
    }

    public void showSgrHelp() {
        this.componentService.openSgrDialog(new SgrHelpViewModel());
    }

    public void run() {
        var inputText = this.currentInputText.get();
        if (!TextUtils.isEscHidden(inputText)) {
            inputText = TextUtils.hideEsc(inputText);
        }
        int defFgColor = FxUtils.toInt(fgColor.getValue());
        int defBgColor = FxUtils.toInt(bgColor.getValue());
        var palette = createPalette();
        Object c;
        switch (this.selectedControlIndex.get()) {
            case 0:
                c = this.processingService.getWebViewContent(inputText, defFgColor, defBgColor, palette);
                break;
            case 1:
                c = this.processingService.getTextFlowContent(inputText, defFgColor, defBgColor, palette);
                break;
            case 2:
                c = this.processingService.getRtfxTextAreaContent(inputText, defFgColor, defBgColor, palette);
                break;
            default:
                throw new AssertionError();
        }
        this.content.set(null);
        this.content.set(c);
    }

    public ObjectProperty<Object> contentProperty() {
        return content;
    }

    private void showText(Integer textIndex) {
        if (textIndex == null || textIndex == -1) {
            return;
        }
        String text;
        switch (textIndex) {
            case 0: text = TextSamples.ALL_PARAMETERS; break;
            case 1: text = TextSamples.LOG_MESSAGES; break;
            default: throw new AssertionError();
        }
        this.newInputText.set(text);
    }

    private Palette16 createPalette() {
        Palette16 palette16 = null;
        switch (this.selectedPaletteIndex.get()) {
            case 0: palette16 = new XtermPalette256(); break;
            case 1: palette16 = new VgaPalette16(); break;
            case 2: palette16 = new VsCodePalette16(); break;
            case 3: palette16 = new XtermPalette16(); break;
            default:
                throw new AssertionError();
        }
        return palette16;
    }

    public Task<String> createCommandTask(List<String> commands) {
        return new Task<String>() {
            @Override
            protected String call() throws IOException, InterruptedException {
                StringBuilder sb = new StringBuilder();
                try {
                    ProcessBuilder processBuilder = new ProcessBuilder(commands);
                    processBuilder.redirectErrorStream(true);
                    Process process = processBuilder.start();
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                            sb.append("\n");
                        }
                    }
                    process.waitFor();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                return sb.toString();
            }
        };
    }

    private void runCommandTask(Task<String> task) {
        if (task == null) {
            return;
        }
        this.runButtonDisable.set(true);
        this.progressBarVisible.set(true);
        task.setOnSucceeded(e -> {
            this.newInputText.set(task.getValue());
            this.runButtonDisable.set(false);
            this.progressBarVisible.set(false);
        });
        task.setOnFailed(e -> {
            this.newInputText.set("Failed to execute command: " + task.getException().getMessage());
            this.runButtonDisable.set(true);
            this.progressBarVisible.set(true);
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}
