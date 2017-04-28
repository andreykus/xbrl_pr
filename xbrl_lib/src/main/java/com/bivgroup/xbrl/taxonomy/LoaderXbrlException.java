package com.bivgroup.xbrl.taxonomy;

import com.bivgroup.xbrl.common.generators.GeneratorException;

/**
 * Created by bush on 24.03.2017.
 */
public class LoaderXbrlException extends GeneratorException {

    /**
     * Prefix message
     */
    final static String PREFIX_MESSAGE = "load taxonomy:";

    public LoaderXbrlException() {
        super();
    }

    /**
     * Constructor with message
     *
     * @param s - message
     */
    public LoaderXbrlException(String s) {
        super(s);
    }

    /**
     * Constructor with cause
     *
     * @param cause - cause
     */
    public LoaderXbrlException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor with message and cause
     *
     * @param s     - message
     * @param cause - cause
     */
    public LoaderXbrlException(String s, Throwable cause) {
        super(s, cause);
    }
}
