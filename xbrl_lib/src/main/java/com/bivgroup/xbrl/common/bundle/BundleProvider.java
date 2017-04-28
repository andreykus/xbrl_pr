package com.bivgroup.xbrl.common.bundle;

import javax.inject.Qualifier;
import javax.inject.Singleton;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation for mark inject location message
 * Created by andreykus on 19.03.2017.
 */
@Qualifier
@Singleton
@Retention(RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE})
public @interface BundleProvider {
    /**
     * type locale
     * @return -  locale
     */
    LocaleType type() default LocaleType.RU;
}

