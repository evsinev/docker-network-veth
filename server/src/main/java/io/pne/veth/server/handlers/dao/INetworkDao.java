package io.pne.veth.server.handlers.dao;

public interface INetworkDao {

    void addNetwork(TNetwork aNetwork);

    TNetwork findNetwork(String aNetworkId);

    void removeNetwork(String aNetworkId);
}
