package io.pne.veth.server.handlers.model;

import com.google.gson.annotations.SerializedName;

public class RevokeExternalConnectivityRequest {
    @SerializedName("NetworkID")  public String networkID;
    @SerializedName("EndpointID") public String endpointId;

    @Override
    public String toString() {
        return "RevokeExternalConnectivityRequest{" +
                "networkID='" + networkID + '\'' +
                ", endpointId='" + endpointId + '\'' +
                '}';
    }
}
