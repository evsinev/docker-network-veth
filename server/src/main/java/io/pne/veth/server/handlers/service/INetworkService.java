package io.pne.veth.server.handlers.service;

import io.pne.veth.server.handlers.dao.TNetwork;

public interface INetworkService {

    String getNetworkGateway(String aNetwork, String aEndpointId);
}
