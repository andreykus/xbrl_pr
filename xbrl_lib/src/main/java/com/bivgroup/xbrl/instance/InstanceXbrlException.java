package com.bivgroup.xbrl.instance;

import com.bivgroup.xbrl.common.generators.GeneratorException;

/**
 * Exeption for module generator instance xbrl report
 * Created by bush on 22.03.2017.
 */
public class InstanceXbrlException extends GeneratorException {
    /**
     * Prefix message
     */
    final static String PREFIX_MESSAGE = "instancexbrl:";

    public InstanceXbrlException() {
        super();
    }

    /**
     * Constructor with message
     *
     * @param s - message
     */
    public InstanceXbrlException(String s) {
        super(s);
    }

    /**
     * Constructor with cause
     *
     * @param cause - cause
     */
    public InstanceXbrlException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor with message and cause
     *
     * @param s     - message
     * @param cause - cause
     */
    public InstanceXbrlException(String s, Throwable cause) {
        super(s,cause);
    }
}
