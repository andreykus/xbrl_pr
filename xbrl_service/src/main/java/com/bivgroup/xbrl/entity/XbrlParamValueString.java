package com.bivgroup.xbrl.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * value element , type - Duble
 * Created by bush on 10.03.2017.
 */

@Entity
@DiscriminatorValue("String")
public class XbrlParamValueString extends XbrlParamValue {
    /**
     * value
     */
    @Column(name = "VALUE_STRING")
    private String value;

    public XbrlParamValueString() {
    }

    public XbrlParamValueString(String value) {
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
