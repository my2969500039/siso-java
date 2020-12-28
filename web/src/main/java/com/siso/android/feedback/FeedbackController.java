package com.siso.android.feedback;

import com.siso.Result.Result;
import com.siso.entity.android.feedback.androidFeedback;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(value = "/feedback",tags = {"用户反馈"})
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/feedback")
    @ApiOperation("用户反馈")
    public Result<String> feedback(@RequestParam("multipartFiles") MultipartFile[] multipartFiles, HttpServletRequest request,
                                   @RequestParam("conetnt") String content,
                                   @RequestParam("type") String type){
        return feedbackService.feedback(multipartFiles,request,content,type);
    }

    @GetMapping("/feedback")
    @ApiOperation("获取用户反馈")
    public Result<List<androidFeedback>> getFeedback(){
        return feedbackService.getFeedback();
    }

}
