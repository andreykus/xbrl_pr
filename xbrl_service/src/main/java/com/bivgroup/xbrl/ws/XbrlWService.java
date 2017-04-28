package com.bivgroup.xbrl.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by bush on 20.03.2017.
 */
@WebService(targetNamespace = "http://wildfly-swarm.io/xbrl")
public interface XbrlWService {

    @WebMethod
    public String sayHello();
}
