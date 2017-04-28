package com.bivgroup.xbrl.common.generators.freemaker;

import com.bivgroup.xbrl.common.generators.GeneratorException;
import com.bivgroup.xbrl.common.generators.InnerTool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;


/**
 * Freemarker generator document
 * Created by andreykus on 18.03.2017.
 *
 * @param <T> - type in param
 * @param <V> - type inner tool
 */
public class FreemakerGenerator<T extends Map, V extends InnerTool> extends AbstractGenerator<T, V> {
    /**
     * logger
     */
    protected Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Constructor Freemarker generator document
     *
     * @param templateName - start template name
     * @param tool         - inner tool
     */
    public FreemakerGenerator(String templateName, V tool) {
        super(templateName, tool);
    }

    /**
     * init template and generate document
     *
     * @param params       - in params
     * @param templateName - start template name
     * @throws GeneratorException - exception generator
     */
    public void doStart(Map params, String templateName) throws GeneratorException {
        setTemplateName(templateName);
        export(params);
    }

    /**
     * set param (context)
     *
     * @throws GeneratorException - exception generator
     */
    protected void setupContext() throws GeneratorException {
        super.setupContext();

    }

    /**
     * generate document
     *
     * @param context - in params
     * @throws GeneratorException - exception generator
     */
    private void export(Map context) throws GeneratorException {
        TemplateProducer producer = new TemplateProducer(getTemplateHelper());
        producer.produce(context, getTemplateName(), getOutputStream(), "General Settings");
    }

}
