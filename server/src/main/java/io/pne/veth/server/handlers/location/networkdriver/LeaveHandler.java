package io.pne.veth.server.handlers.location.networkdriver;

import io.pne.veth.server.handlers.IJsonHandler;
import io.pne.veth.server.handlers.dao.IEndpointDao;
import io.pne.veth.server.handlers.model.DeleteEndpointRequest;
import io.pne.veth.server.handlers.model.LeaveRequest;
import io.pne.veth.server.handlers.model.SuccessResponse;

public class LeaveHandler implements IJsonHandler<LeaveRequest, SuccessResponse> {

    private final IEndpointDao endpointDao;

    public LeaveHandler(IEndpointDao endpointDao) {
        this.endpointDao = endpointDao;
    }

    @Override
    public SuccessResponse handle(LeaveRequest aRequest) {
        endpointDao.removeEndpoint(aRequest.endpointId);
        return SuccessResponse.SUCCESS;
    }

    @Override
    public Class<LeaveRequest> getRequestClass() {
        return LeaveRequest.class;
    }
}
