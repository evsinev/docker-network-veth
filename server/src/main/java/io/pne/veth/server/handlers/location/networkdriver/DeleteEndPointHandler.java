package io.pne.veth.server.handlers.location.networkdriver;

import io.pne.veth.server.handlers.IJsonHandler;
import io.pne.veth.server.handlers.dao.IEndpointDao;
import io.pne.veth.server.handlers.model.DeleteEndpointRequest;
import io.pne.veth.server.handlers.model.SuccessResponse;

public class DeleteEndPointHandler implements IJsonHandler<DeleteEndpointRequest, SuccessResponse> {

    private final IEndpointDao endpointDao;

    public DeleteEndPointHandler(IEndpointDao endpointDao) {
        this.endpointDao = endpointDao;
    }

    @Override
    public SuccessResponse handle(DeleteEndpointRequest aRequest) {
        endpointDao.removeEndpoint(aRequest.endpointId);
        return SuccessResponse.SUCCESS;
    }

    @Override
    public Class<DeleteEndpointRequest> getRequestClass() {
        return DeleteEndpointRequest.class;
    }
}
