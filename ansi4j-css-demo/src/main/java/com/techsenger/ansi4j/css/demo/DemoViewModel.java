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
