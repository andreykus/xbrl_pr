package com.bivgroup.xbrl.ws;

import javax.jws.WebService;

/**
 * Created by bush on 20.03.2017.
 */
@WebService(serviceName = "XbrlWebService", portName = "Xbrl", name = "Xbrl", endpointInterface = "com.bivgroup.xbrl.ws.XbrlWService",
        targetNamespace = "http://wildfly-swarm.io/xbrl")
public class XbrlWServiceImpl implements XbrlWService {


    @Override
    public String sayHello() {
        return "Hello World!";
    }
}

