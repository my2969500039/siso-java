package com.siso.web.announcement;

import com.siso.Result.Result;
import com.siso.request.web.announcement.AnnouncementRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "/announcement",tags = {"公告推送"})
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @PostMapping("/push")
    @ApiOperation("推送公告")
    public Result<String> Announcement(@Valid @RequestBody AnnouncementRequest request, BindingResult result){
        if (result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                return Result.<String>builder().fail().code(500).message(error.getDefaultMessage()).build();
            }
        }
        return announcementService.Announcement(request);
    }

}
