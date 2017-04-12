package io.pne.veth.server.handlers;

public interface IJsonHandler<I,O> {

    O handle(I aRequest);

}
