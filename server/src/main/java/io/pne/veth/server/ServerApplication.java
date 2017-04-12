package io.pne.veth.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.pne.veth.server.handlers.*;
import io.pne.veth.server.http.HttpServerInitializer;

import static io.netty.channel.ChannelOption.*;

public class ServerApplication {

    void run() throws InterruptedException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        DispatchHandler dispatchHandler = new DispatchHandler.Builder()
                .gson(gson)
                .add("/Plugin.Activate"              , new PluginActivateHandler()                  )
                .add("/NetworkDriver.GetCapabilities", new NetworkDriver_GetCapabilitiesHandler()   )
                .add("/NetworkDriver.CreateNetwork"  , new NetworkDriver_CreateNetworkHandler()     )
                .add("/NetworkDriver.DeleteNetwork"  , new NetworkDriver_DeleteNetworkHandler()     )
                .add("/NetworkDriver.CreateEndpoint" , new NetworkDriver_CreateEndPointHandler()    )
                .add("/NetworkDriver.Join"           , new NetworkDriver_JoinHandler()              )
                .add("/NetworkDriver.ProgramExternalConnectivity", new NetworkDriver_ProgramExternalConnectivity())
                .add("/NetworkDriver.EndpointOperInfo", new NetworkDriver_EndpointOperInfo()        )

                .build();

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            new ServerBootstrap()
                    .group          ( new NioEventLoopGroup()                    )
                    .childHandler   ( new HttpServerInitializer(dispatchHandler) )
                    .channel        ( NioServerSocketChannel.class               )
                    .option         ( SO_BACKLOG, 1024                           )
                    .option         ( TCP_NODELAY, true                          )
                    .childOption    ( ALLOCATOR, PooledByteBufAllocator.DEFAULT  )

                    .bind           (9090                                        ).sync()

                    .channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        new ServerApplication().run();
    }

}
