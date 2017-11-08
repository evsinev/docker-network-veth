package io.pne.veth.server.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final Logger LOG = LoggerFactory.getLogger(HttpServerHandler.class);


    private final IHttpRequestListener requestListener;

    public HttpServerHandler(IHttpRequestListener requestListener) {
        this.requestListener = requestListener;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext aContext, FullHttpRequest aRequest) throws Exception {

        FullHttpResponse response = createResponse(aRequest);

        response.headers()
                .add("vetch", "1.0")
                .setInt(CONTENT_LENGTH, response.content().readableBytes());

        aContext.writeAndFlush(response)
                .addListener(ChannelFutureListener.CLOSE);
    }

    private FullHttpResponse createResponse(FullHttpRequest aRequest) {
        String uri = aRequest.uri();
        try {
//            debugLog(aRequest, uri);
            return requestListener.createResponse(aRequest);
        } catch (Exception e) {
            LOG.error("Error while processing " + uri, e);
            ByteBuf buffer = Unpooled.wrappedBuffer((""+e.getMessage()).getBytes());
            return  new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR, buffer, false);
        }
    }

    private void debugLog(HttpRequest aRequest, String uri) {
        LOG.debug("uri = {}", uri);
        for (Map.Entry<String, String> entry : aRequest.headers()) {
            LOG.debug("{} = {}", entry.getKey(), entry.getValue());
        }
        LOG.debug("");
    }


//    @Override
//    public void channelReadComplete(ChannelHandlerContext aContext) throws Exception {
////        aContext.flush().close();
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOG.error("Error processing http request ", cause);
        ctx.close();
    }
}
