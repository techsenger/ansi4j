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
import com.techsenger.ansi4j.core.api.iso6429.ControlFunctionType;
import com.techsenger.ansi4j.core.api.spi.ParserFactoryConfig;
import com.techsenger.ansi4j.core.api.spi.ParserFactoryService;
import java.util.Arrays;
import java.util.ServiceLoader;

/**
 *
 * @author Pavel Castornii
 */
@ThreadSafe
public interface ParserFactory {

    class Builder {

        private final ParserFactoryConfig config = new ParserFactoryConfig();

        public Builder() {
            //empty constructor
        }

        /**
         * Sets the {@link Environment}.
         *
         * @param environment
         * @return
         */
        public Builder environment(Environment environment) {
            this.config.setEnvironment(environment);
            return this;
        }

        /**
         * Sets the {@link ControlFunctionType}. It is necessary to provide a complete list of function types that
         * the parser will have to handle.
         *
         * @param types
         * @return
         */
        public Builder functionTypes(ControlFunctionType... types) {
            config.setFunctionTypes(Arrays.asList(types));
            return this;
        }

        /**
         * Sets the {@link FunctionFinder}. Use this method only if you want to use your custom finder.
         *
         * @param finder
         * @return
         */
        public Builder functionFinder(FunctionFinder finder) {
            this.config.setFunctionFinder(finder);
            return this;
        }

        /**
         * Sets the {@link FunctionHandler}. Use this method only if you want to use your custom handlers.
         *
         * @param handlers
         * @return
         */
        public Builder functionHandlers(FunctionHandler... handlers) {
            this.config.setFunctionHandlers(Arrays.asList(handlers));
            return this;
        }

        /**
         * Sets the {@link TextHandler}. Use this method only if you want to use your custom handler.
         *
         * @param handler
         * @return
         */
        public Builder textHandler(TextHandler handler) {
            this.config.setTextHandler(handler);
            return this;
        }

        public ParserFactory build() {
            this.config.validate();
            var factory = ServiceLoader
                    .load(ParserFactoryService.class).findFirst().orElseThrow().createFactory(config);
            return factory;
        }
    }

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
