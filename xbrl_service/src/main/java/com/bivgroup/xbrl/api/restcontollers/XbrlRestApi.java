package com.bivgroup.xbrl.api.restcontollers;

import com.bivgroup.xbrl.export.report.GetParamsFacade;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * Registrator rest service with context /api
 * Created by bush on 17.01.2017.
 */
@ApplicationPath("/api")
@Api(value = "xbrlReport", description = "RESTful API to create XBRL report.")
public class XbrlRestApi extends Application {
    /**
     * logger
     */
    protected Logger logger = LogManager.getLogger(this.getClass());
    public XbrlRestApi() {
    }

    /**
     * register class rest service
     * @return
     */
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(Arrays.asList(ReportApi.class, GetParamsFacade.class));
    }
}
