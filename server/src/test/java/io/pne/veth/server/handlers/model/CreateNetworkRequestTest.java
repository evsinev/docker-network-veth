package io.pne.veth.server.handlers.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateNetworkRequestTest {

    @Test
    public void gson() {
        String json = "{\n" +
                "    \"NetworkID\": \"1b99d6a68a5ec70bd7f8e88a55448a06cc8206e8ad9b0b3de932b4a4fa1c1c94\",\n" +
                "    \"Options\": {\n" +
                "        \"com.docker.network.enable_ipv6\": false,\n" +
                "        \"com.docker.network.generic\": {}\n" +
                "    },\n" +
                "    \"IPv4Data\": [\n" +
                "        {\n" +
                "            \"AddressSpace\": \"LocalDefault\",\n" +
                "            \"Gateway\": \"172.18.0.1/16\",\n" +
                "            \"Pool\": \"172.18.0.0/16\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"IPv6Data\": []\n" +
                "}";
        System.out.println("json = " + json);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        CreateNetworkRequest request = gson.fromJson(json, CreateNetworkRequest.class);
        System.out.println("request = " + request);
        assertEquals("1b99d6a68a5ec70bd7f8e88a55448a06cc8206e8ad9b0b3de932b4a4fa1c1c94", request.networkID);
    }

}