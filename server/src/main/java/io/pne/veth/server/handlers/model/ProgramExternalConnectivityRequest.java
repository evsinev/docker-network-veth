package io.pne.veth.server.handlers.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ProgramExternalConnectivityRequest {
    @SerializedName("NetworkID") public String              networkID;
    @SerializedName("EndpointID") public String             endpointId;

    @SerializedName("Options")  public Map<String, Object> options;

}
