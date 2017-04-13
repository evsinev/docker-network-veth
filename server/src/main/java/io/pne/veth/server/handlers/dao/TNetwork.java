package io.pne.veth.server.handlers.dao;

public class TNetwork {

    public final String id;
    public final String gateway;
    public final String pool;
    public final String interfaceSuffix;

    public TNetwork(String id, String gateway, String pool, String aSuffix) {
        this.id = id;
        this.gateway = gateway;
        this.pool = pool;
        interfaceSuffix = aSuffix;
    }

    @Override
    public String toString() {
        return "TNetwork{" +
                "id='" + id + '\'' +
                ", gateway='" + gateway + '\'' +
                ", pool='" + pool + '\'' +
                ", interfaceSuffix='" + interfaceSuffix + '\'' +
                '}';
    }
}
