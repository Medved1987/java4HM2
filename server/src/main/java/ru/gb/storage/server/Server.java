package ru.gb.storage.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public  class Server {
    public static void main(String[] args) throws Exception{
        new Server().start();

    }

public  void start() throws InterruptedException {
    NioEventLoopGroup boosGroup = new NioEventLoopGroup(1);
    NioEventLoopGroup workerGroup = new NioEventLoopGroup();
    try {
        ServerBootstrap server = new ServerBootstrap();
        server
                .group(boosGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(
                                new ChannelInboundHandlerAdapter() {
                                }
                        )

                    }

                    @Override
                    protected void initChanel (NioSocketChannel ch) {
                        ch.pipeline() .addLast(
                                new ChannelInboundHandler() {
                                    @Override
                                    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
                                        System.out.println("Channel registered");

                                    }

                                    @Override
                                    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
                                        System.out.println("Channel Unregistered");

                                    }

                                    @Override
                                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                        System.out.println("channel Active");

                                    }

                                    @Override
                                    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                        System.out.println("channel Inactive");

                                    }

                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object mag) throws Exception {
                                        super.channelRead(ctx,mag);



                                    }




                                    @Override
                                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) throws Exception {
                                        System.out.println("cause");
                                        ctx.close();

                                    }


                                }



                        }
                        );
                    }
        }
    }
}
}