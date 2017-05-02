package com.bivgroup.xbrl.dao;

import com.bivgroup.xbrl.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * Xbrl DB Tool
 * Created by bush on 10.03.2017.
 */
@Stateless
public class XbrlDao implements Dao {
    /**
     * logger
     */
    protected Logger logger = LogManager.getLogger(this.getClass());

    /**
     * EntityManager
     */
    @PersistenceContext(unitName = "Xbrl")
    EntityManager entityManager;

    /**
     * get EntityManager
     * @return - EntityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * set EntityManager
     * @param entityManager - Entity Manager
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * get parameter by exampl
     * @param param -  exampl
     * @throws Exception
     */
    public void get(XbrlParam param) throws Exception {
        entityManager.persist(param);
    }

    /**
     * get processors for get value params
     * @return
     */
    public List<PrepareProcessor> getProcessors() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PrepareProcessor> query = builder.createQuery(PrepareProcessor.class);
        Root<PrepareProcessor> root = query.from(PrepareProcessor.class);
        query.select(root);
        List<PrepareProcessor> rezult = entityManager.createQuery(query).getResultList();
        return rezult;
    }

    public int emptyParams() {
//        EntityTransaction tr = entityManager.getTransaction();
//        tr.begin();
        int updatedEntities = entityManager.createQuery("delete from XbrlParamValue")
                .executeUpdate();
//        tr.commit();
        return updatedEntities;
    }

    /**
     * get priod report xbrl by id
     * @param id - id period
     * @return
     */
    public XbrlPeriod getPeriodById(Long id) {
        return entityManager.find(XbrlPeriod.class, id);
    }

    /**
     * create period
     *
     * @param dateFrom - from date report
     * @param dateTo - to date report
     * @param name - name
     * @return
     */
    public Long createPeriod(Date dateFrom, Date dateTo, String name){
        XbrlPeriod period =  new XbrlPeriod();
        period.setDateFrom(dateFrom);
        period.setDateTo(dateTo);
        period.setName(name);
        entityManager.persist(period);
        return period.getId();
    }

    /**
     * get taxonomy report xbrl by id
     * @param id - id
     * @return
     */
    public XbrlTaxonomy getTaxonomyById(Long id) {
        return entityManager.find(XbrlTaxonomy.class, id);
    }

    /**
     * get list values if element report by taxonomy and period
     * @param idPeriod - id period
     * @param idTaxonomy - id taxonomy
     * @return
     */
    public List<XbrlParamValue> getValues(Long idPeriod, Long idTaxonomy) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<XbrlParamValue> query = builder.createQuery(XbrlParamValue.class);
        Root<XbrlParamValue> root = query.from(XbrlParamValue.class);
        Join<XbrlParam, XbrlParamValue> param = root.join("param", JoinType.LEFT);
        query.select(root);
        Predicate predicate = builder.and(builder.equal(root.get("period"), idPeriod), builder.equal(param.get("taxonomy"), idTaxonomy));
        query.where(predicate);
        List<XbrlParamValue> rezult = entityManager.createQuery(query).getResultList();
        return rezult;
    }

}
