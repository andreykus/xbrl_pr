package com.bivgroup.xbrl.instance.elements;

import com.bivgroup.xbrl.instance.elements.enums.PeriodType;

import java.util.Date;
import java.util.UUID;

/**
 * Created by andreykus on 20.03.2017.
 */
public class Context {

    public final static String PREFIX = "C_";
    private String idContext;
    private String url;
    private Date startDate;
    private Date endDate;
    private Date instantDate;
    private PeriodType type;
    private String name;
    private String uuid;



    public String getUuid() {
        if (uuid == null){uuid = PREFIX + UUID.randomUUID().toString();}
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getIdContext() {
        return idContext;
    }

    public void setIdContext(String idContext) {
        this.idContext = idContext;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getInstantDate() {
        return instantDate;
    }

    public void setInstantDate(Date instantDate) {
        this.instantDate = instantDate;
    }

    public PeriodType getType() {
        return type;
    }

    public void setType(PeriodType type) {
        this.type = type;
    }

}
