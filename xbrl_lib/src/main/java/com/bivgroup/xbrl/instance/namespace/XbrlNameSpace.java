package com.bivgroup.xbrl.instance.namespace;

/**
 * namespace for Xbrl
 * Created by bush on 15.03.2017.
 */
public enum XbrlNameSpace {
    reference("reference", "http://www.xbrl.org/2009/role/reference"),
    pf("pf", "http://xbrl.org/2008/filter/period"),
    formula("formula", "http://xbrl.org/2008/formula"),
    negated("negated", "http://www.xbrl.org/2009/role/negated"),
    xbrldt("xbrldt", "http://xbrl.org/2005/xbrldt"),
    enums("enum", "http://xbrl.org/2014/extensible-enumerations"),
    ref("ref", "http://www.xbrl.org/2006/ref"),
    ver("ver", "http://xbrl.org/2013/versioning-base"),
    variable("variable", "http://xbrl.org/2008/variable"),
    vercu("vercu", "http://xbrl.org/2013/versioning-concept-use"),
    msg("msg", "http://xbrl.org/2010/message"),
    valm("valm", "http://xbrl.org/2010/message/validation"),
    vercd("vercd", "http://xbrl.org/2013/versioning-concept-details"),
    xbrli("xbrli", "http://www.xbrl.org/2003/instance"),
    validation("validation", "http://xbrl.org/2008/validation"),
    ca("ca", "http://xbrl.org/2008/assertion/consistency"),
    cf("cf", "http://xbrl.org/2008/filter/concept"),
    xbrldi("xbrldi", "http://xbrl.org/2006/xbrldi"),
    label("label", "http://xbrl.org/2008/label"),
    net("net", "http://www.xbrl.org/2009/role/net"),
    xl("xl", "http://www.xbrl.org/2003/XLink"),
    /**
     * type element
     */
    nonnum("nonnum", "http://www.xbrl.org/dtr/type/non-numeric"),
    /**
     * valute
     */
    iso4217("iso4217", "http://www.xbrl.org/2003/iso4217"),
    verdim("verdim", "http://xbrl.org/2013/versioning-dimensions"),
    link("link", "http://www.xbrl.org/2003/linkbase"),
    va("va", "http://xbrl.org/2008/assertion/value"),
    num("num", "http://www.xbrl.org/dtr/type/numeric"),
    df("df", "http://xbrl.org/2008/filter/dimension"),
    reference_2("reference_2", "http://xbrl.org/2008/reference"),
    ea("ea", "http://xbrl.org/2008/assertion/existence"),
    gen("gen", "http://xbrl.org/2008/generic");

    /**
     * alias - namespace
     */
    private String nameSpace;

    /**
     * url - namespace
     */
    private String url;

    /**
     * Constructor Xbrl namespace
     *
     * @param nameSpace - alias
     * @param url       - url
     */
    XbrlNameSpace(String nameSpace, String url) {
        this.nameSpace = nameSpace;
        this.url = url;
    }

    /**
     * get alias name space
     *
     * @return - alias
     */
    public String getNameSpace() {
        return nameSpace;
    }

    /**
     * set alias name space
     *
     * @param nameSpace - alias
     */
    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    /**
     * get url namespace
     * @return -  url
     */
    public String getUrl() {
        return url;
    }

    /**
     * set url namespace
     * @param url - url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
