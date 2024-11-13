/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.api;

import com.techsenger.ansi4j.css.api.attribute.AttributeChange;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 *
 * @author Pavel Castornii
 */
@Immutable
public interface ProcessorResult {

    /**
     * Returns the attribute changes that occurred during the function processing.
     *
     * @return
     */
    List<AttributeChange<?>> getAttributeChanges();

    /**
     * Returns a list of CSS declarations without a semicolon at the end.
     *
     * @return
     */
    List<String> getStyleDeclarations();
}
