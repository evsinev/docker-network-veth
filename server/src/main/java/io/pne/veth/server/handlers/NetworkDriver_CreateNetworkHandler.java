package io.pne.veth.server.handlers;

import io.pne.veth.server.handlers.model.CapabilitiesResponse;
import io.pne.veth.server.handlers.model.CapabilityScope;
import io.pne.veth.server.handlers.model.CreateNetworkRequest;
import io.pne.veth.server.handlers.model.SuccessResponse;

public class NetworkDriver_CreateNetworkHandler implements IJsonHandler<CreateNetworkRequest, SuccessResponse> {
    @Override
    public SuccessResponse handle(CreateNetworkRequest aRequest) {
        return SuccessResponse.SUCCESS;
    }
}
