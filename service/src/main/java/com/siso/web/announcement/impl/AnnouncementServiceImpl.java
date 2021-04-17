package com.siso.web.announcement.impl;

import com.siso.Result.Result;
import com.siso.WSnetty.ChatHandler;
import com.siso.request.web.announcement.AnnouncementRequest;
import com.siso.web.announcement.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Component
@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    public static  AnnouncementServiceImpl announcementService;

    @Autowired
    private ChatHandler.websocket chatHandler;


    @PostConstruct
    public void init(){
        announcementService=this;
        announcementService.chatHandler=this.chatHandler;
    }

    @Override
    public Result<String> Announcement(AnnouncementRequest request) {
        this.chatHandler.seed(request.getTitle(),request.getContent(),request.getImage(),request.getId());
        return Result.<String>builder().success().data("推送成功").build();
    }

}
