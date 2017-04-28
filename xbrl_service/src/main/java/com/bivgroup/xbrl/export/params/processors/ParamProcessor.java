package com.bivgroup.xbrl.export.params.processors;

import com.bivgroup.xbrl.dao.Dao;

import java.util.Date;

/**
 * Created by andreykus on 12.03.2017.
 */
public interface ParamProcessor {

    void process(Dao dao, String nameObject, Date fromDate, Date toDate);

}
