package io.pne.veth.server.handlers.model;

import com.google.gson.annotations.SerializedName;

public class CreateEndpointResponse {

    @SerializedName("Interface") public EndpointInterface endpointInterface;

    @Override
    public String toString() {
        return "CreateEndpointResponse{" +
                "endpointInterface=" + endpointInterface +
                '}';
    }
}
