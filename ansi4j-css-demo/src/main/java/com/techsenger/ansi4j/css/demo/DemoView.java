/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.demo;

import atlantafx.base.theme.Dracula;
import com.techsenger.ansi4j.css.demo.mvvm.View;
import java.util.List;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignS;
import org.kordamp.ikonli.materialdesign2.MaterialDesignT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Pavel Castornii
 */
public class DemoView extends Application implements View<DemoViewModel> {

    private static final Logger logger = LoggerFactory.getLogger(DemoView.class);

    private final DemoViewModel viewModel = new DemoViewModel();

    private final TabPane tabPane = new TabPane();

    private final VBox mainVBox;

    public DemoView() {
        var fonts = List.of(
                "fonts/JetBrainsMonoNL-Regular.ttf",
                "fonts/JetBrainsMonoNL-Italic.ttf",
                "fonts/JetBrainsMonoNL-Bold.ttf",
                "fonts/JetBrainsMonoNL-BoldItalic.ttf",
                "fonts/JetBrainsMonoNL-Light.ttf",
                "fonts/JetBrainsMonoNL-LightItalic.ttf",
                "fonts/JetBrainsMonoNL-Thin.ttf",
                "fonts/JetBrainsMonoNL-ThinItalic.ttf"
                );
        fonts.forEach(f -> Font.loadFont(DemoView.class.getResource(f).toExternalForm(), 10));
        this.viewModel.setComponentService(new DemoComponentService(this));

        var textMenuItem = new MenuItem("Text Samples", new FontIcon(MaterialDesignT.TEXT_BOX_OUTLINE));
        textMenuItem.setOnAction(e -> this.viewModel.openTextTab());
        var attributeMenuItem = new MenuItem("Attribute Samples", new FontIcon(MaterialDesignS.SHAPE_OUTLINE));
        attributeMenuItem.setOnAction(e -> this.viewModel.openAttributeTab());
        var exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(e -> this.viewModel.exit());
        var fileMenu = new Menu("File", null, textMenuItem, attributeMenuItem, new SeparatorMenuItem(), exitMenuItem);
        var menuBar = new MenuBar(fileMenu);

        this.mainVBox = new VBox(menuBar, tabPane);

        tabPane.getTabs().addListener((ListChangeListener.Change<? extends Tab> change) -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    Tab newTab = change.getAddedSubList().get(change.getAddedSubList().size() - 1);
                    tabPane.getSelectionModel().select(newTab);
                }
            }
        });
        VBox.setVgrow(tabPane, Priority.ALWAYS);
    }

    @Override
    public DemoViewModel getViewModel() {
        return this.viewModel;
    }

    @Override
    public VBox getNode() {
        return this.mainVBox;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Application.setUserAgentStylesheet(new Dracula().getUserAgentStylesheet());
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println(e.getMessage());
            logger.error("Error in FxApplication", e);
        });
        var scene = new Scene(getNode(), 1000, 800);
        scene.getStylesheets().add(DemoView.class.getResource("demo.css").toExternalForm());
        primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWN, event -> this.viewModel.doOnShow());
        primaryStage.setScene(scene);
        primaryStage.titleProperty().bind(this.viewModel.stageTitleProperty());
        primaryStage.show();
    }

    public void addTab(Tab tab) {
        this.tabPane.getTabs().add(tab);
    }
}
