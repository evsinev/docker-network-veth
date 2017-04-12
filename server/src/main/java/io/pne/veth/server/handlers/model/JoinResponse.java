package io.pne.veth.server.handlers.model;

import com.google.gson.annotations.SerializedName;

public class JoinResponse {

    @SerializedName("InterfaceName") public InterfaceName              interfaceName;
    @SerializedName("Gateway") public String              gateway;
    @SerializedName("GatewayIPv6") public String              gatewayIPv6;
    @SerializedName("DisableGatewayService") public boolean              disableGatewayService;
    @SerializedName("StaticRoutes") public StaticRoute[]              staticRoutes;

}
