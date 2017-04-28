package com.bivgroup.xbrl.export.params.processors;

import com.bivgroup.xbrl.dao.Dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.Date;

/**
 * Created by andreykus on 12.03.2017.
 */
public class ProcedureProcessor implements ParamProcessor {
    protected Logger logger = LogManager.getLogger(this.getClass());
    @Override
    public void process(Dao dao, String nameObject, Date fromDate, Date toDate) {

        StoredProcedureQuery query = dao.getEntityManager().createStoredProcedureQuery(nameObject);
        query.registerStoredProcedureParameter( "fromDate", Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter( "toDate", Date.class, ParameterMode.IN);

        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);
        query.execute();
    }
}
