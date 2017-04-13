package io.pne.veth.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.pne.veth.server.handlers.*;
import io.pne.veth.server.handlers.dao.EndpointDaoImpl;
import io.pne.veth.server.handlers.dao.IEndpointDao;
import io.pne.veth.server.handlers.dao.INetworkDao;
import io.pne.veth.server.handlers.dao.NetworkDaoImpl;
import io.pne.veth.server.handlers.service.ICommandService;
import io.pne.veth.server.handlers.service.INetworkService;
import io.pne.veth.server.handlers.service.impl.CommandServiceSsh;
import io.pne.veth.server.handlers.service.impl.NetworkServiceImpl;
import io.pne.veth.server.http.HttpServerInitializer;

import static io.netty.channel.ChannelOption.*;

public class ServerApplication {

    void run() throws InterruptedException {

        IEndpointDao    endpointDao    = new EndpointDaoImpl();
        INetworkDao     networkDao     = new NetworkDaoImpl();
        Gson            gson           = new GsonBuilder().setPrettyPrinting().create();
        ICommandService commandService = new CommandServiceSsh();
        INetworkService networkService = new NetworkServiceImpl(networkDao, endpointDao, gson, commandService);

        DispatchHandler dispatchHandler = new DispatchHandler.Builder()
            .gson(gson)
            .add("/Plugin.Activate"               , new PluginActivateHandler()                                )
            .add("/NetworkDriver.GetCapabilities" , new NetworkDriver_GetCapabilitiesHandler()                 )
            .add("/NetworkDriver.CreateNetwork"   , new NetworkDriver_CreateNetworkHandler(networkDao)         )
            .add("/NetworkDriver.DeleteNetwork"   , new NetworkDriver_DeleteNetworkHandler(networkDao)         )
            .add("/NetworkDriver.CreateEndpoint"  , new NetworkDriver_CreateEndPointHandler(endpointDao)       )
            .add("/NetworkDriver.Join"            , new NetworkDriver_JoinHandler(endpointDao, networkService) )
            .add("/NetworkDriver.EndpointOperInfo", new NetworkDriver_EndpointOperInfo(endpointDao)           )
            .add("/NetworkDriver.ProgramExternalConnectivity", new NetworkDriver_ProgramExternalConnectivity())
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
