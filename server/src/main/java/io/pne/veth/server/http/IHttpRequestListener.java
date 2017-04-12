package io.pne.veth.server.http;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;

public interface IHttpRequestListener {

    FullHttpResponse createResponse(FullHttpRequest aRequest);

}
