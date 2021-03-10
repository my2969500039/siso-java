//package com.siso;
//
//import com.siso.WSnetty.netty;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//@Component
//public class NettyListener implements ApplicationListener<ContextRefreshedEvent> {//使用外部容器时建立线程启动netty
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        netty nettyServer = new netty();
//        nettyServer.start();
//    }
//}
//
