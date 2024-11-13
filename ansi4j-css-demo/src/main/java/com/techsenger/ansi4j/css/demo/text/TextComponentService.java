/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
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
