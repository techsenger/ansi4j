/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
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
