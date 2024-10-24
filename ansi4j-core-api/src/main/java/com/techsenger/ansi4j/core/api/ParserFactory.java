/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.api;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;
import javax.annotation.concurrent.ThreadSafe;
import com.techsenger.ansi4j.core.api.function.FunctionType;

/**
 *
 * @author Pavel Castornii
 */
@ThreadSafe
public interface ParserFactory {

    /**
     * Returns environment.
     * @return
     */
    Environment getEnvironment();

    /**
     * Returns thread-safe type of finder.
     *
     * @return
     */
    FunctionFinder getFunctionFinder();

    /**
     * Returns thread-safe function handlers by type.
     *
     * @return
     */
    Map<FunctionType, FunctionHandler> getFunctionHandlersByType();

    /**
     * Returns thread-safe text handler.
     *
     * @return
     */
    TextHandler getTextHandler();

    /**
     * Creates NOT thread-safe string parser that will use thread-safe components.
     *
     * @param text
     * @return
     */
    StringParser createParser(String text);

    /**
     * Creates NOT thread-safe stream parser that will use thread-safe components.
     *
     * @param stream
     * @param encoding
     * @return
     */
    StreamParser createParser(InputStream stream, Charset encoding, int bufferSize);
}
