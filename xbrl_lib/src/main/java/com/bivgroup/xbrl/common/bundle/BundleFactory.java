package com.bivgroup.xbrl.common.bundle;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Factory locale message
 * Created by andreykus on 19.03.2017.
 */
@BundleProvider
@Singleton
class BundleFactory {

    /**
     * produce RUSSIAN locale
     * @param injectionPoint -  point injection locale
     * @return - bundle
     */
    @Produces
    @BundleProvider(type = LocaleType.RU)
    ResourceBundle createResourceBundleRu(InjectionPoint injectionPoint) {
        //use file in property - systemMessages_ru.properties
        return ResourceBundle.getBundle("systemMessages", new Locale("ru", "RU"));
    }

    /**
     * produce ENGLISH locale
     * @param injectionPoint -  point injection locale
     * @return - bundle
     */
    @Produces
    @Alternative
    @BundleProvider(type = LocaleType.EN)
    ResourceBundle createResourceBundleEn(InjectionPoint injectionPoint) {
        //use file in property - systemMessages_en.properties
        return ResourceBundle.getBundle("systemMessages", new Locale("en", "EN"));
    }

}