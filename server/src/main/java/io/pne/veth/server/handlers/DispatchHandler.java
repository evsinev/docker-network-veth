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
import java.util.Map;

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

        String uri = aRequest.uri();
        IHttpRequestListener listener = listeners.get(uri);
        if(listener == null) {
            return error(uri, "Not Found", NOT_FOUND);
        }

        return listener.createResponse(aRequest);
    }

    private FullHttpResponse error(String aUrl, String aMessage, HttpResponseStatus status) {
        ByteBuf buffer = Unpooled.wrappedBuffer(aMessage.getBytes());
        LOG.error("{} - {}", aUrl, aMessage);
        return new DefaultFullHttpResponse(HTTP_1_1, status, buffer, false);

    }

    public static class Builder {

        private final HashMap<String, IHttpRequestListener> listeners = new HashMap<>();
        private  Gson gson;

        @SuppressWarnings("unused")
        public Builder add(String aPath, IHttpRequestListener aListener) {
            listeners.put(aPath, aListener);
            return this;
        }

        public <I, O> Builder add(String aPath, IJsonHandler<I, O> aHandler) {
            listeners.put(aPath, new JsonHandler<>(gson, aHandler));
            return this;
        }

        public DispatchHandler build() {
            LOG.info("Dispatch URL:");

            for (Map.Entry<String, IHttpRequestListener> entry : listeners.entrySet()) {
                final IHttpRequestListener listener = entry.getValue();
                if(listener instanceof JsonHandler) {
                    JsonHandler jsonListener = (JsonHandler) listener;
                    LOG.info("    {} - {} - {}", entry.getKey(), jsonListener.getRequestClass().getSimpleName(), jsonListener.getHandlerClass().getSimpleName());
                } else {
                    LOG.info("    {} - {}", entry.getKey(), listener.getClass().getSimpleName());
                }

            }
            return new DispatchHandler(listeners);
        }

        public Builder gson(Gson aGson) {
            gson = aGson;
            return this;
        }
    }
}
