package com.bivgroup.xbrl.dao;

import com.bivgroup.xbrl.entity.PrepareProcessor;
import com.bivgroup.xbrl.entity.XbrlParamValue;
import com.bivgroup.xbrl.entity.XbrlPeriod;
import com.bivgroup.xbrl.entity.XbrlTaxonomy;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

/**
 * Interface Dao tools
 * Created by andreykus on 12.03.2017.
 */
public interface Dao {
    /**
     * get Entity Manager
     *
     * @return - Entity Manager
     */
    EntityManager getEntityManager();

    /**
     * set Entity Manager
     *
     * @param entityManager - Entity Manager
     */
    void setEntityManager(EntityManager entityManager);

    /**
     * obtain processors
     *
     * @return - processors
     */
    List<PrepareProcessor> getProcessors();

    /**
     * get period report bi Id
     *
     * @param id - id period
     * @return - period
     */
    XbrlPeriod getPeriodById(Long id);


    /**
     * get Taxonomy report by Id
     *
     * @param id - id
     * @return - Taxonomy
     */
    XbrlTaxonomy getTaxonomyById(Long id);

    int emptyParams();

    /**
     * obtain values elemen report
     *
     * @param idPeriod   - id period
     * @param idTaxonomy - id taxonomy
     * @return - values element report
     */
    List<XbrlParamValue> getValues(Long idPeriod, Long idTaxonomy);

    /**
     * create Period
     *
     * @param dateFrom - from date report
     * @param dateTo   - to date report
     * @param name     - name
     * @return - id period
     */
    Long createPeriod(Date dateFrom, Date dateTo, String name);

}
