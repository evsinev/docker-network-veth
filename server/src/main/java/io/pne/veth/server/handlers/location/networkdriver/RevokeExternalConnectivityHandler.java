package io.pne.veth.server.handlers.location.networkdriver;

import io.pne.veth.server.handlers.IJsonHandler;
import io.pne.veth.server.handlers.model.RevokeExternalConnectivityRequest;
import io.pne.veth.server.handlers.model.SuccessResponse;

public class RevokeExternalConnectivityHandler implements IJsonHandler<RevokeExternalConnectivityRequest, SuccessResponse> {
    @Override
    public SuccessResponse handle(RevokeExternalConnectivityRequest aRequest) {
        return SuccessResponse.SUCCESS;
    }

    @Override
    public Class<RevokeExternalConnectivityRequest> getRequestClass() {
        return RevokeExternalConnectivityRequest.class;
    }
}
