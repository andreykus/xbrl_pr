package com.bivgroup.xbrl.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * taxonomy , table XBRL_TAXONOMY
 * Created by andreykus on 18.03.2017.
 */
@Entity
@Table(name = "XBRL_TAXONOMY")
public class XbrlTaxonomy {
    /**
     * identificator
     */
    @GenericGenerator(name = "generator", strategy = "enhanced-sequence", parameters = {@Parameter(name = "optimizer", value = "pooled"), @Parameter(name = "prefer_sequence_per_entity", value = "true"), @Parameter(name = "jpa_entity_name", value = "XBRL_TAXONOMY"), @Parameter(name = "increment_size", value = "10"), @Parameter(name = "sequence_per_entity_suffix", value = "_SEQ")})
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    private long id;
    /**
     * name taxonomy
     */
    @Column(name = "NAME")
    private String name;
    /**
     * faile taxonomy
     */
    @Column(name = "FILE_NAME")
    private String fileName;

    /**
     * default constructor
     */
    public XbrlTaxonomy() {
    }

    public XbrlTaxonomy(String name, String fileName) {
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
