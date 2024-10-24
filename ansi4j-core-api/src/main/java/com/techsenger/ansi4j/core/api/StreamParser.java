/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.api;

import java.io.Closeable;
import javax.annotation.concurrent.NotThreadSafe;

/**
 * Stream parser is created for one input stream. After using parser must be closed.
 *
 * @author Pavel Castornii
 */
@NotThreadSafe
public interface StreamParser extends Parser, Closeable {

    /**
     * {@inheritDoc}
     *
     * This method will return null when input stream returns -1 and there is no more buffered data. However,
     * if input stream will provide data again the same instance of StreamParser can be used. Also
     * see https://stackoverflow.com/questions/611760/java-inputstream-blocking-read
     *
     * @return
     */
    @Override
    Fragment parse();
}
