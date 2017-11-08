package io.pne.veth.server.handlers.model;

import com.google.gson.annotations.SerializedName;

public class LeaveRequest {

    @SerializedName("NetworkID")  public String networkID;
    @SerializedName("EndpointID") public String endpointId;

    @Override
    public String toString() {
        return "CreateEndpointRequest{" +
                "networkID='" + networkID + '\'' +
                ", endpointId='" + endpointId + '\'' +
                '}';
    }
}
