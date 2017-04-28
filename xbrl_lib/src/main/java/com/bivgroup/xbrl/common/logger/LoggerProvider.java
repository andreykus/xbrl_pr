package com.bivgroup.xbrl.common.logger;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation for mark inject logger
 * Created by andreykus on 19.03.2017.
 */
@Qualifier
@Retention(RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE})
public @interface LoggerProvider {
    /**
     * type loggin
     * @return - type
     */
    LoggerType type() default LoggerType.Log4J;
}