package com.bivgroup.xbrl.common;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by andreykus on 12.03.2017.
 */
public class AbstractStructuraProcessor<E, V extends StructuraVisitor> implements StructuraProcessor<E, V> {
    protected Logger logger = LogManager.getLogger(this.getClass());
    private V visitor;

    @Override
    public void setVisitor(V visitor) {
        this.visitor = visitor;
    }

    @Override
    public void process(List<E> structura) {
        for (E instance : structura) {
            visitor.visit(instance);
        }
        ;
    }

}
