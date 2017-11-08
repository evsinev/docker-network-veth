package io.pne.veth.server.handlers.location.networkdriver;

import io.pne.veth.server.handlers.IJsonHandler;
import io.pne.veth.server.handlers.dao.INetworkDao;
import io.pne.veth.server.handlers.dao.TNetwork;
import io.pne.veth.server.handlers.model.CreateNetworkRequest;
import io.pne.veth.server.handlers.model.SuccessResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CreateNetworkHandler implements IJsonHandler<CreateNetworkRequest, SuccessResponse> {

    private static final Logger LOG = LoggerFactory.getLogger(CreateNetworkHandler.class);

    private final INetworkDao networkDao;

    public CreateNetworkHandler(INetworkDao networkDao) {
        this.networkDao = networkDao;
    }

    @Override
    public SuccessResponse handle(CreateNetworkRequest aRequest) {
        networkDao.addNetwork(new TNetwork(
                  aRequest.networkID
                , aRequest.ipv4Data[0].Gateway
                , aRequest.ipv4Data[0].Pool
                , getInterfaceSuffix(aRequest.options)
        ));

        return SuccessResponse.SUCCESS;
    }

    private String getInterfaceSuffix(Map<String, Object> aOptions) {
        if(aOptions == null) {
            return createRandomName("No options");
        }

        Map<String, Object> generic = (Map<String, Object>) aOptions.get("com.docker.network.generic");
        if(generic == null) {
            return createRandomName("No com.docker.network.generic, creating");
        }

        Object prefix = generic.get("ip.pne.veth.interface.prefix");
        if(prefix == null) {
            return createRandomName("No ip.pne.veth.interface.prefix");
        }
        return prefix.toString();
    }

    private String createRandomName(String aMessage) {
        String prefix = "veth-" + System.currentTimeMillis();
        LOG.warn("{}, creating random interface prefix: {}", aMessage, prefix);
        return prefix;
    }

    @Override
    public Class<CreateNetworkRequest> getRequestClass() {
        return CreateNetworkRequest.class;
    }
}
