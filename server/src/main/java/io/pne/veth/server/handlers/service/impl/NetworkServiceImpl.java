package io.pne.veth.server.handlers.service.impl;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import io.pne.veth.server.handlers.dao.IEndpointDao;
import io.pne.veth.server.handlers.dao.INetworkDao;
import io.pne.veth.server.handlers.dao.TEndpoint;
import io.pne.veth.server.handlers.dao.TNetwork;
import io.pne.veth.server.handlers.service.ICommandService;
import io.pne.veth.server.handlers.service.INetworkService;
import io.pne.veth.server.handlers.service.model.ShellCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.StringTokenizer;

public class NetworkServiceImpl implements INetworkService {

    private static final Logger LOG = LoggerFactory.getLogger(NetworkServiceImpl.class);

    private final INetworkDao     networkDao;
    private final IEndpointDao    endpointDao;
    private final Gson            gson;
    private final ICommandService commandService;

    public NetworkServiceImpl(INetworkDao networkDao, IEndpointDao endpointDao, Gson gson, ICommandService shellService) {
        this.networkDao = networkDao;
        this.endpointDao = endpointDao;
        this.gson = gson;
        this.commandService = shellService;
    }

    @Override
    public String getNetworkGateway(String aNetworkId, String aEndpointId) {
        TNetwork network = networkDao.findNetwork(aNetworkId);
        if(network == null && commandService != null) {
            network = findNetworkInDocker(aNetworkId);
            if(network != null) {
                networkDao.addNetwork(network);
            }
        }

        if(network != null) {
            return network.gateway;
        }

        final String gateway = createGatewayFromEndpoint(aEndpointId);
        LOG.warn("Got the gateway [{}] from the endpoint info", gateway);
        return gateway;
    }

    private String createGatewayFromEndpoint(String aEndpointId) {
        TEndpoint endpoint = endpointDao.findEndPoint(aEndpointId);
        String address = endpoint.ipAddress;
        int pos = address.lastIndexOf('.');
        return address.substring(0, pos ) + ".1";
    }

    private TNetwork findNetworkInDocker(String aNetworkId) {
        // command: docker network inspect dns-1-network
        ShellCommand command = new ShellCommand.Builder("docker")
                .add("network")
                .add("inspect")
                .add(aNetworkId)
                .build();
        try {
            String json = commandService.executeCommand(command);
            LOG.info("network inspect = {}", json);
            NetworkInspect inspect = parseNetworkInspect(gson, json);
            return new TNetwork(
                    aNetworkId
                    , inspect.ipam.cofigs[0].gateway
                    , inspect.ipam.cofigs[0].subnet
                    , inspect.name
            );
        } catch (Exception e) {
            LOG.error("Can't execute the command: " + command, e);
            return null;
        }

    }

    public static NetworkInspect parseNetworkInspect(Gson aGson, String aJson) {
        NetworkInspect[] inspects = aGson.fromJson(aJson, NetworkInspect[].class);
        return inspects[0];
    }

    public static class NetworkInspect {
        @SerializedName("Name") String name;
        @SerializedName("IPAM") IPAM   ipam;

        @Override
        public String toString() {
            return "NetworkInspect{" +
                    "name='" + name + '\'' +
                    ", ipam=" + ipam +
                    '}';
        }
    }

    public static class IPAM {
        @SerializedName("Config") Config cofigs[];

        @Override
        public String toString() {
            return "IPAM{" +
                    "cofigs=" + Arrays.toString(cofigs) +
                    '}';
        }
    }

    public static class Config {
        @SerializedName("Subnet") String subnet;
        @SerializedName("Gateway") String gateway;

        @Override
        public String toString() {
            return "Config{" +
                    "subnet='" + subnet + '\'' +
                    ", gateway='" + gateway + '\'' +
                    '}';
        }
    }
}
