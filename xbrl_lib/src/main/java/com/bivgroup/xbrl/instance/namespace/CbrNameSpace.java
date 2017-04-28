package com.bivgroup.xbrl.instance.namespace;

/**
 * namespace for cbr
 * Created by bush on 16.03.2017.
 */
public enum CbrNameSpace {

    EP_PURCB("EP_PURCB", "http://www.cbr.ru/xbrl/2017-01-01/ifrs-ru/EP/EP_PURCB"),
    cbr_coa_("cbr-coa", "http://www.cbr.ru/xbrl/2017-01-01/cbr-coa/dic"),
    cbr_coa_dic("cbr-coa-dic", "http://www.cbr.ru/xbrl/2017-01-01/cbr-coa/dic"),
    cbr_ref("cbr-ref", "http://www.cbr.ru/xbrl/2017-01-01/cbr/ref"),
    /**
     * valute units
     */
    iso4217("iso4217", "http://www.xbrl.org/2003/iso4217");

    /**
     * alias - namespace
     */
    private String nameSpace;

    /**
     * url - namespace
     */
    private String url;

    /**
     * Constructor cbr namespace
     *
     * @param nameSpace - alias
     * @param url       - url
     */
    CbrNameSpace(String nameSpace, String url) {
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
