package com.bivgroup.xbrl.taxonomy.impl;

import com.bivgroup.xbrl.taxonomy.Loader;
import com.bivgroup.xbrl.taxonomy.LoaderProvider;
import com.bivgroup.xbrl.taxonomy.LoaderXbrlException;
import com.bivgroup.xbrl.taxonomy.TypeLoader;
import com.bivgroup.xbrl.taxonomy.element.Taxonomy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileNotFoundException;

/**
 * Created by bush on 24.03.2017.
 */
//use Custom impl
@LoaderProvider(type = TypeLoader.CUSTOM)
public class LoaderTaxonomy implements Loader {

    /**
     * logger
     */
    protected Logger logger = LogManager.getLogger(this.getClass());

    /**
     * load taxonomy
     *
     * @param path - path to file taxonomy
     * @return - taxonomy
     * @throws LoaderXbrlException - exception loader taxonomy
     */
    @Override
    public Object load(String path) throws LoaderXbrlException {
        return loadXML(path);
    }


    private Taxonomy loadXML(String xml)
            throws LoaderXbrlException {

        return new Taxonomy(xml);
    }

}
