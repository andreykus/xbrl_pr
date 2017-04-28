package com.bivgroup.xbrl.export.params.processors;

import com.bivgroup.xbrl.dao.Dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * Created by bush on 10.03.2017.
 */
public enum ProcessorType {
    PROCEDURA(new ProcedureProcessor()) {
    },
    CLASS(new ClassProcessor()) {
    };
    protected Logger logger = LogManager.getLogger(this.getClass());
    private ParamProcessor processor;

    private ProcessorType(ParamProcessor processor) {
        this.processor = processor;
    }

    public void fire(Dao dao, String nameObject, Date fromDate, Date toDate) {
        getProcessor().process(dao, nameObject, fromDate, toDate);
    }

    public ParamProcessor getProcessor() {
        return processor;
    }
}
