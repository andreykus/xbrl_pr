package com.bivgroup.xbrl.instance.elements;

import com.bivgroup.xbrl.instance.elements.enums.Measurement;

import java.util.List;
import java.util.Map;

/**
 * inner generator xbrl document
 * Created by bush on 21.03.2017.
 */
public class InstanceXbrl {

    public Map<String, String> getNamespaces() {
        return namespaces;
    }

    public void setNamespaces(Map<String, String> namespaces) {
        this.namespaces = namespaces;
    }

    public List<Measurement> getUnits() {
        return units;
    }

    public void setUnits(List<Measurement> units) {
        this.units = units;
    }

    public List<Context> getContexts() {
        return contexts;
    }

    public void setContexts( List<Context> contexts) {
        this.contexts = contexts;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public String getPathTaxonomy() {
        return pathTaxonomy;
    }

    public void setPathTaxonomy(String pathTaxonomy) {
        this.pathTaxonomy = pathTaxonomy;
    }
    private Map <String,String> namespaces;
    private List<Measurement> units = null;
    private List<Context> contexts = null;
    private List<Element> elements = null;
    private String pathTaxonomy =  null;
}
