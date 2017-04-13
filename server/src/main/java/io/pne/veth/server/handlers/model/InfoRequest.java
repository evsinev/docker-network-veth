package io.pne.veth.server.handlers.model;

import com.google.gson.annotations.SerializedName;

public class InfoRequest {

    @SerializedName("NetworkID") public String              networkID;
    @SerializedName("EndpointID") public String             endpointId;

    @Override
    public String toString() {
        return "InfoRequest{" +
                "networkID='" + networkID + '\'' +
                ", endpointId='" + endpointId + '\'' +
                '}';
    }
}
