package io.pne.veth.server.handlers.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Map;

public class DeleteNetworkRequest {

    @SerializedName("NetworkID") public String              networkID;

    @Override
    public String toString() {
        return "DeleteNetworkRequest{" +
                "networkID='" + networkID + '\'' +
                '}';
    }
}
