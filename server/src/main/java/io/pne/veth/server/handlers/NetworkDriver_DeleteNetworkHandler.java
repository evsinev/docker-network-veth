package io.pne.veth.server.handlers;

import io.pne.veth.server.handlers.model.CreateNetworkRequest;
import io.pne.veth.server.handlers.model.DeleteNetworkRequest;
import io.pne.veth.server.handlers.model.SuccessResponse;

public class NetworkDriver_DeleteNetworkHandler implements IJsonHandler<DeleteNetworkRequest, SuccessResponse> {
    @Override
    public SuccessResponse handle(DeleteNetworkRequest aRequest) {
        return SuccessResponse.SUCCESS;
    }
}
