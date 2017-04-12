package io.pne.veth.server.handlers.model;

import com.google.gson.annotations.SerializedName;

public class StaticRoute {

    @SerializedName("Destination") public String              destination;
    @SerializedName("RouteType") public int              routeType;
    @SerializedName("NextHop") public String              nextHop;

}
