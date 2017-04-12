package io.pne.veth.server.handlers;

import io.pne.veth.server.handlers.model.ProgramExternalConnectivityRequest;
import io.pne.veth.server.handlers.model.SuccessResponse;

public class NetworkDriver_ProgramExternalConnectivity implements IJsonHandler<ProgramExternalConnectivityRequest, SuccessResponse> {
    @Override
    public SuccessResponse handle(ProgramExternalConnectivityRequest aRequest) {
        return SuccessResponse.SUCCESS;
    }
}
