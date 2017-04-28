package com.bivgroup.xbrl.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * value element , table XBRL_PARAMS_VALUES
 * Created by bush on 10.03.2017.
 */

@Entity
@Table(name = "XBRL_PARAMS_VALUES"
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "TYPE",
        discriminatorType = DiscriminatorType.STRING)
public class XbrlParamValue implements java.io.Serializable, Value {
    /**
     * identificator
     */
    @GenericGenerator(name = "generator", strategy = "enhanced-sequence", parameters = {@Parameter(name = "optimizer", value = "pooled"), @Parameter(name = "prefer_sequence_per_entity", value = "true"), @Parameter(name = "jpa_entity_name", value = "XBRL_PARAMS_VALUES"), @Parameter(name = "increment_size", value = "10"), @Parameter(name = "sequence_per_entity_suffix", value = "_SEQ")})
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    private long id;
    /**
     * name param value
     */
    @Column(name = "NAME")
    private String name;
    /**
     * reference to element
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARAM_ID", nullable = false)
    private XbrlParam param;
    /**
     * reference to period
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERIOD_ID", nullable = false)
    private XbrlPeriod period;

    public XbrlParamValue() {
    }

    public XbrlParamValue(String name) {
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

    public XbrlParam getParam() {
        return this.param;
    }

    public void setParam(XbrlParam param) {
        this.param = param;
    }

    public XbrlPeriod getPeriod() {
        return this.period;
    }

    public void setPeriod(XbrlPeriod period) {
        this.period = period;
    }


    @Override
    public void setValue(Object value) {
    }

    @Override
    public Object getValue() {
        return null;
    }
}



