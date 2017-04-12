package io.pne.veth.server.handlers;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.AsciiString;
import io.pne.veth.server.http.IHttpRequestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class DispatchHandler implements IHttpRequestListener {

    private static final AsciiString HEADER_CONTENT_LENGTH = new AsciiString("Content-Length");

    private static final Logger LOG = LoggerFactory.getLogger(DispatchHandler.class);

    private final HashMap<String, IHttpRequestListener> listeners;

    private DispatchHandler(HashMap<String, IHttpRequestListener> listeners) {
        this.listeners = listeners;
    }

    @Override
    public FullHttpResponse createResponse(FullHttpRequest aRequest) {

        final String contentLength = aRequest.headers().get(HEADER_CONTENT_LENGTH);
        LOG.info("Content length = {}", contentLength);


        IHttpRequestListener listener = listeners.get(aRequest.uri());
        if(listener == null) {
            return error("Not Found", NOT_FOUND);
        }

        return listener.createResponse(aRequest);
    }

    private FullHttpResponse error(String aMessage, HttpResponseStatus status) {
        ByteBuf buffer = Unpooled.wrappedBuffer(aMessage.getBytes());
        return new DefaultFullHttpResponse(HTTP_1_1, status, buffer, false);

    }

    public static class Builder {

        private final HashMap<String, IHttpRequestListener> listeners = new HashMap<>();
        private  Gson gson;

        public Builder add(String aPath, IHttpRequestListener aListener) {
            listeners.put(aPath, aListener);
            return this;
        }

        public Builder add(String aPath, IJsonHandler aHandler) {
            listeners.put(aPath, new JsonHandler<>(gson, aHandler));
            return this;
        }

        public DispatchHandler build() {
            return new DispatchHandler(listeners);
        }

        public Builder gson(Gson aGson) {
            gson = aGson;
            return this;
        }
    }
}
