/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.api.iso6429;

import javax.annotation.concurrent.Immutable;
import com.techsenger.ansi4j.core.api.function.Function;

/**
 *
 * @author Pavel Castornii
 */
@Immutable
public interface ControlFunction extends Function {

    ControlFunctionType getType();

}
