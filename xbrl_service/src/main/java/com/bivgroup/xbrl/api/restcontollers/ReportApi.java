package com.bivgroup.xbrl.api.restcontollers;

import com.bivgroup.xbrl.api.restcontollers.exceptions.XbrlServerException;
import com.bivgroup.xbrl.common.logger.LoggerProvider;
import com.bivgroup.xbrl.export.report.GetParamsFacade;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * API /api/report XBRL service, action with report
 * Created by bush on 17.01.2017.
 */
@Stateless
@Path("/report")
public class ReportApi {

    /**
     * Facade create xbrl report
     */
    @Inject
    private GetParamsFacade facade;

    /**
     * logger
     */
    @Inject
    @LoggerProvider
    protected Logger logger;

    public ReportApi() {
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "XBRL repot - xml file", notes = "generate report", responseContainer = "file")
    public List getAll(@ApiParam(value = "Period of report", required = false, defaultValue = "")
                       @QueryParam("period") String periodID) throws XbrlServerException {

        final Properties p = new Properties();
//        final Context context = EJBContainer.createEJBContainer(p).getContext();
//        XbrlDao movies = (XbrlDao) context.lookup("java:global/injection-of-entitymanager/XbrlDao");

        List out = new ArrayList();
        out.add(new String("exampl"));
        if (1 == 1) throw new NotFoundException();
        return new ArrayList();
    }


    @GET
    @Path("/getFile")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response doTest(@Context HttpServletResponse response) throws XbrlServerException {
        try {
            ServletOutputStream outStream = response.getOutputStream();
            facade.createReport(outStream);
            outStream.flush();
        } catch (Exception e) {
            logger.error(e, e.getCause());
            return Response.serverError().entity(e.getMessage()).build();
        }
        return Response.ok().build();


    }

    /**
     * Get XBRL report
     *
     * @param response   - xml file
     * @param idPeriod   - id period
     * @param idTaxonomy - id taxonomy
     * @return -    status
     * @throws Exception
     */
    @GET
    @Path("/getReport")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getReport(@Context HttpServletResponse response, @FormParam("idPeriod") Long idPeriod, @FormParam("idTaxonomy") Long idTaxonomy) throws Exception {
        try {
            ServletOutputStream outStream = response.getOutputStream();
            facade.createReport(outStream, idPeriod, idTaxonomy);
            outStream.flush();
        } catch (Exception e) {
            logger.error(e, e.getCause());
            return Response.serverError().entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    /**
     * create Period report
     *
     * @param response   - xml file
     * @param idPeriod   - id period
     * @param name - name
     * @return -    period
     * @throws Exception
     */
    @GET
    @Path("/createPeriod")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getReport(@Context HttpServletResponse response, @FormParam("dateFrom") Date dateFrom , @FormParam("dateTo") Date dateTo,  @FormParam("idPeriod") Date idPeriod, @FormParam("name") String name) throws Exception {
        Long periodId = null;
        try {
            periodId = facade.createPeriod(dateFrom, dateTo, name);

        } catch (Exception e) {
            logger.error(e, e.getCause());
            return Response.serverError().entity(e.getMessage()).build();
        }
        return  Response.ok(periodId,MediaType.TEXT_PLAIN).build();
    }

}
