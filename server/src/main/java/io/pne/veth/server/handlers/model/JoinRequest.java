package io.pne.veth.server.handlers.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class JoinRequest {

    @SerializedName("NetworkID") public String              networkID;
    @SerializedName("EndpointID") public String             endpointId;
    @SerializedName("SandboxKey") public String             sandboxKey;

    @SerializedName("Options")  public Map<String, Object> options;

    @Override
    public String toString() {
        return "JoinRequest{" +
                "networkID='" + networkID + '\'' +
                ", endpointId='" + endpointId + '\'' +
                ", sandboxKey='" + sandboxKey + '\'' +
                ", options=" + options +
                '}';
    }
}
