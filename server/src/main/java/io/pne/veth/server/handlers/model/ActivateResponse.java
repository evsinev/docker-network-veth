package io.pne.veth.server.handlers.model;

public class ActivateResponse {
    public ActivateResponse(PluginType anImplements) {
        Implements = new PluginType[]{anImplements};
    }

    PluginType[] Implements;
}
