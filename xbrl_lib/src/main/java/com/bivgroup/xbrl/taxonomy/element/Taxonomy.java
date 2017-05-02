package com.bivgroup.xbrl.taxonomy.element;

import com.bivgroup.xbrl.taxonomy.LoaderXbrlException;
import com.bivgroup.xbrl.taxonomy.Utils;

import org.w3c.dom.*;

import java.util.Vector;

/**
 * inner process taxonomy
 * Created by bush on 24.03.2017.
 */
public class Taxonomy {

    Schema schema;

    public Taxonomy(String filename) throws LoaderXbrlException {
        this.schema = new Schema();
        load(filename);

    }

    /**
     * load taxonomy from file
     *
     * @param filename - file name
     * @throws LoaderXbrlException
     */
    private void load(String filename) throws LoaderXbrlException {
        try {
            Document document = Utils.loadXML(filename);
            Element element = document.getDocumentElement();

            loadSchema(filename, element);
        } catch (Exception ex) {
            throw new LoaderXbrlException(ex);
        }
    }

    private void loadSchema(String schemaName, Element element) throws Exception {
        Vector vector = new Vector();
        schema.load(schemaName, element, vector);

        for (int i = 0; i < vector.size(); i++) {
            String s1 = (String) vector.get(i);
            {
                try {
                    Document document = Utils.loadXML(s1);
                    document.getDocumentElement();
                } catch (Exception e2) {
                    e2.printStackTrace();
                    System.out.println("Loading linkbase error" + s1);
                }
            }
        }

    }

}
