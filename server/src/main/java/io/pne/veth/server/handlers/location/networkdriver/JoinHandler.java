package io.pne.veth.server.handlers.location.networkdriver;

import io.pne.veth.server.handlers.IJsonHandler;
import io.pne.veth.server.handlers.dao.IEndpointDao;
import io.pne.veth.server.handlers.model.*;
import io.pne.veth.server.handlers.service.INetworkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoinHandler implements IJsonHandler<JoinRequest, JoinResponse> {

    private static final Logger LOG = LoggerFactory.getLogger(JoinHandler.class);

    final IEndpointDao endpointDao;
    final INetworkService  networkService;

    public JoinHandler(IEndpointDao endpointDao, INetworkService aNetworkService) {
        this.endpointDao = endpointDao;
        networkService = aNetworkService;
    }

    @Override
    public JoinResponse handle(JoinRequest aRequest) {
        /*
        Request:
            "NetworkID": "fa7cb1370dce0911aa4522ed5d9c182a9b85b974ffdc77b89417c5a7a900211e",
            "EndpointID": "f1b23cda6f6d83e210a1c9340756ac20f1a31c9d942776187a493fb6abf3fd31",
            "SandboxKey": "/var/run/docker/netns/5514800a72be",
            "Options": {
                "com.docker.network.endpoint.exposedports": [],
                "com.docker.network.portmap": []
            }

        Response:
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

        response.gateway               = networkService.getNetworkGateway(aRequest.networkID, aRequest.endpointId);
        response.disableGatewayService = false;

        return response;
    }

    @Override
    public Class<JoinRequest> getRequestClass() {
        return JoinRequest.class;
    }
}
