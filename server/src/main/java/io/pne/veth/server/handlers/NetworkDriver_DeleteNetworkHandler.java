package io.pne.veth.server.handlers;

import io.pne.veth.server.handlers.dao.INetworkDao;
import io.pne.veth.server.handlers.model.CreateNetworkRequest;
import io.pne.veth.server.handlers.model.DeleteNetworkRequest;
import io.pne.veth.server.handlers.model.SuccessResponse;

public class NetworkDriver_DeleteNetworkHandler implements IJsonHandler<DeleteNetworkRequest, SuccessResponse> {

    private final INetworkDao networkDao;

    public NetworkDriver_DeleteNetworkHandler(INetworkDao networkDao) {
        this.networkDao = networkDao;
    }

    @Override
    public SuccessResponse handle(DeleteNetworkRequest aRequest) {

        networkDao.removeNetwork(aRequest.networkID);

        return SuccessResponse.SUCCESS;
    }

    @Override
    public Class<DeleteNetworkRequest> getRequestClass() {
        return DeleteNetworkRequest.class;
    }
}
