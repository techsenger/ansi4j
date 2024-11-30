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

import com.techsenger.ansi4j.css.demo.AbstractTabViewModel;
import com.techsenger.ansi4j.css.demo.mvvm.ComponentService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Pavel Castornii
 */
public class AttributeTabViewModel extends AbstractTabViewModel {

    private final AttributeSampleService sampleService = new AttributeSampleService();

    private final ObservableList<AbstractAttributeSample<?>> samples =
            FXCollections.observableArrayList(sampleService.createSamples());

    private final ObjectProperty<AbstractAttributeSample<?>> selectedSample = new SimpleObjectProperty<>();

    private final ObjectProperty<SampleContents> content = new SimpleObjectProperty<>();

    public AttributeTabViewModel() {
        this.selectedSample.addListener((ov, oldV, newV) -> {
            content.set(sampleService.createContent(newV));
        });
    }

    @Override
    public void setComponentService(ComponentService s) {

    }

    public ObservableList<AbstractAttributeSample<?>> getSamples() {
        return samples;
    }

    public ObjectProperty<AbstractAttributeSample<?>> selectedSampleProperty() {
        return selectedSample;
    }

    public ObjectProperty<SampleContents> contentProperty() {
        return content;
    }
}
