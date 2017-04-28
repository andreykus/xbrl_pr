package com.bivgroup.xbrl.api.restcontollers;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * register message - on not found request context
 * Created by bush on 17.01.2017.
 */
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    /**
     * responce
     * @param e - exception
     * @return - responce
     */
    public Response toResponse(NotFoundException e) {
        return Response
                .status(Response.Status.OK)
                .entity("exception page!")
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }

}
