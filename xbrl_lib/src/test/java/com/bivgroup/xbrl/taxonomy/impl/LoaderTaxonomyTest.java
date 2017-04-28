package com.bivgroup.xbrl.taxonomy.impl;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by bush on 24.03.2017.
 */
public class LoaderTaxonomyTest {
    final static String file= "c:\\1222\\Электронная база данных расширенной модели данных и таксономии\\Бухгалтерская (финансовая) отчетность\\Таксономия\\www.cbr.ru\\xbrl\\2017-01-01\\ifrs-ru\\EP\\EP_PURCB.xsd";

    @org.testng.annotations.Test
    public void testLoad() throws Exception {
        LoaderTaxonomy loader = new LoaderTaxonomy();
        loader.load(file);
    }
}