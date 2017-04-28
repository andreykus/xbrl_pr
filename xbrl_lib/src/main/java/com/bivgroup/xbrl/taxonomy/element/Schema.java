package com.bivgroup.xbrl.taxonomy.element;
import org.w3c.dom.*;

import java.util.Vector;

/**
 * Created by bush on 24.03.2017.
 */
public class Schema {
    protected String PREFIX;
    protected String URI;
    Vector PREFIXS=new Vector();
    Vector URIS=new Vector();
    protected Vector hv=new Vector();

    void load(String schemaName, Element element, Vector vector) throws Exception{

    };



}
