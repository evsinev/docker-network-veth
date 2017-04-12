package io.pne.veth.server.handlers;

import io.pne.veth.server.handlers.model.*;

public class NetworkDriver_CreateEndPointHandler implements IJsonHandler<CreateEndpointRequest, CreateEndpointResponse> {
    @Override
    public CreateEndpointResponse handle(CreateEndpointRequest aRequest) {
        /*
            "Interface": {
                "Address"    : "",
                "AddressIPv6": "",
                "MacAddress" : ""
            }
         */
        CreateEndpointResponse response = new CreateEndpointResponse();
        response.endpointInterface = new EndpointInterface();
        response.endpointInterface.address     = "";
        response.endpointInterface.addressIPv6 = "";
        response.endpointInterface.macAddress  = "";
        return response;
    }
}
