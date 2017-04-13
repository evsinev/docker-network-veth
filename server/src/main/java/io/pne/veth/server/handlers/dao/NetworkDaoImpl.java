package io.pne.veth.server.handlers.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NetworkDaoImpl implements INetworkDao {

    private static final Logger LOG = LoggerFactory.getLogger(NetworkDaoImpl.class);

    private final Map<String, TNetwork> map = new ConcurrentHashMap<>();

    @Override
    public void addNetwork(TNetwork aNetwork) {
        LOG.info("Adding network {} ...", aNetwork);
        TNetwork previous = map.put(aNetwork.id, aNetwork);
        if(previous != null) {
            LOG.warn("The previous network war replaced by new [previous={}, new={}]", previous, aNetwork);
        }
    }

    @Override
    public TNetwork findNetwork(String aNetworkId) {
        TNetwork network = map.get(aNetworkId);
        if(network == null) {
            LOG.warn("No network {} in {}", aNetworkId, map);
        }
        return network;
    }

    @Override
    public void removeNetwork(String aNetworkId) {
        TNetwork network = map.remove(aNetworkId);
        if(network == null) {
            LOG.warn("No network {}", aNetworkId);
        } else {
            LOG.info("Network was removed: {}", network);
        }
    }
}
