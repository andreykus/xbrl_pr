package com.bivgroup.xbrl.entity;


import com.bivgroup.xbrl.instance.elements.enums.BalanceType;
import com.bivgroup.xbrl.instance.elements.enums.ConceptGroup;
import com.bivgroup.xbrl.instance.elements.enums.PeriodType;
import com.bivgroup.xbrl.instance.elements.enums.TypeElement;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * XBRL ELEMENT, table XBRL_PARAMS
 * Created by bush on 10.03.2017.
 */

@Entity
@Table(name = "XBRL_PARAMS")
public class XbrlParam implements java.io.Serializable {
    /**
     * id element
     */
    @GenericGenerator(name = "generator", strategy = "enhanced-sequence", parameters = {@Parameter(name = "optimizer", value = "pooled"), @Parameter(name = "prefer_sequence_per_entity", value = "true"), @Parameter(name = "jpa_entity_name", value = "XBRL_PARAMS"), @Parameter(name = "increment_size", value = "10"), @Parameter(name = "sequence_per_entity_suffix", value = "_SEQ")})
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    private long id;

    /**
     * parent element
     */
    @ManyToOne
    @JoinColumn(name = "PARENTID", nullable = true)
    private XbrlParam parent;

    /**
     * id external
     */
    @Column(name = "CONCEPT_ID")
    private long conceptId;
    /**
     * alias element
     */
    @Column(name = "CONCEPT_PREFIX")
    private String conceptPrefix;
    /**
     * russion name element
     */
    @Column(name = "RUS_NAME")
    private String rusName;
    /**
     * level in hierarhy tree
     */
    @Column(name = "TREE_LEVEL")
    private Integer treeLevel;

    @Column(name = "ORDER_NUM")
    private Integer orderNum;
    @Column(name = "WEIGHT")
    private Integer weight;
    /**
     * type of element report (string,integer,money)
     */
    @Column(name = "CONCEPT_TYPE")
    @Enumerated(EnumType.ORDINAL)
    private TypeElement conceptType;
    /**
     * group of element report (item,tuple)
     */
    @Column(name = "CONCEPT_GROUP")
    @Enumerated(EnumType.ORDINAL)
    private ConceptGroup conceptGroup;
    @Column(name = "BALANCE_TYPE")
    @Enumerated(EnumType.ORDINAL)
    private BalanceType balanceType;
    /**
     * period type (duration,instant)
     */
    @Column(name = "PERIOD_TYPE")
    @Enumerated(EnumType.ORDINAL)
    private PeriodType periodType;
    @Column(name = "CALC_COUNT")
    private Integer calcCount;
    @Column(name = "PERS_COUNT")
    private Integer presCount;
    @Column(name = "IS_BALANCE")
    private Boolean isBalance;
    @Column(name = "IS_SUBTOTAL")
    private Boolean isSubtotal;
    @Column(name = "REPORT_CODE")
    private String reportCode;
    @Column(name = "REPORT_NAME")
    private String reportName;
    /**
     * name element of report xbrl
     */
    @Column(name = "CONCEPT_NAME")
    private String conceptName;
    /**
     * reference to taxonomy
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TACONOMY_ID", nullable = false)
    private XbrlTaxonomy taxonomy;


    public XbrlParam getParent() {
        return parent;
    }

    public void setParent(XbrlParam parent) {
        this.parent = parent;
    }

    public long getConceptId() {
        return conceptId;
    }

    public void setConceptId(long conceptId) {
        this.conceptId = conceptId;
    }

    public String getConceptPrefix() {
        return conceptPrefix;
    }

    public void setConceptPrefix(String conceptPrefix) {
        this.conceptPrefix = conceptPrefix;
    }

    public String getRusName() {
        return rusName;
    }

    public void setRusName(String rusName) {
        this.rusName = rusName;
    }

    public Integer getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public TypeElement getConceptType() {
        return conceptType;
    }

    public void setConceptType(TypeElement conceptType) {
        this.conceptType = conceptType;
    }

    public ConceptGroup getConceptGroup() {
        return conceptGroup;
    }

    public void setConceptGroup(ConceptGroup conceptGroup) {
        this.conceptGroup = conceptGroup;
    }

    public BalanceType getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(BalanceType balanceType) {
        this.balanceType = balanceType;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }

    public Integer getCalcCount() {
        return calcCount;
    }

    public void setCalcCount(Integer calcCount) {
        this.calcCount = calcCount;
    }

    public Integer getPresCount() {
        return presCount;
    }

    public void setPresCount(Integer presCount) {
        this.presCount = presCount;
    }

    public Boolean getIsBalance() {
        return isBalance;
    }

    public void setIsBalance(Boolean isBalance) {
        this.isBalance = isBalance;
    }

    public Boolean getIsSubtotal() {
        return isSubtotal;
    }

    public void setIsSubtotal(Boolean isSubtotal) {
        this.isSubtotal = isSubtotal;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public XbrlParam() {
    }

    public XbrlParam(String conceptName, XbrlTaxonomy taxonomy) {
        this.conceptName = conceptName;
        this.taxonomy = taxonomy;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConceptName() {
        return this.conceptName;
    }

    public void setConceptName(String name) {
        this.conceptName = conceptName;
    }

    public XbrlTaxonomy getTaxonomy() {
        return this.taxonomy;
    }

    public void setTaxonomy(XbrlTaxonomy taxonomy) {
        this.taxonomy = taxonomy;
    }

}


