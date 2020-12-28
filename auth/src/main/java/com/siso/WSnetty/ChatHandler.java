package com.siso.WSnetty;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.*;


import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @Description: 处理消息的handler
 * TextWebSocketFrame： 在netty中，是用于为websocket专门处理文本的对象，frame是消息的载体
 */
//public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
@Service("ServerRunner")
public class ChatHandler{
    public static Map<String, ChannelHandlerContext> jsonObjectweb = new HashMap();
    public static Map<String, ChannelHandlerContext> jsonObjectsocket = new HashMap();
    public static Map<ChannelHandlerContext, String> jsonObjectsocket2 = new HashMap();
    public static Map<ChannelHandlerContext, List<String>> date = new HashMap();

    @Service
    public static class socket extends SimpleChannelInboundHandler<String> {

        private static final ChannelGroup clientsSocket =
                new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

        modubs modubs1=new modubs();
//        @Autowired
//        private information information1;
        information information1=new information();


        public void seed(String type,String name){
//            jsonObjectsocket.get(name).writeAndFlush(modubs1.result(type));
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg)
                throws Exception {
            if (!date.containsKey(ctx)){
                List<String> v=new ArrayList<>();
                v.add(msg);
                date.put(ctx,v);
            }
            else
               date.get(ctx).add(msg);
            //ChannelHandlerContext 返回事件的对象
            // 收到消息直接打印输出
//            if (msg.contains("3366")){
//                String str="";
//                for(String m:date.get(ctx)){
//                    str+=m;
//                }
//                if (str.contains("3266") && str.indexOf("3266")<str.indexOf("3366")){//查找帧头帧尾
//                    String result = str.substring( str.indexOf("3266"),  str.indexOf("3366")).substring("3266".length());//截取数据部分
//                    if (modubs1.encode(result).equals("false")){//判断验证
//                        ctx.write(modubs1.result("fail")+"\n");
//                    }
//                    else //验证成功
//                    {
//                        String res =information1.verification(modubs1.encode(result));
//                        if (res.equals("flase")){
//                            ctx.writeAndFlush(modubs1.result("fail")+"\n");
//                        }
//                        else {
//                            ctx.writeAndFlush(modubs1.result("success") + "\n");
//                            jsonObjectsocket.put(res,ctx);
//                            jsonObjectsocket2.put(ctx,res);
//                        }
//
//                    }
//
//                }
//
//                date.get(ctx).clear();
//            }

        }


        /**
         * 移除
         * @param ctx
         * @throws Exception
         */
        @Override
        public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
                date.remove(ctx);
            Collection<ChannelHandlerContext> col = jsonObjectsocket.values();
            col.remove(ctx);
            information1.state("0",jsonObjectsocket2.get(ctx));
            jsonObjectsocket2.remove(ctx);
//                System.out.println("客户端断开，channle对应的长id为："
//                        + ctx.channel().id().asLongText());
//                System.out.println("客户端断开，channle对应的短id为："
//                        + ctx.channel().id().asShortText());
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
            public   static ChannelGroup clients =
                    new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
            public static  List<ChannelGroup>list=new ArrayList<>();

            @Override
            protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg)
                    throws Exception {
                socket socket1 = new socket();
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
                list.add(clients);

            }


            @Override
            public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
                // 当触发handlerRemoved，ChannelGroup会自动移除对应客户端的channel
//                         clients.remove(ctx.channel());
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
                        // 在规定时间内没有收到客户端的上行数据, 主动断开连接
//                     socketChannelMap.remove((SocketChannel) ctx.channel());
                        ctx.disconnect();
                    }
                } else {
                    super.userEventTriggered(ctx, evt);
                }
            }

        }
    }