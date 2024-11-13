/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.css.demo.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class lets to add and load css resources dynamically. JavaFX 17 supports loading style sheets from data URIs,
 * but we use JavaFX 14.
 *
 * @author Pavel Castornii
 */
public final class StylesheetStorage {

    private static final String PROTOCOL = "internal";

    private static Map<String, String> contentByFileName = new ConcurrentHashMap<>();

    private static class StringURLConnection extends URLConnection {

        StringURLConnection(URL url) {
            super(url);
        }

        @Override
        public void connect() throws IOException { }

        @Override
        public InputStream getInputStream() throws IOException {
            var fileName = getURL().toString().substring((PROTOCOL + ":").length());
            var content = contentByFileName.remove(fileName);
            return new StringBufferInputStream(content);
        }
    }

    private static final class StringURLStreamHandlerFactory implements URLStreamHandlerFactory {

        private URLStreamHandler streamHandler = new URLStreamHandler() {

            @Override
            protected URLConnection openConnection(URL url) throws IOException {
                if (url.toString().toLowerCase().endsWith(".css")) {
                    return new StringURLConnection(url);
                }
                throw new FileNotFoundException();
            }
        };

        @Override
        public URLStreamHandler createURLStreamHandler(String protocol) {
            if (PROTOCOL.equals(protocol)) {
                return streamHandler;
            }
            return null;
        }
    }

    private StylesheetStorage() {
        //empty
    }

    static {
          URL.setURLStreamHandlerFactory(new StringURLStreamHandlerFactory());
    }

    /**
     * Adds resource to storage and returns link to it.
     * @param content
     * @return
     */
    public static String addCssResource(String content) {
        var cssFileName = System.nanoTime() + ".css";
        contentByFileName.put(cssFileName, content);
        return PROTOCOL + ":" + cssFileName;
    }
}
