package io.pne.veth.server.handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.pne.veth.server.handlers.dao.INetworkDao;
import io.pne.veth.server.handlers.dao.NetworkDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonHandlerTest {

    @Test
    public void test() {
        Gson gson        = new GsonBuilder().setPrettyPrinting().create();
        INetworkDao networkDao  = new NetworkDaoImpl();

        JsonHandler handler = new JsonHandler(gson, new NetworkDriver_CreateNetworkHandler(networkDao));
        String json = "{\"NetworkID\":\"9f55eb121867c859bf6bdac33127f79aed641976263138d99e34637edf0cc3fe\",\"Options\":{\"com.docker.network.enable_ipv6\":false,\"com.docker.network.generic\":{}},\"IPv4Data\":[{\"AddressSpace\":\"LocalDefault\",\"Gateway\":\"172.18.0.1/16\",\"Pool\":\"172.18.0.0/16\"}],\"IPv6Data\":[]}";
        final Object o = handler.parseRequest("/test", json);
        System.out.println("o = " + o);
        System.out.println("o.getClass() = " + o.getClass());
    }
}