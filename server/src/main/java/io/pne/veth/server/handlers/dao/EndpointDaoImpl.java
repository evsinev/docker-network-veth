package io.pne.veth.server.handlers.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EndpointDaoImpl implements IEndpointDao {

    final Map<String, TEndpoint> map = new ConcurrentHashMap<>();

    @Override
    public void addEndpoint(TEndpoint aEndpoint) {
        map.put(aEndpoint.id, aEndpoint);
    }

    @Override
    public TEndpoint findEndPoint(String aEndpointId) {
        return map.get(aEndpointId);
    }

    @Override
    public void removeEndpoint(String aEndpointId) {
        map.remove(aEndpointId);
    }
}
