package io.pne.veth.server.handlers.location.networkdriver;

import io.pne.veth.server.handlers.IJsonHandler;
import io.pne.veth.server.handlers.model.CapabilitiesResponse;
import io.pne.veth.server.handlers.model.CapabilityScope;

public class GetCapabilitiesHandler implements IJsonHandler<Void, CapabilitiesResponse> {
    @Override
    public CapabilitiesResponse handle(Void aRequest) {
        return new CapabilitiesResponse(CapabilityScope.local);
    }

    @Override
    public Class<Void> getRequestClass() {
        return Void.class;
    }
}
