package io.pne.veth.server.handlers.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Map;

public class CreateNetworkRequest {

    @SerializedName("NetworkID") public String              networkID;
    @SerializedName("Options")   public Map<String, Object> options;
    @SerializedName("IPv4Data")  public IPAMData[]          ipv4Data;
    @SerializedName("IPv6Data")  public IPAMData[]          ipv6Data;


    @Override
    public String toString() {
        return "CreateNetworkRequest{" +
                "NetworkID='" + networkID + '\'' +
                ", Options=" + options +
                ", IPv4Data=" + Arrays.toString(ipv4Data) +
                ", IPv6Data=" + Arrays.toString(ipv6Data) +
                '}';
    }
}
