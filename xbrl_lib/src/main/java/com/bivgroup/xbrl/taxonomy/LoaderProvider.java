package com.bivgroup.xbrl.taxonomy;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation marker implementation load txonomy
 * Created by bush on 24.03.2017.
 */

@Qualifier
@Retention(RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE})
public @interface LoaderProvider {

    /**
     * type loader, default use CUSTOM
     * @return - type generator
     */
    TypeLoader type() default TypeLoader.CUSTOM;
}


