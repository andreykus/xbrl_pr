package com.bivgroup.xbrl.instance.impl.xwand.handlers;

/**
 * Created by bush on 19.04.2017.
 */

import com.fujitsu.xml.xbrl.xwand.processor.XBRLErrorHandler;
import com.fujitsu.xml.xbrl.xwand.processor.XBRLProcessorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * manual exception xbrl processor
 */
public class XbrlXwandErrorHandler implements XBRLErrorHandler {
    /**
     * logger
     */
    protected Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Receives information on unrecoverable errors.
     * After this report is made, throws an exception and interrupts processing.
     *
     * @param exception - Exception received from XBRL processor
     * @throws XBRLProcessorException - Generated when an exception is posted to the outside
     */
    @Override
    public void fatalError(XBRLProcessorException exception) throws XBRLProcessorException {
        logger.error(exception);
    }

    /**
     * Receives information on recoverable errors.
     *
     * @param exception Exception - received from XBRL processor
     * @throws XBRLProcessorException - Generated when an exception is posted to the outside
     */
    @Override
    public void error(XBRLProcessorException exception) throws XBRLProcessorException {
        logger.error(exception);
        throw exception;
    }

    /**
     * Receives warning information.
     *
     * @param exception - Exception received from XBRL processor
     * @throws XBRLProcessorException - Generated when an exception is posted to the outside
     */
    @Override
    public void warning(XBRLProcessorException exception) throws XBRLProcessorException {
        logger.warn(exception);
        System.err.println(exception.getMessage());
    }

    /**
     * Receives a message.
     *
     * @param exception - Exception received from XBRL processor
     * @throws XBRLProcessorException - Generated when an exception is posted to the outside
     */
    @Override
    public void message(XBRLProcessorException exception) throws XBRLProcessorException {
        logger.error(exception);
        System.err.println(exception.getMessage());
    }

}
