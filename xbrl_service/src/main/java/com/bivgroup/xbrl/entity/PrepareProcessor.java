package com.bivgroup.xbrl.entity;

import com.bivgroup.xbrl.export.params.processors.ProcessorType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * Processor , table PREPARE_PROCESSORS
 * Created by bush on 10.03.2017.
 */

@Entity
@Table(name = "PREPARE_PROCESSORS"
)
public class PrepareProcessor implements java.io.Serializable {
    /**
     * id processor
     */
    private long id;
    /**
     * name processor
     */
    private String name;
    /**
     * system name processr
     */
    private String sysName;
    /**
     * type processor (class, procedure)
     */
    private ProcessorType type;

    public PrepareProcessor() {
    }

    public PrepareProcessor(String name, ProcessorType type) {
        this.name = name;
        this.type = type;
    }

    @GenericGenerator(name = "generator", strategy = "enhanced-sequence", parameters = {@Parameter(name = "optimizer", value = "pooled"), @Parameter(name = "prefer_sequence_per_entity", value = "true"), @Parameter(name = "jpa_entity_name", value = "PREPARE_PROCESSORS"), @Parameter(name = "increment_size", value = "10"), @Parameter(name = "sequence_per_entity_suffix", value = "_SEQ")})
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ValueID", nullable = false, insertable = false, updatable = false)
    public long getID() {
        return this.id;
    }

    public void setID(long id) {
        this.id = id;
    }

    @Column(name = "SysName",nullable = false)
    public String getSysName() {
        return this.sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    @Column(name = "Name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "TypeProcessor",nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public ProcessorType getType() {
        return type;
    }

    public void setType(ProcessorType type) {
        this.type = type;
    }
}
