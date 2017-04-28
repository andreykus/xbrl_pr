package com.bivgroup.xbrl.taxonomy;

import com.bivgroup.xbrl.common.generators.GeneratorException;

/**
 * Interface load taxonomy
 * Created by bush on 24.03.2017.
 */
public interface Loader {

    /**
     * load taxonomy
     *
     * @param path - path to file taxonomy
     * @return - taxonomy
     * @throws LoaderXbrlException - exception loader taxonomy
     */
    Object load(String path) throws LoaderXbrlException;
}

