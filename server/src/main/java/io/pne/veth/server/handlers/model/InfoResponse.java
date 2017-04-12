package io.pne.veth.server.handlers.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class InfoResponse {

    @SerializedName("Value")  public Map<String, String> value;

}
