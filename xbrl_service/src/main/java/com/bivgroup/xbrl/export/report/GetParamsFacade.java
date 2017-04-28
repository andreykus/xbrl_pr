package com.bivgroup.xbrl.export.report;

import com.bivgroup.xbrl.entity.XbrlParamValue;
import com.bivgroup.xbrl.entity.XbrlPeriod;
import com.bivgroup.xbrl.entity.XbrlTaxonomy;
import com.bivgroup.xbrl.instance.elements.Context;
import com.bivgroup.xbrl.instance.elements.Element;
import com.bivgroup.xbrl.instance.elements.InstanceXbrl;
import com.bivgroup.xbrl.instance.elements.enums.Measurement;
import com.bivgroup.xbrl.instance.elements.enums.PeriodType;
import com.bivgroup.xbrl.instance.elements.enums.TypeElement;
import com.bivgroup.xbrl.instance.GeneratorProvider;
import com.bivgroup.xbrl.dao.Dao;
import com.bivgroup.xbrl.dao.XbrlDao;
import com.bivgroup.xbrl.entity.PrepareProcessor;
import com.bivgroup.xbrl.export.params.visitors.impl.ParamsProcessor;
import com.bivgroup.xbrl.instance.Creator;
import com.bivgroup.xbrl.instance.TypeGenerator;
import com.bivgroup.xbrl.instance.namespace.CbrNameSpace;
import com.bivgroup.xbrl.instance.namespace.IfrsNameSpace;
import com.bivgroup.xbrl.instance.namespace.SchemaConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * Facade create xbrl report
 * Created by andreykus on 12.03.2017.
 */
@Stateless
public class GetParamsFacade {
    /**
     * logger
     */
    protected Logger logger = LogManager.getLogger(this.getClass());
    /**
     * name company
     */
    private static String COMPANY_NAME = "MyCompany";
    /**
     * inner params for generator xbrl
     */
    private List<Context> contexts;
    /**
     * measurement for  report
     */
    List<Measurement> units;

    /**
     * tools for work BD
     */
    @Inject
    private Dao dao;

    /**
     * generator report xbrl
     */
    @GeneratorProvider(type = TypeGenerator.XWAND)
    @Inject
    public Creator generator;

    /**
     * stack parocessors -- getting values fix element
     */
    private ParamsProcessor prepareProcessor;

    /**
     * get tools for work BD
     *
     * @return dao
     */
    public Dao getDao() {
        return dao;
    }

    /**
     * Constructor Facade create xbrl report
     */
    public GetParamsFacade() {
        this.dao = new XbrlDao();
        contexts = new ArrayList<Context>();
        units = new ArrayList<Measurement>();
        prepareProcessor = new ParamsProcessor();
    }

    /**
     * fill param value by stack parocessors
     *
     * @param fromDate - start date
     * @param toDate   - end date
     */
    public void prepare(Date fromDate, Date toDate) {
        dao.emptyParams();
        List<PrepareProcessor> processors = dao.getProcessors();
        prepareProcessor.process(processors, dao, fromDate, toDate);
    }


    /**
     * Create XBRL report
     *
     * @param stream     - out stream
     * @param idPeriod   - id period
     * @param idTaxonomy - id taxonomy
     * @throws Exception - exception
     */
    public void createReport(OutputStream stream, Long idPeriod, Long idTaxonomy) throws Exception {

        InstanceXbrl xbrl = getXbrlInstance(idPeriod, idTaxonomy);

        Object is = generator.getInstance(xbrl);
        StringBuffer buf = (StringBuffer) is;
        stream.write(buf.toString().getBytes());
    }

    /**
     * Create Contexts XBRL report
     *
     * @param idPeriod   - id period
     * @param idTaxonomy - id taxonomy
     * @throws Exception - exception
     */
    public void createContexts(Long idPeriod, Long idTaxonomy) throws Exception {
        InstanceXbrl xbrl = getXbrlInstance(idPeriod, idTaxonomy);
        generator.getContexts(xbrl);
    }

    /**
     * get Instance object for XBRL report
     *
     * @param idPeriod   - id period
     * @param idTaxonomy - id taxonomy
     * @return - report
     * @throws Exception - exception
     */
    private InstanceXbrl getXbrlInstance(Long idPeriod, Long idTaxonomy) throws Exception {
        //get Data: Values,Period,Taxonomy
        XbrlTaxonomy taxonomy = dao.getTaxonomyById(idTaxonomy);
        XbrlPeriod period = dao.getPeriodById(idPeriod);
        List<XbrlParamValue> values = dao.getValues(idPeriod, idTaxonomy);
        InstanceXbrl xbrl = new InstanceXbrl();
        List<Context> contexts = getContexts(period);
        List<Measurement> measurements = getUnits();
        Map<String, String> namespaces = getNameSpace();
        xbrl.setContexts(contexts);
        xbrl.setUnits(getUnits());
        xbrl.setNamespaces(getNameSpace());
        xbrl.setElements(getElements(values, contexts, measurements, namespaces));
        xbrl.setPathTaxonomy(taxonomy.getFileName());
        return xbrl;
    }

    /**
     * get name spaces for report
     *
     * @return - collection name spaces
     */
    private Map<String, String> getNameSpace() {
        Map ns = new HashMap<String, String>();
        for (CbrNameSpace n : CbrNameSpace.values()) {
            ns.put(n.getNameSpace(), n.getUrl());
        }
        for (IfrsNameSpace n : IfrsNameSpace.values()) {
            ns.put(n.getNameSpace(), n.getUrl());
        }
        return ns;
    }

    /**
     * get measurements for report
     *
     * @return - collection measurement
     */
    private List<Measurement> getUnits() {
        List<Measurement> units = new ArrayList<Measurement>();
        for (Measurement u : Measurement.values()) {
            units.add(u); //TODO гененрить идентификаторы и помнить
        }
        return units;
    }

    /**
     * get context for report
     *
     * @param period - period
     * @return - contexts
     */
    private List<Context> getContexts(XbrlPeriod period) {
        if (period.getOnDate() != null) {
            Context context = new Context();
            context.setIdContext(context.getUuid()); //TODO гененрить и помнить
            context.setName(COMPANY_NAME); //TODO ...
            context.setUrl(SchemaConstant.schemaLocation); //TODO из намеспейса
            context.setType(PeriodType.INSTANT);
            context.setInstantDate(period.getOnDate());
            contexts.add(context);
        } else {
            logger.error("period not contains date ON");
        }
        if (period.getDateFrom() != null && period.getDateTo() != null) {
            Context context = new Context();
            context.setIdContext(context.getUuid()); //TODO гененрить и помнить
            context.setName(COMPANY_NAME); //TODO ...
            context.setUrl(SchemaConstant.schemaLocation); //TODO из намеспейса
            context.setType(PeriodType.DURATION); //TODO размножение на 2
            context.setStartDate(period.getDateFrom());
            context.setEndDate(period.getDateTo());
            contexts.add(context);
        } else {
            logger.error("period not contains date FROM or date TO");
        }
        return contexts;
    }

    /**
     * get elemet for report
     *
     * @param values       -  values elements
     * @param contexts     - contexts
     * @param measurements - measurements
     * @param namespaces   - namespaces
     * @return - fix elements
     * @throws Exception
     */
    private List<Element> getElements(List<XbrlParamValue> values, List<Context> contexts, List<Measurement> measurements, Map<String, String> namespaces) throws Exception {
        List<Element> elements = new ArrayList<Element>();
        for (XbrlParamValue value : values) {
            Element element = new Element();
            element.setIdElement(element.getUuid());//TODO гененрить и помнить
            element.setDescription("this description");
            element.setAlias(value.getParam().getConceptPrefix());  //TODO из намеспейса
            element.setName(value.getParam().getConceptName());
            element.setUnit(Measurement.RUR);
            element.setValue(value.getValue()); //TODO приведение к строке от типа
            element.setContext(findContext(contexts, value.getParam().getPeriodType())); //TODO из параметра и определение из памяти
            if (TypeElement.MONEY.equals(value.getParam().getConceptType())) {
                element.setPrecision(5);
            }
            elements.add(element);
        }
        return elements;
    }

    /**
     * find context in cash by period
     *
     * @param contexts - contexts
     * @param period   - period
     * @return - context
     * @throws Exception
     */
    private Context findContext(List<Context> contexts, PeriodType period) throws Exception {
        for (Context context : contexts) {
            if (context.getType().equals(period)) return context;
        }
        throw new Exception("not found context for this period");
    }

    /**
     * test from file
     *
     * @param stream
     * @throws Exception
     */
    public void createReport(OutputStream stream) throws Exception {
        InputStream is = new FileInputStream("c:\\projects\\xbrl\\xbrl_export\\run.bat");
        int len;
        byte[] buffer = new byte[4096];
        while ((len = is.read(buffer, 0, buffer.length)) != -1) {
            stream.write(buffer, 0, len);
        }
        is.close();
    }

}
