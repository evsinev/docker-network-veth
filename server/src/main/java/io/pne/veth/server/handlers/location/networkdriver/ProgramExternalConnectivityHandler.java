package io.pne.veth.server.handlers.location.networkdriver;

import io.pne.veth.server.handlers.IJsonHandler;
import io.pne.veth.server.handlers.model.ProgramExternalConnectivityRequest;
import io.pne.veth.server.handlers.model.SuccessResponse;

public class ProgramExternalConnectivityHandler implements IJsonHandler<ProgramExternalConnectivityRequest, SuccessResponse> {
    @Override
    public SuccessResponse handle(ProgramExternalConnectivityRequest aRequest) {
        return SuccessResponse.SUCCESS;
    }

    @Override
    public Class<ProgramExternalConnectivityRequest> getRequestClass() {
        return ProgramExternalConnectivityRequest.class;
    }
}
