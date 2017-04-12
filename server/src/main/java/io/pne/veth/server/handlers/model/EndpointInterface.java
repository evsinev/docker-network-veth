package io.pne.veth.server.handlers.model;

import com.google.gson.annotations.SerializedName;

public class EndpointInterface {

    @SerializedName("Address")      public String address   ;
    @SerializedName("AddressIPv6")  public String addressIPv6   ;
    @SerializedName("MacAddress")   public String macAddress   ;

    @Override
    public String toString() {
        return "EndpointInterface{" +
                "address='" + address + '\'' +
                ", addressIPv6='" + addressIPv6 + '\'' +
                ", macAddress='" + macAddress + '\'' +
                '}';
    }
}
