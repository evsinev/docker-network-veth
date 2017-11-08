package io.pne.veth.server.handlers.location.plugin;

import io.pne.veth.server.handlers.IJsonHandler;
import io.pne.veth.server.handlers.model.ActivateResponse;
import io.pne.veth.server.handlers.model.PluginType;

public class PluginActivateHandler implements IJsonHandler<Void, ActivateResponse> {
    @Override
    public ActivateResponse handle(Void aRequest) {
        return new ActivateResponse(PluginType.NetworkDriver);
    }

    @Override
    public Class<Void> getRequestClass() {
        return Void.class;
    }
}
