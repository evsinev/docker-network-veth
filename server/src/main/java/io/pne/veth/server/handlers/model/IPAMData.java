package io.pne.veth.server.handlers.model;

import java.util.Map;

public class IPAMData {
    public String AddressSpace;
    public String Pool;
    public String Gateway;
    public Map<String, Object> AuxAddresses;

    @Override
    public String toString() {
        return "IPAMData{" +
                "AddressSpace='" + AddressSpace + '\'' +
                ", Pool='" + Pool + '\'' +
                ", Gateway='" + Gateway + '\'' +
                ", AuxAddresses=" + AuxAddresses +
                '}';
    }
}
