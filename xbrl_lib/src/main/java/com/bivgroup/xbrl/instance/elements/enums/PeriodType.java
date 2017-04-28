package com.bivgroup.xbrl.instance.elements.enums;

/**
 * period type
 * Created by bush on 20.03.2017.
 */
public enum PeriodType {
    /**
     * Duration, need start date, end date
     */
    DURATION("duration"),
    /**
     * Instant, need on date
     */
    INSTANT("instant"),
    /**
     * ALL
     */
    FOREVER("forever");

    private String namePeriod;

    PeriodType(String namePeriod) {
        this.namePeriod = namePeriod;
    }

    public String getNamePeriod() {
        return namePeriod;
    }

    public void setNamePeriod(String nameUnit) {
        this.namePeriod = namePeriod;
    }

}
