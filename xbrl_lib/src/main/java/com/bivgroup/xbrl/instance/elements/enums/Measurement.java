package com.bivgroup.xbrl.instance.elements.enums;

import com.bivgroup.xbrl.instance.namespace.CbrNameSpace;

/**
 * Created by andreykus on 19.03.2017.
 */
public enum Measurement {
    RUR("RUB","rur",CbrNameSpace.iso4217),
    USD("USD","usd",CbrNameSpace.iso4217);

    private final static String PREFIX = "U_";
    private String nameUnit;
    private String idUnit;
    private CbrNameSpace url;

    Measurement(String nameUnit, String idUnit, CbrNameSpace url) {
        this.nameUnit = nameUnit;
        this.idUnit = idUnit;
        this.url = url;
    }

    public String getNameUnit() {
        return nameUnit;
    }

    public void setNameUnit(String nameUnit) {
        this.nameUnit = nameUnit;
    }

    public CbrNameSpace getUrl() {
        return url;
    }

    public void setUrl(CbrNameSpace url) {
        this.url = url;
    }

    public String getIdUnit() {
        return idUnit;
    }

    public void setIdUnit(String idUnit) {
        this.idUnit = idUnit;
    }

    public Measurement getById(String id) throws Exception{
        switch (idUnit) {
            case "u1":
                return RUR;
            case "u2":
                return USD;
            default:
                throw new Exception("Not contains this measurement");
        }
    }
}
