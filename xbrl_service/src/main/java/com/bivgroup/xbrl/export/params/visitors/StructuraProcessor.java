package com.bivgroup.xbrl.export.params.visitors;

import com.bivgroup.xbrl.dao.Dao;

import java.util.Date;
import java.util.List;

/**
 * Created by andreykus on 12.03.2017.
 */
public interface StructuraProcessor<E, V extends StructuraVisitor> {
    void setVisitor(V visitor);
    void process(List<E> structura, Dao dao, Date fromDate, Date toDate);
}
