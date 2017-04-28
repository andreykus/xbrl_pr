package com.bivgroup.xbrl.instance;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation marker implementation xbrl
 * Created by andreykus on 18.03.2017.
 */

@Qualifier
@Retention(RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE})
public @interface GeneratorProvider {
    /**
     * type generator, default use FREEMARKER
     * @return - type generator
     */
    TypeGenerator type() default TypeGenerator.FREEMARKER;
}




