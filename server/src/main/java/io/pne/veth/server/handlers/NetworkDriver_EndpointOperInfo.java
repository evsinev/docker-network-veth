package io.pne.veth.server.handlers;

import io.pne.veth.server.handlers.model.InfoRequest;
import io.pne.veth.server.handlers.model.InfoResponse;

import java.util.HashMap;

public class NetworkDriver_EndpointOperInfo implements IJsonHandler<InfoRequest, InfoResponse> {
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

        InfoResponse response = new InfoResponse();

        response.value = new HashMap<>();
        response.value.put("hostNic.Addr", "192.168.3.2/24");
        response.value.put("hostNic.HardwareAddr", "62:7b:0b:7d:54:8d");
        response.value.put("hostNic.Name", "dns-1.c");
        response.value.put("id", "dc90c4902a7a43cf897f91c094252f038c2e24c507083c81bb7a2ec2b7f79543");
        response.value.put("srcName", "dns-1.c");

        return response;
    }
}
