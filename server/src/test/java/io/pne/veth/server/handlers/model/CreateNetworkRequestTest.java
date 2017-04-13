package io.pne.veth.server.handlers.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateNetworkRequestTest {

    @Test
    public void gson() {
        String json = "{\"NetworkID\":\"9f55eb121867c859bf6bdac33127f79aed641976263138d99e34637edf0cc3fe\",\"Options\":{\"com.docker.network.enable_ipv6\":false,\"com.docker.network.generic\":{}},\"IPv4Data\":[{\"AddressSpace\":\"LocalDefault\",\"Gateway\":\"172.18.0.1/16\",\"Pool\":\"172.18.0.0/16\"}],\"IPv6Data\":[]}";
        System.out.println("json = " + json);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        CreateNetworkRequest request = gson.fromJson(json, CreateNetworkRequest.class);
        System.out.println("request = " + request);
        assertEquals("9f55eb121867c859bf6bdac33127f79aed641976263138d99e34637edf0cc3fe", request.networkID);
    }

}