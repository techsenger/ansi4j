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

package com.techsenger.ansi4j.css.demo.text;

import com.techsenger.ansi4j.css.demo.mvvm.ComponentService;

/**
 *
 * @author Pavel Castornii
 */
class TextComponentService implements ComponentService {

    private final TextTabView view;

    TextComponentService(TextTabView view) {
        this.view = view;
    }

    public void openCommandDialog(CommandViewModel viewModel) {
        var v = new CommandView(viewModel);
        this.view.openDialog(v.getNode());
    }

    public void openSgrDialog(SgrHelpViewModel viewModel) {
        var v = new SgrHelpView(viewModel);
        this.view.openDialog(v.getNode());
    }
}
