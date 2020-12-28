package com.siso.request.web.announcement;

import lombok.Data;

@Data
public class AnnouncementRequest {
    private Long id;
    private String title;
    private String content;
    private String image;
}
