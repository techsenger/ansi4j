/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
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
