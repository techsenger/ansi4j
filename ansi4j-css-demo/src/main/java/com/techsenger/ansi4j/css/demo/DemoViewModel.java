/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.demo;

import com.techsenger.ansi4j.css.demo.attribute.AttributeTabViewModel;
import com.techsenger.ansi4j.css.demo.mvvm.ComponentService;
import com.techsenger.ansi4j.css.demo.mvvm.ViewModel;
import com.techsenger.ansi4j.css.demo.text.TextTabViewModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Pavel Castornii
 */
public class DemoViewModel implements ViewModel {

    private final StringProperty stageTitle = new SimpleStringProperty("Techsenger Ansi4j CSS Demo");

    private DemoComponentService componentService;

    public DemoViewModel() {

    }

    @Override
    public void setComponentService(ComponentService s) {
        this.componentService = (DemoComponentService) s;
    }

    public StringProperty stageTitleProperty() {
        return stageTitle;
    }

    public void openTextTab() {
        this.componentService.openTextTab(new TextTabViewModel());
    }

    public void openAttributeTab() {
        this.componentService.openAttributeTab(new AttributeTabViewModel());
    }

    public void exit() {
        Platform.exit();
    }

    public void doOnShow() {
        this.openTextTab();
    }
}
