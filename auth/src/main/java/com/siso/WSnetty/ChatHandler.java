package com.siso.WSnetty;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.*;


import com.alibaba.fastjson.JSONObject;
import com.siso.Result.Result;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 *
 * @Description: 处理消息的handler
 * TextWebSocketFrame： 在netty中，是用于为websocket专门处理文本的对象，frame是消息的载体
 */
@Service("ServerRunner")
public class ChatHandler{


    @Service
    @Component
    public static class Socket extends SimpleChannelInboundHandler<String> {

        private static final ChannelGroup clientsSocket = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        private static final Map<Channel,String> channels =new HashMap<>();

        public static Socket socket;

        @Autowired
        private ModubusChannel modubusChannel;

        @PostConstruct
        public void  init(){
            socket=this;
            socket.modubusChannel=this.modubusChannel;

        }

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            if (evt instanceof IdleStateEvent) {
                IdleState state = ((IdleStateEvent) evt).state();
                if (state == IdleState.READER_IDLE) {
                    ctx.writeAndFlush("heart");
//                        ctx.disconnect();
                }
            } else {
                super.userEventTriggered(ctx, evt);
            }
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg)
                throws Exception {
             if (channels.containsKey(ctx.channel())){

            }
             else {
                 Result result= socket.modubusChannel.register(msg);
                 if (result.getCode()!=null&&result.getCode()==201){
                     clientsSocket.add(ctx.channel());
                     channels.put(ctx.channel(),result.getMessage());
                     ctx.writeAndFlush("\\ACK");
                 }
             }

        }

        /**
         * 移除
         * @param ctx
         * @throws Exception
         */
        @Override
        public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
            socket.modubusChannel.removeRegister(channels.get(ctx.channel()));
            clientsSocket.remove(ctx.channel());
            channels.remove(ctx.channel());
        }

        /*
         * 如果连接错误事件
         */
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            // TODO Auto-generated method stub
            System.err.println(cause);
            cause.printStackTrace();
            ctx.close();
        }


        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush("Welcome to "
                    + InetAddress.getLocalHost().getHostName() + " service!\n");
            super.channelActive(ctx);
        }

    }

    @Service
    public static class websocket extends SimpleChannelInboundHandler<TextWebSocketFrame> {
            // 用于记录和管理所有客户端的channle
            public  static ChannelGroup clients =
                    new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
//            public static  List<ChannelGroup>list=new ArrayList<>();

            @Override
            protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg)
                    throws Exception {
                System.out.println(msg);
                clients.writeAndFlush(
                        new TextWebSocketFrame(
                                "[服务器在]" + LocalDateTime.now()
                                        + "接受到消息, 消息为：" + msg.text()));
            }


        public void seed(String title,String content,String image,Long id){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("title",title);
            jsonObject.put("content",content);
            jsonObject.put("image",image);
            jsonObject.put("id","http://aacz3m.natappfree.cc/noticet/article/2/"+id);
            clients.writeAndFlush( new TextWebSocketFrame(jsonObject.toString()));
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush("Welcome to "
                    + InetAddress.getLocalHost().getHostName() + " service!\n");
            super.channelActive(ctx);
        }


            /**
             * 当客户端连接服务端之后（打开连接）
             * 获取客户端的channle，并且放到ChannelGroup中去进行管理
             */
        @Override
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            clients.add(ctx.channel());
//            list.add(clients);

        }


            @Override
            public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
                // 当触发handlerRemoved，ChannelGroup会自动移除对应客户端的channel
                         clients.remove(ctx.channel());
                System.out.println("客户端断开，channle对应的长id为："
                        + ctx.channel().id().asLongText());
                System.out.println("客户端断开，channle对应的短id为："
                        + ctx.channel().id().asShortText());
            }


            @Override
            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                if (evt instanceof IdleStateEvent) {
                    IdleState state = ((IdleStateEvent) evt).state();
                    if (state == IdleState.READER_IDLE) {
                        // 在规定时间内没有收到客户端的上行数据, 主动发送信息
                        ctx.writeAndFlush("heart");
//                        ctx.disconnect();
                    }
                } else {
                    super.userEventTriggered(ctx, evt);
                }
            }

        }
    }