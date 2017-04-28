package com.bivgroup.xbrl.export.params.visitors.impl;

import com.bivgroup.xbrl.dao.Dao;
import com.bivgroup.xbrl.entity.PrepareProcessor;
import com.bivgroup.xbrl.export.params.processors.ParamProcessor;
import com.bivgroup.xbrl.export.params.processors.ProcessorType;
import com.bivgroup.xbrl.export.params.visitors.AbstractStructuraVisitor;
import com.bivgroup.xbrl.export.params.visitors.StructuraVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * Created by andreykus on 12.03.2017.
 */
public class ParamProcessVisitor extends AbstractStructuraVisitor {
    protected Logger logger = LogManager.getLogger(this.getClass());
    public ParamProcessVisitor(StructuraVisitor next)  {
        super(next);
    }

    @Override
    public void visit(Object element, Dao dao, Date fromDate, Date toDate) {
        PrepareProcessor prepProcessor = (PrepareProcessor)element;
        ProcessorType processorType = prepProcessor.getType();
        processorType.fire(dao,prepProcessor.getSysName(), fromDate, toDate);

    }
}
