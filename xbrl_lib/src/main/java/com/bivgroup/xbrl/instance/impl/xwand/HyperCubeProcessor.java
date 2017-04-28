package com.bivgroup.xbrl.instance.impl.xwand;

import com.fujitsu.xml.xbrl.dimension.instance.DimensionalContext;
import com.fujitsu.xml.xbrl.dimension.instance.DimensionalInstance;
import com.fujitsu.xml.xbrl.dimension.taxonomy.*;
import com.fujitsu.xml.xbrl.xwand.instance.*;
import com.fujitsu.xml.xbrl.xwand.taxonomy.ElementDecl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.*;

/**
 * Process HyperCube element
 * build explain dimension, for add context with axis in scenario
 * Created by bush on 25.04.2017.
 */
public enum HyperCubeProcessor {

    DomainMember {
        void process(Object obj, Object action) {
            DomainMemberList children = null;
            boolean usage = false;
            ElementDecl element = null;
            if (obj instanceof DimensionDomainRelationship) {
                children = ((DimensionDomainRelationship) obj).getDomainMember().getChildren();
                usage = ((DimensionDomainRelationship) obj).isUsable();
                element = ((DimensionDomainRelationship) obj).getDomainMember().getElementDecl();
            } else {
                children = ((DomainMember) obj).getChildren();
                usage = ((DomainMember) obj).isUsable();
                element = ((DomainMember) obj).getElementDecl();
            }
            for (int i = 0, n = children.size(); i < n; i++) {
                DomainMember childDomainMember = children.get(i);
                logger.debug("domain:" + childDomainMember.getElementDecl().getName());
                DomainMember.process(childDomainMember, action);
            }
            if (usage && children.size() > 0) {
                logger.debug("total:" + element.getName());
                //getItemsForCalculations
            }
        }
    },

    ExplicitMember {
        void process(Object obj, Object action) {
            DimensionItem dimItem = (DimensionItem) obj;
            ExplicitDimensionItem exDimItem = (ExplicitDimensionItem) dimItem;
            logger.debug("axis:" + exDimItem.getElementDecl().getName());
            DimensionDomainRelationshipList ddRels = exDimItem.getDimensionDomainRelationships();
            for (int k = 0, nnn = ddRels.size(); k < nnn; k++) {
                DimensionDomainRelationship ddRel = ddRels.get(k);
                DomainMember.process(ddRel, action);
            }
        }
    },

    Dimension {
        void process(Object obj, Object action) {
            HasHypercubeRelationship hhRel = (HasHypercubeRelationship) obj;
            Hypercube hypercube = hhRel.getHypercube();
            logger.debug("table:" + hypercube.getElementDecl().getName());
            HypercubeDimensionRelationshipList hdRelList =
                    hypercube.getHypercubeDimensionRelationships();
            for (int j = 0, nn = hdRelList.size(); j < nn; j++) {
                HypercubeDimensionRelationship hdRel = hdRelList.get(j);
                DimensionItem dimItem = hdRel.getDimensionItem();
                if (dimItem.getType() != DimensionItemType.EXPLICIT) {
                    continue;
                }
                ExplicitMember.process(dimItem, action);
            }
        }
    },

    Hypercube {
        void process(Object obj, Object action) {
            PrimaryItem primaryItem = (PrimaryItem) obj;

            HasHypercubeRelationshipList hhRelList =
                    primaryItem.getInheritedHasHypercubeRelationships();
            if (hhRelList.size() > 0) {
                logger.debug("primaryItemHyper:" + primaryItem.getElementDecl().getName());
                PrimaryItemList childPrimaryItemList = primaryItem.getChildPrimaryItems();
                for (int i = 0, n = childPrimaryItemList.size(); i < n; i++) {
                    PrimaryItem childPrimaryItem = childPrimaryItemList.get(i);
                    logger.debug("childPrimaryItemHyper:" + childPrimaryItem.getElementDecl().getName());
                }
            }

            for (int i1 = 0, n1 = hhRelList.size(); i1 < n1; i1++) {
                HasHypercubeRelationship hhRel = hhRelList.get(i1);
                if (hhRel.getType() != HasHypercubeRelationshipType.ALL) {
                    continue;
                }
                Dimension.process(hhRel, action);

            }
        }
    },

    PrimaryItem {
        void process(Object obj, Object action) {
            PrimaryItem primaryItem = (PrimaryItem) obj;

            PrimaryItemList childPrimaryItemList = primaryItem.getChildPrimaryItems();
            for (int i = 0, n = childPrimaryItemList.size(); i < n; i++) {
                PrimaryItem childPrimaryItem = childPrimaryItemList.get(i);
                PrimaryItem.process(childPrimaryItem, action);
            }
            ElementDecl elementDecl = primaryItem.getElementDecl();
            if (elementDecl.isAbstract() || !elementDecl.isNumericItemDecl()) {
                return;
            }
            Hypercube.process(primaryItem, action);
        }
    };

    /**
     * logger
     */
    protected Logger logger = LogManager.getLogger(this.getClass());

    abstract void process(Object obj, Object action);

    private List getItems(ElementDecl elementDecl, ExplicitDimensionItem exDimItem,
                          DomainMember domainMember, DimensionalInstance dimInstance) {
        List itemList = new ArrayList();
        Instance instance = dimInstance.getInstance();
        InstanceElementList elements = instance.getChildren();
        for (int i = 0, n = elements.size(); i < n; i++) {
            InstanceElement element = elements.get(i);
            if (element.getElementType() != Item.ELEMENT_TYPE_NUMERIC_ITEM) {
                continue;
            }
            NumericItem numItem = (NumericItem) element;
            if (numItem.getElementDecl() != elementDecl) {
                continue;
            }
            Context ctx = numItem.getContext();
            DimensionalContext dc = dimInstance.getDimensionalContext(ctx);
// checks if the context refers refers the specified Dimension and Domain Member
            if (pointsDomainMember(dc, exDimItem, domainMember)) {
                itemList.add(numItem);
            }
        }
        return itemList;
    }

    private List getItemsForCalculation(DimensionalInstance dimInstance, ElementDecl elementDecl,
                                        ExplicitDimensionItem exDimItem,
                                        DomainMember domainMember, Context context, Unit unit) {
        DimensionalContext dimContext = dimInstance.getDimensionalContext(context);
        List itemList = new ArrayList();
        Instance instance = dimInstance.getInstance();
        InstanceElementList elements = instance.getChildren();
        for (int i = 0, n = elements.size(); i < n; i++) {
            InstanceElement element = elements.get(i);
            if (element.getElementType() != Item.ELEMENT_TYPE_NUMERIC_ITEM) {
                continue;
            }
            NumericItem numItem = (NumericItem) element;
            if (numItem.getElementDecl() != elementDecl) {
                continue;
            }
            if (!unit.s_equals(numItem.getUnit())) {
// checks if the unit is equal.
                continue;
            }
            Context ctx = numItem.getContext();
            DimensionalContext dc = dimInstance.getDimensionalContext(ctx);
            if (!dimContext.equalsExceptOneDimension(dc, exDimItem)) {
// checks if the context is equal except for the specified dimension
                continue;
            }
            if (pointsDomainMember(dc, exDimItem, domainMember)) {
                itemList.add(numItem);
            }
        }

        return itemList;
    }

    private void getItemsForCalculations(DomainMemberList children, DimensionalInstance dimInstance, PrimaryItem primaryItem, ExplicitDimensionItem exDimItem, DomainMember domainMember) {
        List targetItemList = getItems(primaryItem.getElementDecl(), exDimItem, domainMember, dimInstance);
        for (int i = 0, n = targetItemList.size(); i < n; i++) {
            NumericItem numItem = (NumericItem) targetItemList.get(i);
            BigDecimal total = null;

// calculates the total
            for (int j = 0, nn = children.size(); j < nn; j++) {
                DomainMember childDomainMember = children.get(j);
                List childItemList = getItemsForCalculation(dimInstance, primaryItem.getElementDecl(),
                        exDimItem, childDomainMember, numItem.getContext(),
                        numItem.getUnit());
                if (childItemList.size() == 1) {
// calculates only if the count of the child Item is one.
// If the count is more than one, they are duplicated.
                    NumericItem childItem = (NumericItem) childItemList.get(0);
                    BigDecimal value = childItem.getRoundedValue();
                    if (total == null) {
                        total = value;
                    } else {
                        total = total.add(value);
                    }
                }
            }
            if (total != null) {
                if (total.compareTo(numItem.getRoundedValue()) != 0) {
// adds the item into the error list
                    // errorItemList.add(numItem);
                }
            }
        }
    }

    private boolean pointsDomainMember(DimensionalContext dc, ExplicitDimensionItem exDimItem,
                                       DomainMember domainMember) {
        if (exDimItem.getDefaultDomainMemberDecl() !=
                domainMember.getElementDecl()) {
            return (dc.containsDomainMemberDecl(exDimItem.getElementDecl(),
                    domainMember.getElementDecl()));
        } else {
// When the specified member is a default member,
// checks that the context does not contain the xbrldi:explicitMember
// which shows the specified Dimension Item
            return (!dc.containsDimensionItemDecl(exDimItem.getElementDecl()));
        }
    }


}
