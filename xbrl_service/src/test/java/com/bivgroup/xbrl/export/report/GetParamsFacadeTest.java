package com.bivgroup.xbrl.export.report;


import com.bivgroup.xbrl.AbstractTest;
import com.bivgroup.xbrl.app.Main;
import com.bivgroup.xbrl.dao.XbrlDao;
import com.bivgroup.xbrl.entity.XbrlParam;
import com.bivgroup.xbrl.entity.XbrlParamValue;
import com.bivgroup.xbrl.instance.impl.freemaker.CreatorXbrlFreeMarker;
import com.bivgroup.xbrl.instance.impl.xwand.CreatorXbrlXWand;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;

import org.wildfly.swarm.arquillian.DefaultDeployment;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.spi.api.JARArchive;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by bush on 21.03.2017.
 */

//@DefaultDeployment(testable = false, type = DefaultDeployment.Type.WAR)

public class GetParamsFacadeTest extends AbstractTest {

    //    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//                .addClass(GetParamsFacade.class)
//                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//    }
    private EntityManagerFactory entityManagerFactory;

    @Inject
    public GetParamsFacade facade;
    @Inject
    public XbrlDao dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(GetParamsFacade.class, XbrlDao.class, CreatorXbrlXWand.class, CreatorXbrlFreeMarker.class, EntityManager.class).inject(this).build();

    @Test
    public void testCreateReport() throws Exception {
        facade.createReport(new ByteArrayOutputStream(), 1001L, 1001L);
    }

    @Test
    public void testCreateContexts() throws Exception {
        facade.createContexts(1001L, 1001L);
    }

    @Override
    public void init() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("Xbrl");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        dao.setEntityManager(entityManager);

        entityManager.find(XbrlParam.class, 1L);
        facade.getDao().setEntityManager(entityManager);
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File("src/test/resources/com/bivgroup/xbrl/dao/TestDelete.xml"));
        DatabaseOperation.DELETE_ALL.execute(con, dataSet);

        dataSet = new FlatXmlDataSetBuilder().build(new File("src/test/resources/com/bivgroup/xbrl/dao/TestData.xml"));
        DatabaseOperation.INSERT.execute(con, dataSet);
    }

    public static void main(String[] args)
    {
        JUnitCore core= new JUnitCore();
        core.run(GetParamsFacadeTest.class);
    }
}