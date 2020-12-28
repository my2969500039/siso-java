package com.siso.WSnetty;
import groovy.util.logging.Slf4j;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/**
 * netty配置类
 */


@Slf4j
public class netty extends Thread{

    public void start(){
        System.out.println("启动成功");
        /* 主从线程组模型
         */
        EventLoopGroup mainGroup =new NioEventLoopGroup();
        EventLoopGroup subGroup=new NioEventLoopGroup();
        try {//创建核心类
            ServerBootstrap serverBootstrap1 =new ServerBootstrap();
            ServerBootstrap serverBootstrap2 =new ServerBootstrap();
            serverBootstrap1.group(mainGroup,subGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new WSServerInitialzer());//添加助手类
            ChannelFuture channelFuture1= serverBootstrap1.bind(8085).sync();

            serverBootstrap2.group(mainGroup,subGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new socketInitalzer());//添加助手类
            ChannelFuture channelFuture2= serverBootstrap2.bind(8086).sync();
            channelFuture1.channel().closeFuture().sync();
            channelFuture2.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            mainGroup.shutdownGracefully();
            subGroup.shutdownGracefully();//优雅的关闭主从线程池
        }
    }


}