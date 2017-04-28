package com.bivgroup.xbrl.export.params.visitors;

import com.bivgroup.xbrl.dao.Dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;

/**
 * Created by andreykus on 12.03.2017.
 */
public class AbstractStructuraProcessor<E,V extends StructuraVisitor> implements StructuraProcessor<E,V> {
    protected Logger logger = LogManager.getLogger(this.getClass());
    private V visitor;

    @Override
    public void setVisitor(V visitor){
        this.visitor = visitor;
    }

    @Override
    public void process(List<E> structura, Dao dao, Date fromDate, Date toDate) {
        for (E instance:structura) {
            visitor.visit(instance, dao, fromDate, toDate);
        };
    }

}
