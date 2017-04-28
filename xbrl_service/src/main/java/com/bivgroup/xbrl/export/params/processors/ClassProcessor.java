package com.bivgroup.xbrl.export.params.processors;

import com.bivgroup.xbrl.dao.Dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * Created by andreykus on 12.03.2017.
 */
public class ClassProcessor implements ParamProcessor {
    protected Logger logger = LogManager.getLogger(this.getClass());
    @Override
    public void process(Dao dao, String nameObject, Date fromDate, Date toDate) {
        try {
            Class clazz = Class.forName(nameObject);
            ParamProcessor processor = (ParamProcessor) clazz.newInstance();
            processor.process(dao, nameObject, fromDate, toDate);
        } catch (ClassNotFoundException enf) {
            enf.printStackTrace();
        } catch (InstantiationException ei) {
            ei.printStackTrace();
        } catch (IllegalAccessException ea) {
            ea.printStackTrace();
        }
    }
}
