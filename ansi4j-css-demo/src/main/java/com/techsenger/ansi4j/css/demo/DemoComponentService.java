/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.demo;

import com.techsenger.ansi4j.css.demo.attribute.AttributeTabView;
import com.techsenger.ansi4j.css.demo.attribute.AttributeTabViewModel;
import com.techsenger.ansi4j.css.demo.mvvm.ComponentService;
import com.techsenger.ansi4j.css.demo.text.TextTabView;
import com.techsenger.ansi4j.css.demo.text.TextTabViewModel;

/**
 *
 * @author Pavel Castornii
 */
public class DemoComponentService implements ComponentService {

    private final DemoView view;

    public DemoComponentService(DemoView view) {
        this.view = view;
    }

    public void openTextTab(TextTabViewModel textViewModel) {
        var textView = new TextTabView(textViewModel);
        this.view.addTab(textView.getNode());
    }

    public void openAttributeTab(AttributeTabViewModel attributeViewModel) {
        var attributeView = new AttributeTabView(attributeViewModel);
        this.view.addTab(attributeView.getNode());
    }
}
