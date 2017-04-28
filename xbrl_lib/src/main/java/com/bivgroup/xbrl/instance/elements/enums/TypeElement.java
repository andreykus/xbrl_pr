package com.bivgroup.xbrl.instance.elements.enums;

import com.bivgroup.xbrl.instance.namespace.XbrlNameSpace;

/**
 * Type value element report xbrl
 * Created by andreykus on 20.03.2017.
 */
public enum TypeElement {
    /**
     * string
     */
    STRING("escapedItemType", XbrlNameSpace.nonnum),
    /**
     * integer
     */
    INTEGER("integerItemType", XbrlNameSpace.xbrli),
    /**
     * money
     */
    MONEY("monetaryItemType", XbrlNameSpace.xbrli);
    /**
     * alias
     */
    private String nameType;
    /**
     * url
     */
    private XbrlNameSpace url;

    TypeElement(String namePeriod, XbrlNameSpace url) {
        this.nameType = namePeriod;
        this.url = url;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public XbrlNameSpace getUrl() {
        return url;
    }

    public void setUrl(XbrlNameSpace url) {
        this.url = url;
    }
}
