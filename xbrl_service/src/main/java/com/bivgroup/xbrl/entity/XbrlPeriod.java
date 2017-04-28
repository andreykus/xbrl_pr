package com.bivgroup.xbrl.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

/**
 * period report , table XBRL_PERIOD
 * Created by bush on 13.03.2017.
 */
@Entity
@Table(name = "XBRL_PERIOD")
public class XbrlPeriod {
    /**
     * identificator
     */
    @GenericGenerator(name = "generator", strategy = "enhanced-sequence", parameters = {@Parameter(name = "optimizer", value = "pooled"), @Parameter(name = "prefer_sequence_per_entity", value = "true"), @Parameter(name = "jpa_entity_name", value = "XBRL_PERIOD"), @Parameter(name = "increment_size", value = "10"), @Parameter(name = "sequence_per_entity_suffix", value = "_SEQ")})
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    private long id;

    @Column(name = "NAME")
    private String name;
    /**
     * from date report
     */
    @Column(name = "DATE_FROM")
    private Date dateFrom;
    /**
     * to date report
     */
    @Column(name = "DATE_TO")
    private Date dateTo;
    /**
     * on date report
     */
    @Column(name = "DATE_ON")
    private Date onDate;

    /**
     * default constructor
     */
    public XbrlPeriod() {
    }

    public XbrlPeriod(String name) {
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Date getOnDate() {
        return onDate;
    }

    public void setOnDate(Date onDate) {
        this.onDate = onDate;
    }

}
