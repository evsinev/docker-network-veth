package io.pne.veth.server.handlers.location.networkdriver;

import io.pne.veth.server.handlers.IJsonHandler;
import io.pne.veth.server.handlers.dao.IEndpointDao;
import io.pne.veth.server.handlers.dao.TEndpoint;
import io.pne.veth.server.handlers.model.*;

public class CreateEndPointHandler implements IJsonHandler<CreateEndpointRequest, CreateEndpointResponse> {

    final IEndpointDao endpointDao;

    public CreateEndPointHandler(IEndpointDao endpointDao) {
        this.endpointDao = endpointDao;
    }

    @Override
    public CreateEndpointResponse handle(CreateEndpointRequest aRequest) {
        /*
            "Interface": {
                "Address"    : "",
                "AddressIPv6": "",
                "MacAddress" : ""
            }
         */

        final EndpointInterface iface = aRequest.endpointInterface;
        if(iface == null) {
            throw new IllegalStateException("No section 'Interface': "+aRequest);
        }
        endpointDao.addEndpoint(new TEndpoint(
                  aRequest.endpointId
                , iface.address
                , iface.macAddress
                , "dns-1.c"
        ));

        CreateEndpointResponse response = new CreateEndpointResponse();
        response.endpointInterface = new EndpointInterface();
        response.endpointInterface.address     = "";
        response.endpointInterface.addressIPv6 = "";
        response.endpointInterface.macAddress  = "";
        return response;
    }

    @Override
    public Class<CreateEndpointRequest> getRequestClass() {
        return CreateEndpointRequest.class;
    }
}
