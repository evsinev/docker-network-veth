package io.pne.veth.server.handlers;

import io.pne.veth.server.handlers.dao.IEndpointDao;
import io.pne.veth.server.handlers.dao.TEndpoint;
import io.pne.veth.server.handlers.model.InfoRequest;
import io.pne.veth.server.handlers.model.InfoResponse;

import java.util.HashMap;

public class NetworkDriver_EndpointOperInfo implements IJsonHandler<InfoRequest, InfoResponse> {

    private final IEndpointDao endpointDao;

    public NetworkDriver_EndpointOperInfo(IEndpointDao endpointDao) {
        this.endpointDao = endpointDao;
    }

    @Override
    public InfoResponse handle(InfoRequest aRequest) {
        /*
            "Value": {
                "hostNic.Addr": "192.168.3.2/24",
                "hostNic.HardwareAddr": "62:7b:0b:7d:54:8d",
                "hostNic.Name": "dns-1.c",
                "id": "dc90c4902a7a43cf897f91c094252f038c2e24c507083c81bb7a2ec2b7f79543",
                "srcName": "dns-1.c"
            }
         */

        TEndpoint endpoint = endpointDao.findEndPoint(aRequest.endpointId);

        InfoResponse response = new InfoResponse();

        response.value = new HashMap<>();
        response.value.put("hostNic.Addr", endpoint.ipAddress);
        response.value.put("hostNic.HardwareAddr", endpoint.macAddress);
        response.value.put("hostNic.Name", endpoint.device);
        response.value.put("id", endpoint.id);
        response.value.put("srcName", endpoint.device);

        return response;
    }

    @Override
    public Class<InfoRequest> getRequestClass() {
        return InfoRequest.class;
    }
}
