package io.pne.veth.server.handlers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;
import io.pne.veth.server.http.IHttpRequestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class JsonHandler<I, O> implements IHttpRequestListener {

    private static final Logger LOG = LoggerFactory.getLogger(JsonHandler.class);

    private static final AsciiString CONTENT_LENGTH = new AsciiString("Content-Length");

    private final IJsonHandler<I, O> handler;
    private final Class<I> requestClass;

    private final Gson gson;
    private final JsonParser parser = new JsonParser();

    private static final AsciiString HEADER_CONTENT_LENGTH = new AsciiString("Content-Length");


    public JsonHandler(Gson aGson, IJsonHandler<I, O> handler) {
        this.handler = handler;
        gson = aGson;
        requestClass = handler.getRequestClass();
        if(requestClass.isAssignableFrom(Object.class)) {
            throw new IllegalArgumentException(handler.getClass().getSimpleName() + " has wrong argument in the method handler: " + requestClass);
        }
    }

    @Override
    public FullHttpResponse createResponse(FullHttpRequest aRequest) {
        int contentLength = aRequest.headers().getInt(HEADER_CONTENT_LENGTH);

        if(requestClass.equals(Void.class) && contentLength != 0) {
            return error(aRequest, BAD_REQUEST, "Request must be empty but it has body with length of " + contentLength);
        }

        String body = aRequest.content().toString(StandardCharsets.UTF_8);

        I requestObject = parseRequest(aRequest.uri(), body);

        LOG.info("request object = {}", requestObject);

        O response = handler.handle(requestObject);

        String outputJson = gson.toJson(response);

        LOG.info("Response:\n{}", outputJson);

        return ok(aRequest, outputJson);
    }

    protected I parseRequest(String aUrl, String body) {
        JsonElement jsonElement = parser.parse(body);
        LOG.info("Request for {}\n{}", aUrl, gson.toJson(jsonElement));
        return gson.fromJson(jsonElement, requestClass);
    }


    private void dumpRequest(FullHttpRequest aRequest, String body) {
    }

    private FullHttpResponse error(HttpRequest aRequest, HttpResponseStatus aStatus, String aMessage) {
        LOG.error("Bad Request {} : {}", aRequest.uri(), aMessage);
        ByteBuf buffer = Unpooled.wrappedBuffer(aMessage.getBytes());
        return  new DefaultFullHttpResponse(HTTP_1_1, aStatus, buffer, false);
    }

    private FullHttpResponse ok(HttpRequest aRequest, String aMessage) {
        ByteBuf buffer = Unpooled.wrappedBuffer(aMessage.getBytes());
        final DefaultFullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, buffer, false);
        response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
        return response;
    }

    @Override
    public String toString() {
        return "JsonHandler{" +
                "handler=" + handler +
                ", requestClass=" + requestClass +
                '}';
    }

    public Class<I> getRequestClass() {
        return requestClass;
    }

    public Class<? extends IJsonHandler> getHandlerClass() {
        return handler.getClass();
    }
}
