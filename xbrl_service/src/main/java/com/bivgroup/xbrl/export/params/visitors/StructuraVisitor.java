package com.bivgroup.xbrl.export.params.visitors;


import com.bivgroup.xbrl.dao.Dao;

import java.util.Date;

/**
 *
 * Created by andreykus on 18.09.2016.
 */
public interface StructuraVisitor<E> {
     void visit(E element, Dao dao, Date fromDate, Date toDate) ;
}
