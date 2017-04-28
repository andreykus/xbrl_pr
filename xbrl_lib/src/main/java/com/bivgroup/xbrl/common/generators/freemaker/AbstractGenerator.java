package com.bivgroup.xbrl.common.generators.freemaker;


import com.bivgroup.xbrl.common.generators.GeneratorException;
import com.bivgroup.xbrl.common.generators.InnerTool;
import com.bivgroup.xbrl.common.generators.ObjectGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Abstract class generator document (template)
 * Created by bush on 15.09.2016.
 *
 * @param <T> - type in param
 * @param <V> - type inner tool
 */
abstract class AbstractGenerator<T, V extends InnerTool> implements ObjectGenerator<T, V> {
    /**
     * logger for class
     */
    protected Logger logger = LogManager.getLogger(this.getClass());
    /**
     * output source , in ths put generated dociment
     */
    private StringBuffer outputStream;
    /**
     * strat template
     */
    private String templateName;
    /**
     * template path
     */
    protected String[] templatePaths = new String[0];

    private Properties properties = new Properties();
    private Iterator<Map.Entry<Object, Object>> iterator;
    /**
     * inner tool for template , use addition function describe in remote modules
     */
    private InnerTool tool;

    /**
     * Engine generator document
     */
    private TemplateHelper vh;

    /**
     * super Conxtructor generator
     *
     * @param templateName - template
     * @param tool         - inner tool
     */
    public AbstractGenerator(String templateName, InnerTool tool) {
        this.tool = tool;
        this.outputStream = new StringBuffer();
        this.templateName = templateName;
    }

    /**
     * get generated document
     *
     * @return - output source
     */
    public StringBuffer getOutputStream() {
        return this.outputStream;
    }

    /**
     * get name template
     *
     * @return - template
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * set template
     *
     * @param templateName - template
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * upper generator method
     *
     * @param params - in params
     * @return - document
     * @throws GeneratorException - exception generator
     */
    @Override
    public Object generate(T params) throws GeneratorException {
        //new engine
        setTemplateHelper(new TemplateHelper());
        //path templates
        setupTemplates();
        //init context
        setupContext();
        //template moethod , need contains in extendet class
        doStart(params, templateName);
        //clean context
        cleanUpContext();
        //clean engine
        setTemplateHelper(null);
        return outputStream;
    }

    /**
     * template moethod , need contains in extendet class
     *
     * @param context       - in params
     * @param templateName- template name
     * @throws GeneratorException - exception generator
     */
    abstract protected void doStart(T context, String templateName) throws GeneratorException;

    /**
     * get template paths
     *
     * @return - path
     */
    public String[] getTemplatePaths() {
        return templatePaths;
    }

    /**
     * return generated object as string
     *
     * @param a
     * @return
     */
    static String toString(Object[] a) {
        if (a == null)
            return "null";
        if (a.length == 0)
            return "[]";

        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < a.length; i++) {
            if (i == 0)
                buf.append('[');
            else
                buf.append(", ");

            buf.append(String.valueOf(a[i]));
        }

        buf.append("]");
        return buf.toString();
    }

    /**
     * initialization path to templates for helper
     *
     * @throws GeneratorException - exception generator
     */
    protected void setupTemplates() throws GeneratorException {
        if (logger.isDebugEnabled()) {
            logger.debug(getClass().getName() + " path: " + toString(templatePaths));
        }
        getTemplateHelper().init(getOutputStream(), templatePaths);
    }

    /**
     * init default inner param, add params from Properties, add to engine generator
     *
     * @throws GeneratorException - exception generator
     */
    protected void setupContext() throws GeneratorException {
        getTemplateHelper().setupContext();
        getTemplateHelper().putInContext(Constants.EXPORTER, this);
        getTemplateHelper().putInContext(Constants.TOOL, getInnerTool());
        if (getOutputStream() != null) getTemplateHelper().putInContext(Constants.OUTPUT_DIR, getOutputStream());
        if (getTemplatePaths() != null) getTemplateHelper().putInContext(Constants.TEMPLATE_PATH, getTemplatePaths());
        if (getProperties() != null) {
            iterator = getProperties().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Object, Object> element = iterator.next();
                String key = element.getKey().toString();
                Object value = transformValue(element.getValue());
                getTemplateHelper().putInContext(key, value);
            }
        }
    }

    // called to have "true"/"false" strings returned as real booleans in templates code.
    private Object transformValue(Object value) {
        if ("true".equals(value)) {
            return Boolean.TRUE;
        }
        if ("false".equals(value)) {
            return Boolean.FALSE;
        }
        return value;
    }

    /**
     * clean in params, remove from engine generator
     *
     * @throws GeneratorException - exception generator
     */
    protected void cleanUpContext() throws GeneratorException {
        if (getProperties() != null) {
            iterator = getProperties().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Object, Object> element = iterator.next();
                Object value = transformValue(element.getValue());
                String key = element.getKey().toString();
                getTemplateHelper().removeFromContext(key, value);
            }
        }
        if (getOutputStream() != null) getTemplateHelper().removeFromContext(Constants.OUTPUT_DIR, getOutputStream());
        if (getTemplatePaths() != null)
            getTemplateHelper().removeFromContext(Constants.TEMPLATE_PATH, getTemplatePaths());
        getTemplateHelper().removeFromContext(Constants.EXPORTER, this);
        getTemplateHelper().removeFromContext(Constants.TOOL, getInnerTool());

    }

    /**
     * set engine generator
     *
     * @param vh - engine generator
     */
    protected void setTemplateHelper(TemplateHelper vh) {
        this.vh = vh;
    }

    /**
     * get engine generator
     *
     * @return - engine generator
     */
    protected TemplateHelper getTemplateHelper() {
        return vh;
    }

    /**
     * get Properties
     *
     * @return - properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * get class name
     *
     * @return
     */
    public String getName() {
        return this.getClass().getName();
    }

    /**
     * get inner tool
     *
     * @return - tool
     */
    public InnerTool getInnerTool() {
        return tool;
    }

}


