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
import io.pne.veth.server.handlers.location.networkdriver.*;
import io.pne.veth.server.handlers.location.plugin.PluginActivateHandler;
import io.pne.veth.server.handlers.service.ICommandService;
import io.pne.veth.server.handlers.service.INetworkService;
import io.pne.veth.server.handlers.service.impl.CommandServiceImpl;
import io.pne.veth.server.handlers.service.impl.NetworkServiceImpl;
import io.pne.veth.server.http.HttpServerInitializer;

import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import static io.netty.channel.ChannelOption.*;

public class ServerApplication {

    private void run() throws InterruptedException {

        IEndpointDao    endpointDao    = new EndpointDaoImpl();
        INetworkDao     networkDao     = new NetworkDaoImpl();
        Gson            gson           = new GsonBuilder().setPrettyPrinting().create();
        ICommandService commandService = new CommandServiceImpl();
        INetworkService networkService = new NetworkServiceImpl(networkDao, endpointDao, gson, commandService);

        DispatchHandler dispatchHandler = new DispatchHandler.Builder()
            .gson(gson)
            .add("/Plugin.Activate"               , new PluginActivateHandler()                  )
            .add("/NetworkDriver.GetCapabilities" , new GetCapabilitiesHandler()                 )
            .add("/NetworkDriver.CreateNetwork"   , new CreateNetworkHandler(networkDao)         )
            .add("/NetworkDriver.DeleteNetwork"   , new DeleteNetworkHandler(networkDao)         )
            .add("/NetworkDriver.CreateEndpoint"  , new CreateEndPointHandler(endpointDao)       )
            .add("/NetworkDriver.DeleteEndpoint"  , new DeleteEndPointHandler(endpointDao)       )
            .add("/NetworkDriver.Leave"           , new LeaveHandler(endpointDao)                )
            .add("/NetworkDriver.Join"            , new JoinHandler(endpointDao, networkService) )
            .add("/NetworkDriver.EndpointOperInfo"           , new EndpointOperInfoHandler(endpointDao) )
            .add("/NetworkDriver.ProgramExternalConnectivity", new ProgramExternalConnectivityHandler() )
            .add("/NetworkDriver.RevokeExternalConnectivity" , new RevokeExternalConnectivityHandler()  )
            .build();

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            new ServerBootstrap()
                    .group          ( new NioEventLoopGroup()                    )
                    .childHandler   ( new HttpServerInitializer(dispatchHandler) )
                    .channel        ( NioServerSocketChannel.class               )
                    .option         ( SO_BACKLOG, 1024                     )
                    .childOption    ( ALLOCATOR, PooledByteBufAllocator.DEFAULT  )

                    .bind           (new InetSocketAddress("127.0.0.1", 9090))
                    .sync()
                    .channel()

                    .closeFuture()
                    .sync()
            ;
        } finally {
            group.shutdownGracefully().sync();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        new ServerApplication().run();
    }

}
