package com.bivgroup.xbrl.instance.impl.xwand;

import com.bivgroup.xbrl.taxonomy.LoaderXbrlException;

/**
 * exception when element not found in taxonome
 * Created by bush on 28.03.2017.
 */
public class ElementNotFoundException extends LoaderXbrlException{

    public ElementNotFoundException() {
        super();
    }

    /**
     * Constructor with message
     *
     * @param s - message
     */
    public ElementNotFoundException(String s) {
        super(s);
    }

    /**
     * Constructor with cause
     *
     * @param cause - cause
     */
    public ElementNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor with message and cause
     *
     * @param s     - message
     * @param cause - cause
     */
    public ElementNotFoundException(String s, Throwable cause) {
        super(s, cause);
    }
}
