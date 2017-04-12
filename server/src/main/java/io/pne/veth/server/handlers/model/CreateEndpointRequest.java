package io.pne.veth.server.handlers.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CreateEndpointRequest {

    @SerializedName("NetworkID") public String              networkID;
    @SerializedName("EndpointID") public String             endpointId;

    @SerializedName("Options")  public Map<String, Object> options;
    @SerializedName("Interface") public EndpointInterface endpointInterface;

    @Override
    public String toString() {
        return "CreateEndpointRequest{" +
                "networkID='" + networkID + '\'' +
                ", endpointId='" + endpointId + '\'' +
                ", options=" + options +
                ", endpointInterface=" + endpointInterface +
                '}';
    }
}
