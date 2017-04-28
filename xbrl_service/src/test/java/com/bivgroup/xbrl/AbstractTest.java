package com.bivgroup.xbrl;




import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.oracle.OracleDataTypeFactory;
import org.junit.Before;


import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.sql.SQLException;


/**
 * Created by andreykus on 26.11.2016.
 */
public abstract class AbstractTest {

    public DataSource dataSource;
    public IDatabaseConnection con;


    protected DataSource getDataSource(String className, String url, String user_name, String password) throws Exception {
        DataSource dataSource;

            Class clazz = Class.forName(className);
            dataSource = (DataSource) clazz.newInstance();
            Method setURL = clazz.getDeclaredMethod("setURL", String.class);
            Method setUser = clazz.getDeclaredMethod("setUser", String.class);
            Method setPassword = clazz.getDeclaredMethod("setPassword", String.class);

            setURL.invoke(dataSource, url);
            if (user_name != null) setUser.invoke(dataSource, user_name);
            if (password != null) setPassword.invoke(dataSource, password);



        return dataSource;
    }

    public abstract void init() throws Exception;

    /**
     * Инициируем соединение и заполняем данными
     */
    @Before
    public void setUp() throws Exception{
       String className = "org.h2.jdbcx.JdbcDataSource";
        String  url = "jdbc:h2:file:C:/PILOT/demo;MV_STORE=FALSE;MVCC=FALSE";
        String shema_name = null  ;
        String  user_name = "sa";
        String password = null;
        dataSource = getDataSource(className, url, user_name, password);
        con = new DatabaseDataSourceConnection(dataSource, shema_name);
        if (className.equals("oracle.jdbc.pool.OracleDataSource")) con.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new OracleDataTypeFactory());
        init();
    }
}