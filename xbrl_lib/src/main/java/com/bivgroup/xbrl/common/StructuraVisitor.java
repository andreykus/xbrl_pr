package com.bivgroup.xbrl.common;


/**
 * Created by andreykus on 18.09.2016.
 */
public interface StructuraVisitor<E> {
    void visit(E element);
}
