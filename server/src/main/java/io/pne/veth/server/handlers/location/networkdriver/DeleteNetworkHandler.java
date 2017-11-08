package io.pne.veth.server.handlers.location.networkdriver;

import io.pne.veth.server.handlers.IJsonHandler;
import io.pne.veth.server.handlers.dao.INetworkDao;
import io.pne.veth.server.handlers.model.DeleteNetworkRequest;
import io.pne.veth.server.handlers.model.SuccessResponse;

public class DeleteNetworkHandler implements IJsonHandler<DeleteNetworkRequest, SuccessResponse> {

    private final INetworkDao networkDao;

    public DeleteNetworkHandler(INetworkDao networkDao) {
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
