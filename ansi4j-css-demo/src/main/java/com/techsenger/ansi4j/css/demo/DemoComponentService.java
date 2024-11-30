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
