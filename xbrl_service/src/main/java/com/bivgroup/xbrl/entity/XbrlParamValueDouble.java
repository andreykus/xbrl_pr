package com.bivgroup.xbrl.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * value element , type - Duble
 * Created by andreykus on 12.03.2017.
 */

@Entity
@DiscriminatorValue("Double")
public class XbrlParamValueDouble extends XbrlParamValue {
    /**
     * value
     */
    @Column(name = "VALUE_DUBLE")
    private Double value;

    public XbrlParamValueDouble() {
    }

    public XbrlParamValueDouble(Double value) {
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
