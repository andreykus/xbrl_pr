package com.bivgroup.xbrl.common.generators;

/**
 * Exeption for module generator object
 * Created by andreykus on 18.03.2017.
 */
public class GeneratorException extends Exception {
    /**
     * Prefix message
     */
    final static String PREFIX_MESSAGE = "generator:";
    public Throwable detail;

    /**
     * Default constructor
     */
    public GeneratorException() {
        initCause(null);
    }

    /**
     * Constructor with message
     *
     * @param s - message
     */
    public GeneratorException(String s) {
        super(s);
        initCause(null);
    }

    /**
     * Constructor with cause
     *
     * @param cause - cause
     */
    public GeneratorException(Throwable cause) {
        super(cause);
        this.detail = cause;

    }

    /**
     * Constructor with message and cause
     *
     * @param s     - message
     * @param cause - cause
     */
    public GeneratorException(String s, Throwable cause) {
        super(s);
        initCause(null);
        detail = cause;
    }

    /**
     * Сreate message for Exception
     *
     * @return -  message
     */
    public String getMessage() {
        if (detail == null) {
            return PREFIX_MESSAGE + super.getMessage();
        } else {
            return PREFIX_MESSAGE + super.getMessage() + "; nested exception is: \n\t" +
                    detail.toString();
        }
    }

    /**
     * Responce cause
     *
     * @return - cause
     */
    public Throwable getCause() {
        return detail;
    }
}
