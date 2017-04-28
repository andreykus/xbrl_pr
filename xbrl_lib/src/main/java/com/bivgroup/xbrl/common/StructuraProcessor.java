package com.bivgroup.xbrl.common;


import java.util.List;

/**
 * Created by andreykus on 12.03.2017.
 */
public interface StructuraProcessor<E, V extends StructuraVisitor> {
    void setVisitor(V visitor);

    void process(List<E> structura);
}
