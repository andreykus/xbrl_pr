package com.bivgroup.xbrl.app;

import com.bivgroup.xbrl.api.restcontollers.CORSFilter;
import com.bivgroup.xbrl.api.restcontollers.ReportApi;
import com.bivgroup.xbrl.api.restcontollers.XbrlRestApi;
//import com.bivgroup.xbrl.servlet.FileServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.logging.LoggingFraction;
import org.wildfly.swarm.management.ManagementFraction;
import org.wildfly.swarm.swagger.SwaggerArchive;
import org.wildfly.swarm.undertow.WARArchive;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * Main class start swarm
 * Created by bush on 17.01.2017.
 */
public class Main {
    /**
     * logger
     */
    protected Logger logger = LogManager.getLogger(this.getClass());

    /**
     * get container swarm
     *
     * @return -  container
     * @throws Exception -
     */
    public static Swarm getSwarm() throws Exception {
        URL stageConfig = Main.class.getClassLoader().getResource("project-stages.yml");
        String logFile = System.getProperty("user.dir") + File.separator + "swarm.log";

        Swarm swarm = new Swarm();

        swarm.fraction(
                ManagementFraction.createDefaultFraction()
                        .httpInterfaceManagementInterface((iface) -> {
                            iface.allowedOrigin("http://localhost:8080");
                            iface.securityRealm("ManagementRealm");
                        })
                        .securityRealm("ManagementRealm", (realm) -> {
                            realm.inMemoryAuthentication((authn) -> {
                                authn.add("bob", "tacos!", true);
                            });
                            realm.inMemoryAuthorization((authz) -> {
                                authz.add("bob", "admin");
                            });
                        })
        );

        swarm.fraction(
                new LoggingFraction()
                        .fileHandler("FILE", f -> {
                            Map<String, String> fileProps = new HashMap<>();
                            fileProps.put("path", logFile);
                            f.file(fileProps);
                            f.level(org.wildfly.swarm.config.logging.Level.DEBUG);
                            f.formatter("%d{HH:mm:ss,SSS} %-5p [%c] (%t) %s%e%n");

                        })
                        .rootLogger(org.wildfly.swarm.config.logging.Level.DEBUG, "FILE")
        );

        //init jax-rs
        JAXRSArchive deploymentrs = getArchive();

//        WARArchive deploymentw = ShrinkWrap.create(WARArchive.class);
//        deploymentw.addClass(FileServlet.class);
//        deploymentw.addAllDependencies();

        //get type bd from
        String useDB = System.getProperty("swarm.use.db", "h2");

        // Configure the Datasources subsystem with a driver
        // and a datasource
        switch (useDB.toLowerCase()) {
            case "h2":
                swarm.fraction(datasourceWithH2());
                break;
            case "postgresql":
                swarm.fraction(datasourceWithPostgresql());
                break;
            case "mysql":
                swarm.fraction(datasourceWithMysql());
                break;
            case "oracle":
                swarm.fraction(datasourceWithMysql());
                break;
            default:
                swarm.fraction(datasourceWithH2());
        }

//        swarm.fraction(new ManagementConsoleFraction()
//                .contextRoot("/general-console")
//        );

        swarm
                .fraction(LoggingFraction.createDefaultLoggingFraction())
                .start();
        swarm.deploy(deploymentrs);//.deploy(deploymentw);

        return swarm;
    }

    /**
     * init jax-rs
     *
     * @return - jar archive
     * @throws Exception
     */
    public static JAXRSArchive getArchive() throws Exception {
        // Create a SwaggerArchive using ShrinkWrap API
        SwaggerArchive archive = ShrinkWrap.create(SwaggerArchive.class);
        // Now we can use the SwaggerArchive to fully customize the JSON output
        archive.setVersion("1.0"); // our API version
        archive.setContact("Andrey <andreykus@mail.ru>");  // set contact info
        archive.setLicense("MIT"); // set license
        // Finally tell swagger where our resources are
        archive.setResourcePackages("com.bivgroup.xbrl.api.restcontollers");
        archive.setTitle("Swagger XBRL");

        //init class for rest service
        JAXRSArchive deploymentrs = archive.as(JAXRSArchive.class);
        deploymentrs.addClass(XbrlRestApi.class);
        deploymentrs.addClass(ReportApi.class);
        deploymentrs.addResource(CORSFilter.class);
//        deploymentrs.as(TopologyArchive.class)
//                .advertise("recommendations");
        //init config JPA
        deploymentrs.addAsWebInfResource(new ClassLoaderAsset("META-INF/persistence.xml", Main.class.getClassLoader()), "classes/META-INF/persistence.xml");
        deploymentrs.addAllDependencies();
        return deploymentrs;
    }


    /**
     * main method
     *
     * @param args - in params
     * @throws Exception
     */
    public static void main(String... args) throws Exception {
        getSwarm();
    }

    /***
     * get Data Source type H2
     *
     * @return - fraction
     */
    private static DatasourcesFraction datasourceWithH2() {
        return new DatasourcesFraction()
                .jdbcDriver("h2", (d) -> {
                    d.driverClassName("org.h2.Driver");
                    d.xaDatasourceClass("org.h2.jdbcx.JdbcDataSource");
                    d.driverModuleName("com.h2database.h2");
                })
                .dataSource("XBRL_DS", (ds) -> {
                    ds.driverName("h2");
                    ds.connectionUrl("jdbc:h2:file:C:/PILOT/demo;MV_STORE=FALSE;MVCC=FALSE");
                    ds.userName("sa");
                    ds.password("sa");
                });
    }

    /***
     * get Data Source type POSTGRE
     *
     * @return - fraction
     */
    private static DatasourcesFraction datasourceWithPostgresql() {
        return new DatasourcesFraction()
                .jdbcDriver("org.postgresql", (d) -> {
                    d.driverClassName("org.postgresql.Driver");
                    d.xaDatasourceClass("org.postgresql.xa.PGXADataSource");
                    d.driverModuleName("org.postgresql");
                })
                .dataSource("XBRL_DS", (ds) -> {
                    ds.driverName("org.postgresql");
                    ds.connectionUrl("jdbc:postgresql://localhost:5432/postgres");
                    ds.userName("postgres");
                    ds.password("postgres");
                });
    }

    /***
     * get Data Source type MSSQL
     *
     * @return - fraction
     */
    private static DatasourcesFraction datasourceWithMysql() {
        return new DatasourcesFraction()
                .jdbcDriver("com.mysql", (d) -> {
                    d.driverClassName("com.mysql.jdbc.Driver");
                    d.xaDatasourceClass("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
                    d.driverModuleName("com.mysql");
                })
                .dataSource("XBRL_DS", (ds) -> {
                    ds.driverName("com.mysql");
                    ds.connectionUrl("jdbc:mysql://localhost:3306/mysql");
                    ds.userName("root");
                    ds.password("root");
                });
    }

    /***
     * get Data Source type ORACLE
     *
     * @return - fraction
     */
    private static DatasourcesFraction datasourceWithOracle() {
        return new DatasourcesFraction()
                .jdbcDriver("com.oracle", (d) -> {
                    d.driverClassName("oracle.jdbc.OracleDriver");
                    d.xaDatasourceClass("oracle.jdbc.pool.OracleDataSource");
                    d.driverModuleName("com.oracle");
                })
                .dataSource("XBRL_DS", (ds) -> {
                    ds.driverName("com.oracle");
                    ds.connectionUrl("jdbc:oracle://localhost:3306/mysql");
                    ds.userName("root");
                    ds.password("root");
                });
    }

//    Context ctx = new InitialContext();
//    DataSource ds = (DataSource) ctx.lookup("jboss/datasources/XBRL_DS");
}
