package com.bivgroup.xbrl.common.logger;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Annotation for mark inject location message
 * Created by andreykus on 19.03.2017.
 */
@LoggerProvider
class LogFactory {
    /**
     * produce logger type log4j
     * @param injectionPoint - point injection
     * @return - logger
     */
    @Produces
    @LoggerProvider(type = LoggerType.Log4J)
    Logger createLoggerLog4J(InjectionPoint injectionPoint) {
        return LogManager.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

}