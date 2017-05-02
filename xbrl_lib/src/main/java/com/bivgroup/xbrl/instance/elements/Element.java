package com.bivgroup.xbrl.instance.elements;

import com.bivgroup.xbrl.instance.elements.enums.TypeElement;
import com.bivgroup.xbrl.instance.elements.enums.Measurement;

import java.util.UUID;

/**
 * inner generate fact value element xbrl document
 * Created by andreykus on 20.03.2017.
 */
public class Element {
    public final static String PREFIX = "E_";
    private String name;
    private TypeElement type;
    private String idElement;
    private String description;
    private Context context;
    private Object value;
    private Integer precision;
    private String alias;
    private Measurement unit;

    private String uuid;

    public String getUuid() {
        if (uuid == null){uuid = PREFIX + UUID.randomUUID().toString();}
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Measurement getUnit() {
        return unit;
    }

    public void setUnit(Measurement unit) {
        this.unit = unit;
    }

    public TypeElement getType() {
        return type;
    }

    public void setType(TypeElement type) {
        this.type = type;
    }

    public String getIdElement() {
        return idElement;
    }

    public void setIdElement(String idElement) {
        this.idElement = idElement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
