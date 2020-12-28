package com.siso.web.announcement;

import com.siso.Result.Result;
import com.siso.request.web.announcement.AnnouncementRequest;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AnnouncementService {

    Result<String> Announcement(AnnouncementRequest request);
}
