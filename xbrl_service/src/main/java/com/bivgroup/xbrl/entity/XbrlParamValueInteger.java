package com.bivgroup.xbrl.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * value element , type - Duble
 * Created by bush on 21.03.2017.
 */
@Entity
@DiscriminatorValue("Integer")
public class XbrlParamValueInteger extends XbrlParamValue {
    /**
     * value
     */
    @Column(name = "VALUE_INTEGER")
    private Integer value;

    public XbrlParamValueInteger() {
    }

    public XbrlParamValueInteger(Integer value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setValue(Object name) {
        this.value = value;
    }
}

