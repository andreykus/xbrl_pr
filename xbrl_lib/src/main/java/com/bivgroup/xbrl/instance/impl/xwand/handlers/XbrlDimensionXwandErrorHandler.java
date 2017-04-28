package com.bivgroup.xbrl.instance.impl.xwand.handlers;

import com.fujitsu.xml.xbrl.dimension.XBRLDimensionErrorHandler;
import com.fujitsu.xml.xbrl.dimension.XBRLDimensionException;
import com.fujitsu.xml.xbrl.xwand.processor.XBRLProcessorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by bush on 25.04.2017.
 */
public class XbrlDimensionXwandErrorHandler implements XBRLDimensionErrorHandler {
    /**
     * logger
     */
    protected Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Receives information on unrecoverable errors.
     * After this report is made, throws an exception and interrupts processing.
     *
     * @param exception - Exception received from XBRL Dimension processor
     */
    @Override
    public void fatalError(XBRLDimensionException exception) {
        logger.error(exception);
    }

    /**
     * Receives information on recoverable errors.
     *
     * @param exception Exception - received from XBRL Dimension processor
     */
    @Override
    public void error(XBRLDimensionException exception) {
        logger.error(exception);
    }

    /**
     * Receives warning information.
     *
     * @param exception - Exception received from XBRL Dimension processor
     */
    @Override
    public void warning(XBRLDimensionException exception) {
        logger.warn(exception);
        System.err.println(exception.getMessage());
    }

    /**
     * Receives a message.
     *
     * @param message - Exception message received from XBRL processor
     */
    @Override
    public void message(String message) {
        logger.error(message);
        System.err.println(message);
    }
}
