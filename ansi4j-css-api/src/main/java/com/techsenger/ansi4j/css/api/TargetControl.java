/*
 * Copyright 2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.api;

/**
 *
 * @author Pavel Castornii
 */
public enum TargetControl {

    /**
     * For this control standard web CSS styles will be generated.
     */
    WEB_VIEW,

    /**
     * JavaFX TextFlow.
     */
    TEXT_FLOW,

    /**
     * RichTextFX text areas.
     */
    RTFX_TEXT_AREA
}
