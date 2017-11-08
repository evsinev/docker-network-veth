package io.pne.veth.server.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    public static final int MAX_CONTENT_LENGTH = 10 * 1024 * 1024;

    private final IHttpRequestListener requestListener;

    public HttpServerInitializer(IHttpRequestListener requestListener) {
        this.requestListener = requestListener;
    }

    @Override
    public void initChannel(SocketChannel aChannel) throws Exception {
        ChannelPipeline pipeline = aChannel.pipeline();
        pipeline.addLast("encoder"    , new HttpResponseEncoder());
        pipeline.addLast("decoder"    , new HttpRequestDecoder(4096, 8192, 8192, false));
        pipeline.addLast("aggregator" , new HttpObjectAggregator(MAX_CONTENT_LENGTH)); // handle HttpContents
        pipeline.addLast("chunked", new ChunkedWriteHandler());
        //        pipeline.addLast("handler"    , new HttpServerHandler(requestListener));

        pipeline.addLast("handler"    , new HttpServerHandler(requestListener));

    }
}
