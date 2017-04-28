package com.bivgroup.xbrl.api.restcontollers.exceptions;

/**
 * Exception for service XBRL
 * Created by andreykus on 18.03.2017.
 */
public class XbrlServerException extends Exception{
    /**
     * prefix message
     */
    final static String PREFIX_MESSAGE = "xbrl server:";
    public Throwable detail;

    public XbrlServerException() {
        initCause(null);
    }

    public XbrlServerException(String s) {
        super(s);
        initCause(null);
    }

    public XbrlServerException(Throwable cause) {
        super(cause);
        this.detail = cause;

    }

    public XbrlServerException(String s, Throwable cause) {
        super(s);
        initCause(null);
        detail = cause;
    }

    public String getMessage() {
        if (detail == null) {
            return PREFIX_MESSAGE + super.getMessage();
        } else {
            return PREFIX_MESSAGE + super.getMessage() + "; nested exception is: \n\t" +
                    detail.toString();
        }
    }

    public Throwable getCause() {
        return detail;
    }
}
