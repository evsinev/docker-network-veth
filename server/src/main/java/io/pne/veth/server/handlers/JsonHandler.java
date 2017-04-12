package io.pne.veth.server.handlers;

import com.google.gson.Gson;
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

    private static final AsciiString HEADER_CONTENT_LENGTH = new AsciiString("Content-Length");


    public JsonHandler(Gson aGson, IJsonHandler<I, O> handler) {
        this.handler = handler;
        gson = aGson;
        requestClass = findRequestClass(handler);
    }

    private Class<I> findRequestClass(IJsonHandler<I, O> aHandler) {
        Class handlerClass = aHandler.getClass();
        for (Method method : handlerClass.getMethods()) {
            if(method.getName().equals("handle")) {
                //noinspection unchecked
                return (Class<I>) method.getParameterTypes()[0];
            }
        }
        throw new IllegalStateException("No method handle(I aRequest) in the " + handlerClass.getSimpleName());
    }

    @Override
    public FullHttpResponse createResponse(FullHttpRequest aRequest) {
        int contentLength = aRequest.headers().getInt(HEADER_CONTENT_LENGTH);
        LOG.info("Content length = {}", contentLength);

        if(requestClass.equals(Void.class) && contentLength != 0) {
            return error(aRequest, BAD_REQUEST, "Request must be empty but it has body with length of " + contentLength);
        }

        String body = aRequest.content().toString(StandardCharsets.UTF_8);
        LOG.info("body = {}", body);
        I requestObject = gson.fromJson(body, requestClass);
        LOG.info("request object = {}", requestObject);

        O response = handler.handle(requestObject);

        String outputJson = gson.toJson(response);

        LOG.info("output = {}", outputJson);

        return ok(aRequest, outputJson);
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
}
