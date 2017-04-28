package com.bivgroup.xbrl.instance.impl.freemaker;

import com.bivgroup.xbrl.common.generators.GeneratorException;
import com.bivgroup.xbrl.common.generators.ObjectGenerator;
import com.bivgroup.xbrl.common.generators.freemaker.FreemakerGenerator;
import com.bivgroup.xbrl.instance.InstanceXbrlException;
import com.bivgroup.xbrl.instance.Creator;
import com.bivgroup.xbrl.instance.elements.InstanceXbrl;
import com.bivgroup.xbrl.instance.GeneratorProvider;
import com.bivgroup.xbrl.instance.TypeGenerator;
import com.bivgroup.xbrl.instance.impl.freemaker.generator.InnerTemplateTool;
import com.bivgroup.xbrl.instance.namespace.SchemaConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Implementation creator report XMRL - FREEMARKER
 * Created by bush on 17.03.2017.
 */

//use Freemarker impl
@GeneratorProvider(type = TypeGenerator.FREEMARKER)
public class CreatorXbrlFreeMarker implements Creator {
    /**
     * logger
     */
    protected Logger logger = LogManager.getLogger(this.getClass());

//    @Inject
//    @BundleProvider
//    private ResourceBundle bundle;

    /**
     * start tempalte file
     */
    final static String START_TEMPLATE = "templates/xbrl/xbrl.ftl";
    /**
     * name param - element
     */
    final static String PARAM_LIST = "elements";
    /**
     * name param - shemaref
     */
    final static String SHEMA_REF = "shemaref";
    /**
     * name param - shemalocation
     */
    final static String SHEMA_LOCATION = "shemalocation";
    /**
     * name param - units
     */
    final static String UNITS = "units";
    /**
     * name param - contexts
     */
    final static String CONTEXTS = "contexts";
    /**
     * name param - namespaces
     */
    final static String NAME_SPACE = "namespaces";


    /**
     * crate instance report
     * @param params - in params (xbrl object)
     * @return - report
     * @throws GeneratorException - exception generator
     */
    @Override
    public StringBuffer getInstance(Object params) throws InstanceXbrlException {
        StringBuffer stream = (StringBuffer) getXbrlByParam((InstanceXbrl) params);
        logger.debug(stream);
        return stream;
    }

    @Override
    public List getContexts(Object xbrl) throws InstanceXbrlException{
        return null;
    }
    /**
     * init in params, create generator, generate report
     * @param xbrl - xbrl object
     * @return - report
     * @throws GeneratorException - exception generator
     */
    private Object getXbrlByParam(InstanceXbrl xbrl) throws InstanceXbrlException {
        Map context = new HashMap<>();
        context.put(NAME_SPACE, xbrl.getNamespaces());
        context.put(UNITS, xbrl.getUnits());
        context.put(CONTEXTS, xbrl.getContexts());
        context.put(PARAM_LIST, xbrl.getElements());
        context.put(SHEMA_LOCATION, SchemaConstant.schemaLocation);
        context.put(SHEMA_REF, SchemaConstant.file);
        ObjectGenerator generator = new FreemakerGenerator(START_TEMPLATE, new InnerTemplateTool());
        try {
            return generator.generate(context);
        }catch (GeneratorException ex) {
            throw new InstanceXbrlException(ex);
        }

    }


}
