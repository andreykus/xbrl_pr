package com.bivgroup.xbrl.export.params.visitors;

import com.bivgroup.xbrl.dao.Dao;
import com.bivgroup.xbrl.export.params.visitors.StructuraVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * Created by andreykus on 18.09.2016.
 */
public abstract class AbstractStructuraVisitor<E> implements StructuraVisitor<E> {
    protected Logger logger = LogManager.getLogger(this.getClass());
    private StructuraVisitor next;
    private Dao dao;
    private Date fromDate;
    private Date toDate;

    public AbstractStructuraVisitor(StructuraVisitor next)  {
        this.next = next;

    }

    protected void processNext(E element, Dao dao, Date fromDate, Date toDate) throws Exception {
        if (next != null) {
            next.visit(element ,dao, fromDate,  toDate);
        }
    }

}
