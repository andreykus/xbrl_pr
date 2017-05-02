package com.bivgroup.xbrl.instance.impl.xwand;

import com.bivgroup.xbrl.common.generators.GeneratorException;
import com.bivgroup.xbrl.common.generators.ObjectGenerator;
import com.bivgroup.xbrl.instance.Creator;
import com.bivgroup.xbrl.instance.GeneratorProvider;
import com.bivgroup.xbrl.instance.InstanceXbrlException;
import com.bivgroup.xbrl.instance.TypeGenerator;
import com.bivgroup.xbrl.instance.elements.Element;
import com.bivgroup.xbrl.instance.elements.InstanceXbrl;
import com.bivgroup.xbrl.instance.elements.enums.Measurement;
import com.bivgroup.xbrl.instance.elements.enums.PeriodType;
import com.bivgroup.xbrl.instance.namespace.CbrNameSpace;
import com.bivgroup.xbrl.instance.namespace.SchemaConstant;
import com.fujitsu.xml.xbrl.dimension.XBRLDimensionProcessor;
import com.fujitsu.xml.xbrl.dimension.instance.DimensionalInstance;
import com.fujitsu.xml.xbrl.dimension.taxonomy.*;
import com.fujitsu.xml.xbrl.xwand.common.*;
import com.fujitsu.xml.xbrl.xwand.instance.*;
import com.fujitsu.xml.xbrl.xwand.processor.XBRLProcessor;
import com.fujitsu.xml.xbrl.xwand.processor.XBRLProcessorException;
import com.fujitsu.xml.xbrl.xwand.taxonomy.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Result;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Implementation creator report XMRL - Fujitsu XWand
 * Created by bush on 28.03.2017.
 */

@GeneratorProvider(type = TypeGenerator.XWAND)
public class CreatorXbrlXWand implements Creator {
    /**
     * logger
     */
    protected Logger logger = LogManager.getLogger(this.getClass());

    static final String XBRLDI = "http://xbrl.org/2006/xbrldi";
    static final String EXPLICIT = "xbrldi:explicitMember";
    static final String TAG_IFRS_RU = "ifrs-ru";
    static final String TAG_DIMENSION = "dimension";
    static final String SYSTEM = "BIVGROUP_XBRL_PROCESSOR";
    private TaxonomySet taxonomy;
    private DimensionalTaxonomy dimensionTaxonomy;
    private XMLSchema rootschema;
    private XBRLDocumentList selfschemas;
    private Instance instance;
    private DimensionalInstance dimensionalInstance;
    private XBRLProcessor xbrlProcessor;
    private XBRLDimensionProcessor dimensionProcessor;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public CreatorXbrlXWand() {
        // XBRL  processor
        this.xbrlProcessor = new XBRLProcessor();
        // Dimension processor
        this.dimensionProcessor = new XBRLDimensionProcessor();

        this.initializeProcessor(this.xbrlProcessor);
    }

    /**
     * init param processor
     *
     * @param xbrlProc
     */
    public void initializeProcessor(XBRLProcessor xbrlProc) {
        //Specifies a unique error handler for the XBRL processor.
        //TODO: xbrlProcessor.setErrorHandler(new XbrlXwandErrorHandler());
        //Specifies a unique error handler for the XBRL Dimension processor.
        //TODO: dimensionProcessor.setErrorHandler(new XbrlDimensionXwandErrorHandler());
    }

    /**
     * Crate instance report
     *
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

    /**
     * Save xbrl to out object
     *
     * @param xbrlDoc - instance xbrl
     * @throws Exception
     */
    private StringBuffer saveToStream(Instance xbrlDoc) throws Exception {
        StringWriter stringWriter = new StringWriter();
        StreamResult result = new StreamResult(stringWriter);
        xbrlProcessor.saveInstance(instance, result);
        return new StringBuffer(stringWriter.toString());

    }


    /**
     * Get all contexts by hyperCube axis
     *
     * @param xbrl - instance xbrl
     * @return - list context
     * @throws InstanceXbrlException - exception
     */
    public List getContexts(Object xbrl) throws InstanceXbrlException {
        if (taxonomy == null) {
            loadTaxonomy(((InstanceXbrl) xbrl).getPathTaxonomy());
        }
        Map<Object, List<Object>> cont = new HashMap<Object, List<Object>>();

        PrimaryItemList rootPrimaryItems = dimensionTaxonomy.getRootPrimaryItems();
        for (int i = 0, n = rootPrimaryItems.size(); i < n; i++) {
            PrimaryItem primaryItem = rootPrimaryItems.get(i);
            HyperCubeProcessor.PrimaryItem.process(primaryItem, cont);

        }
        return null;
    }

    /**
     * Add name space to xbrl
     *
     * @param urlNamespace - url name space
     * @param prefix       - prefix (alias)
     */
    private void addNamespace(String urlNamespace, String prefix) {
        String existNamespace = instance.getNamespacePrefix(urlNamespace);
        if (existNamespace == null) {
            instance.putNamespacePrefix(urlNamespace, prefix);
        }
    }

    /**
     * Add Scenario for axis in Context
     *
     * @param context - context
     */
    private void addScenario(Context context) {
        // scenario
        Scenario scenario = instance.createScenario();
        //add scenario to context
        context.setScenario(scenario);
        DocumentBuilder parser = xbrlProcessor.getParser();
        Document doc = parser.newDocument();
        DocumentFragment fragment = doc.createDocumentFragment();
        // "explicitMember" element
        org.w3c.dom.Element expMemElem = doc.createElementNS(XBRLDI, EXPLICIT);
        // "dimension" attribute
        expMemElem.setAttribute(TAG_DIMENSION, "p0:AreaDim"); //-- dimensionItem
        // member name
        expMemElem.appendChild(doc.createTextNode("p0:JP")); //-- domainMember
        fragment.appendChild(expMemElem);
        scenario.setScenarioElements(fragment);

    }

    /**
     * Add Segment for axis in Context
     *
     * @param cotntext - context
     */
    private void addSegment(Context cotntext) {

        ContextEntity entity = cotntext.getEntity();
        // segment
        Segment segment = instance.createSegment();
        //add segment to entity
        entity.setSegment(segment);
        DocumentBuilder parser = xbrlProcessor.getParser();
        Document doc = parser.newDocument();
        DocumentFragment fragment = doc.createDocumentFragment();
        // "explicitMember" element
        org.w3c.dom.Element expMemElem = doc.createElementNS(XBRLDI, EXPLICIT);
        // "dimension" attribute
        expMemElem.setAttribute(TAG_DIMENSION, "p0:AreaDim"); //-- dimensionItem
        // member name
        expMemElem.appendChild(doc.createTextNode("p0:JP")); //-- domainMember
        fragment.appendChild(expMemElem);
        segment.setSegmentElements(fragment);
    }

    /**
     * Add context to xbrl
     *
     * @param cont - context
     */
    private void addContext(com.bivgroup.xbrl.instance.elements.Context cont) {
        // Add contexts
        Context context = instance.createContext();
        context.setId(cont.getIdContext());
        ContextEntity entity = instance.createContextEntity();
        Identifier identifier = instance.createIdentifier();
        identifier.setValue(cont.getName());
        identifier.setScheme(CbrNameSpace.EP_PURCB.getUrl());

        entity.setIdentifier(identifier);
        context.setEntity(entity);
        if (PeriodType.INSTANT.equals(cont.getType())) {
            Instant instant = instance.createInstant();
            instant.setDate(df.format(cont.getInstantDate()));
            context.setPeriod(instant);
        } else if (PeriodType.DURATION.equals(cont.getType())) {
            Duration duration = instance.createDuration();
            duration.setEndDate(df.format(cont.getEndDate()));
            duration.setStartDate(df.format(cont.getStartDate()));
            context.setPeriod(duration);
        }

        addScenario(context);

        instance.addContext(context);
    }

    /**
     * Add unitts to xbrl
     *
     * @param un - measurement
     */
    private void addUnit(Measurement un) {
        // Add units
        Multiply unit = instance.createMultiply();
        unit.setId(un.getIdUnit());
        //  Measure measure = instance.createMeasure();
        Measure measure = instance.createMeasure(un.getUrl().getUrl(), un.name());
        // measure.setValue(un.getUrl().getUrl(), un.getUrl().getNameSpace());
        unit.addMeasure(measure);
        instance.addUnit(unit);
    }

    /**
     * Add Fact value element to xbrl
     *
     * @param context - context
     * @param unit    - measurement
     * @param value   - value element
     * @param prefix  - url prefix param
     * @param name    - name param
     * @throws Exception - execption
     */
    private void addElement(Context context, Unit unit, String value, String prefix, String name) throws Exception {
        if (context == null) {
            throw new InstanceXbrlException("need set context");
        }
        ElementDecl el = getElement(prefix, name);
        Item nItem = null;
        if (el.isItemDecl()) {
            if (el.isNumericItemDecl()) {
                nItem = instance.createNumericItem(el);
                nItem.setSpecifiedValue(value);
                if (unit == null) {
                    throw new InstanceXbrlException("need set unitRef");
                }
                ((NumericItem) nItem).setUnit(unit);
                nItem.setContext(context);
            } else if (el.isMonetaryItemDecl()) {
                nItem = instance.createNumericItem(el);
                nItem.setSpecifiedValue(value);
                //TODO:((NumericItem) nItem).setPrecision("18");
                if (unit == null) {
                    throw new InstanceXbrlException("need set unitRef");
                }
                ((NumericItem) nItem).setUnit(unit);
                nItem.setContext(context);
            } else {
                nItem = instance.createNonNumericItem(el);
                nItem.setSpecifiedValue(value);
                nItem.setContext(context);
            }
            instance.addChild(nItem);
        } else {
            throw new InstanceXbrlException("not support over type");
        }
    }

    /**
     * get element from taxonomy
     *
     * @param prefix - url prefix param
     * @param name   - name param
     * @return - element
     * @throws ElementNotFoundException - exception not found element
     */
    private ElementDecl getElement(String prefix, String name) throws ElementNotFoundException {
        try {
            XMLSchema schema = null;
            String urlNamespace = rootschema.getNamespaceURI(prefix);
            if (urlNamespace == null) {
                XMLSchema ext = rootschema.getDescendantOrSelfSchema(rootschema.getNamespaceURI(TAG_IFRS_RU));
                urlNamespace = ext.getNamespaceURI(prefix);
                schema = ext.getDescendantOrSelfSchema(urlNamespace);
            } else {
                schema = rootschema.getDescendantOrSelfSchema(urlNamespace);
            }
            addNamespace(urlNamespace, prefix);

            ElementDecl element = schema.getElementDeclByName(name);

            return element;
        } catch (Exception ex) {
            throw new ElementNotFoundException(ex);
        }
    }

    /**
     * load taxonomy
     *
     * @param pathTaxonomy - taxonomy file
     * @throws InstanceXbrlException - exception generator
     */
    public void loadTaxonomy(String pathTaxonomy) throws InstanceXbrlException {
        try {
            // creating a Taxonomy object
            taxonomy = xbrlProcessor.loadTaxonomySet(new StreamSource(new File(pathTaxonomy)));
            // creating a DimensionalTaxonomy object
            dimensionTaxonomy = dimensionProcessor.createDimensionalTaxonomy(taxonomy);
            // validation process
            dimensionProcessor.validateTaxonomySet(taxonomy);


            rootschema = (XMLSchema) taxonomy.getPrimarySchemas().get(0);

            selfschemas = rootschema.getDescendantAndSelfSchemas();


        } catch (Exception ex) {
            throw new InstanceXbrlException(ex);
        }
    }

    private void validateInParam(InstanceXbrl xbrl) throws InstanceXbrlException {
        if (xbrl.getPathTaxonomy() == null) throw new InstanceXbrlException("param: file taxonomy not set");
    }

    /**
     * init in params, create generator, generate report
     *
     * @param xbrl - xbrl object
     * @return - report
     * @throws GeneratorException - exception generator
     */
    private Object getXbrlByParam(InstanceXbrl xbrl) throws InstanceXbrlException {
        try {

            validateInParam(xbrl);
            //load taxonomy
            if (taxonomy == null) {
                loadTaxonomy(xbrl.getPathTaxonomy());
            }

            // creating a Instance object
            instance = xbrlProcessor.newInstance(taxonomy);
            instance.setSystemId(SYSTEM);

            // creating a DimensionalInstance object
            dimensionalInstance = dimensionProcessor.createDimensionalInstance(dimensionTaxonomy, instance);

            //Register a prefix.
            addNamespace(CbrNameSpace.iso4217.getUrl(), CbrNameSpace.iso4217.getNameSpace());

            List<com.bivgroup.xbrl.instance.elements.Context> comtexts = xbrl.getContexts();
            List<Measurement> units = xbrl.getUnits();
            List<Element> elements = xbrl.getElements();

            // Add contexts
            for (com.bivgroup.xbrl.instance.elements.Context cont : comtexts) {
                addContext(cont);
            }

            // Add units
            for (Measurement un : units) {
                addUnit(un);
            }
            //Add items.
            for (Element el : elements) {
                addElement(instance.getContextById(el.getContext().getIdContext()), instance.getUnitById(el.getUnit().getIdUnit()), el.getValue().toString(), el.getAlias(), el.getName());
            }

            // validation instance process and taxonomy
            dimensionProcessor.validateInstance(dimensionTaxonomy, instance);
            //save
            return saveToStream(instance);

        } catch (Exception ex) {
            throw new InstanceXbrlException(ex);
        }

    }

    void milestone() {
        //Add items.
        XMLSchema schema1 = (XMLSchema) taxonomy.getPrimarySchemas().get(0);
        ElementDecl element11 = schema1.getElementDeclByName("cash");
        NumericItem nItem = instance.createNumericItem(element11);
        instance.addChild(nItem);

        //    NumericItem nItem1 = instance.createNumericItem(element11, context, unit, "100");
        nItem.setPrecision("18");
        instance.addChild(nItem);

        nItem.setSpecifiedValue("100");
        //     nItem.setContext(context);
        //     nItem.setUnit(unit);
        nItem.setPrecision("18");


        String sysId = instance.getSystemId();
        taxonomy.getElementDecl("", "");
        XBRLDocument d;

        ElementDecl element1111 = ((XMLSchema) ((XMLSchema) taxonomy.getPrimarySchemas().get(0)).getDescendantAndSelfSchemas().get(3)).getElementDeclByName("DenezhnyeSredstva");
        ElementDecl element111 = ((XMLSchema) ((XMLSchema) taxonomy.getPrimarySchemas().get(0)).getDescendantAndSelfSchemas().get(3)).getElementDeclById("ifrs-ru_DenezhnyeSredstva");
        ((XMLSchema) taxonomy.getPrimarySchemas().get(0)).getDescendantOrSelfSchema(((XMLSchema) taxonomy.getPrimarySchemas().get(0)).getNamespaceURI("ifrs-ru")).getElementDeclByName("DenezhnyeSredstva");


        XMLSchema schema = (XMLSchema) taxonomy.getPrimarySchemas().get(0);
        XMLSchema baseSchema = (XMLSchema) schema.getBaseSchemas().get(0);
        String namespace = baseSchema.getTargetNamespace();
        XMLSchema includedSchema = (XMLSchema) schema.getIncludedSchemas().get(0);
        String namespace1 = includedSchema.getTargetNamespaceOfIncludingSchema();
        taxonomy.getAllSchemas();
        int count = taxonomy.getPrimarySchemas().size();
        schema.getSystemId();

        schema.getTargetNamespace();
        ElementDecl element = schema.getElementDeclByName("cash");
        ElementDecl element1 = schema.getElementDeclById("");
        if (element.isItemDecl()) {
            String balance = element.getBalance();
        }
        Linkbase linkbase =
                (Linkbase) schema.getLinkbases(Link.LINK_TYPE_PRESENTATION).get(0);
        taxonomy.getAllLinkbases(Link.LINK_TYPE_PRESENTATION);
        PresentationLink rootLink =
                (PresentationLink) taxonomy.getRootPresentationLinks().get(0);

        RelationLinkMap map =
                taxonomy.getRelationLinkMap(Link.LINK_TYPE_PRESENTATION);
        Enumeration roles = map.getRoles();
        while (roles.hasMoreElements()) {
            String role = (String) roles.nextElement();
            RelationLink rootLink1 = (RelationLink) map.getRootRelationLinks(role).get(0);
            RelationLinkModel childModel =
                    (RelationLinkModel) map.getChildModels(role, rootLink).get(0);
        }

        PresentationLink rootLink2 =
                (PresentationLink) taxonomy.
                        getRootPresentationLinks("http://www.fujitsu.com/xbrl/document1", null).get(0);

//            Arc arc = linkInfo.getArc();
//            if(arc.getArcType() == Link.LINK_TYPE_PRESENTATION){
//                PresentationArc presArc = (PresentationArc)arc;
//                double order = presArc.getOrder();
//            }

//            ElementDecl element2 = (ElementDecl) rootLink.getAnchor();
    }
}
