package io.pne.veth.server.handlers.model;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateEndpointRequestTest {

    @Test
    public void deserialize() {
        String json = "{\n" +
                "  \"NetworkID\": \"fa7cb1370dce0911aa4522ed5d9c182a9b85b974ffdc77b89417c5a7a900211e\",\n" +
                "  \"EndpointID\": \"3c9ff4e65a4d2590f74d0d6925c36b5394f55d269dd186759032511de5a63e27\",\n" +
                "  \"Interface\": {\n" +
                "    \"Address\": \"192.168.4.2/24\",\n" +
                "    \"AddressIPv6\": \"\",\n" +
                "    \"MacAddress\": \"62:7b:0b:7d:54:8d\"\n" +
                "  },\n" +
                "  \"Options\": {\n" +
                "    \"com.docker.network.endpoint.exposedports\": [],\n" +
                "    \"com.docker.network.endpoint.macaddress\": \"YnsLfVSN\",\n" +
                "    \"com.docker.network.portmap\": []\n" +
                "  }\n" +
                "}";

        CreateEndpointRequest request = new Gson().fromJson(json, CreateEndpointRequest.class);
        assertNotNull(request.endpointInterface);
        assertNotNull(request.endpointId);
        assertEquals("192.168.4.2/24", request.endpointInterface.address);
    }

}