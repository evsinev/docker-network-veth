package io.pne.veth.server.handlers.service.impl;

import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

public class NetworkServiceImplTest {

    @Test
    public void testParseNetworkInspect() throws Exception {
        String json = "[\n" +
                "    {\n" +
                "        \"Name\": \"dns-1-network\",\n" +
                "        \"Id\": \"fa7cb1370dce0911aa4522ed5d9c182a9b85b974ffdc77b89417c5a7a900211e\",\n" +
                "        \"Created\": \"2017-04-12T18:25:55.219723947Z\",\n" +
                "        \"Scope\": \"local\",\n" +
                "        \"Driver\": \"veth\",\n" +
                "        \"EnableIPv6\": false,\n" +
                "        \"IPAM\": {\n" +
                "            \"Driver\": \"default\",\n" +
                "            \"Options\": {},\n" +
                "            \"Config\": [\n" +
                "                {\n" +
                "                    \"Subnet\": \"192.168.4.0/24\",\n" +
                "                    \"Gateway\": \"192.168.4.1\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"Internal\": false,\n" +
                "        \"Attachable\": false,\n" +
                "        \"Containers\": {},\n" +
                "        \"Options\": {\n" +
                "            \"ip.pne.veth.interface.suffix\": \"dns-1\"\n" +
                "        },\n" +
                "        \"Labels\": {}\n" +
                "    }\n" +
                "]";
        final NetworkServiceImpl.NetworkInspect networkInspect = NetworkServiceImpl.parseNetworkInspect(new Gson(), json);
        System.out.println("networkInspect = " + networkInspect);
    }
}