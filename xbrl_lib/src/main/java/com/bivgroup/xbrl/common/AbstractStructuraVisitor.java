package com.bivgroup.xbrl.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by andreykus on 18.09.2016.
 */
public abstract class AbstractStructuraVisitor<E> implements StructuraVisitor<E> {
    protected Logger logger = LogManager.getLogger(this.getClass());
    private StructuraVisitor next;

    public AbstractStructuraVisitor(StructuraVisitor next) {
        this.next = next;

    }

    protected void processNext(E element) throws Exception {
        if (next != null) {
            next.visit(element);
        }
    }

}
