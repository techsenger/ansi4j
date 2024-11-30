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

package com.techsenger.ansi4j.css.demo;

import com.techsenger.ansi4j.css.demo.mvvm.View;
import javafx.scene.control.Tab;

/**
 *
 * @author Pavel Castornii
 */
public abstract class AbstractTabView<T extends AbstractTabViewModel> implements View<T> {

    private final T viewModel;

    private final Tab tab = new Tab();

    public AbstractTabView(T viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public T getViewModel() {
        return this.viewModel;
    }

    public Tab getNode() {
        return this.tab;
    }

}
