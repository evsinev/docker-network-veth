package io.pne.veth.server.handlers;

import io.pne.veth.server.handlers.model.*;

public class NetworkDriver_JoinHandler implements IJsonHandler<JoinRequest, JoinResponse> {
    @Override
    public JoinResponse handle(JoinRequest aRequest) {
        /*
            "InterfaceName": {
                "SrcName": "dns-1.c",
                "DstPrefix": "eth"
            },
            "Gateway": "192.168.3.1",
            "GatewayIPv6": "",
            "StaticRoutes": null,
            "DisableGatewayService": false
         */
        JoinResponse response = new JoinResponse();

        response.interfaceName = new InterfaceName();
        response.interfaceName.srcName   = "dns-1.c";
        response.interfaceName.dstPrefix = "eth";

        response.gateway               = "192.168.3.1";
        response.disableGatewayService = false;

        return response;
    }
}
