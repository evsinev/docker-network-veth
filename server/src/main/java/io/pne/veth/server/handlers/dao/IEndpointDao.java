package io.pne.veth.server.handlers.dao;

public interface IEndpointDao {

    void addEndpoint(TEndpoint aEndpoint);

    TEndpoint findEndPoint(String aEndpointId);

    void removeEndpoint(String aEndpointId);
}
