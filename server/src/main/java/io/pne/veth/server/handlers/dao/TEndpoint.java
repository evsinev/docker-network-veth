package io.pne.veth.server.handlers.dao;

public class TEndpoint {

    public final String id;
    public final String ipAddress;
    public final String macAddress;
    public final String device;

    public TEndpoint(String id, String ipAddress, String macAddress, String aDevice) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        device = aDevice;
    }
}
