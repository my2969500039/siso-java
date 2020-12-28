package com.siso.android.feedback.impl;

import com.siso.Result.Result;
import com.siso.android.feedback.FeedbackService;
import com.siso.entity.android.feedback.androidFeedback;
import com.siso.exception.NormalException;
import com.siso.repository.android.feedback.FeedbackRepository;
import com.siso.token.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public Result<String> feedback(MultipartFile[] multipartFiles, HttpServletRequest request,String content, String type) {
        String[] list_img = new String[3];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(new Date());
        for (int i = 0; i < multipartFiles.length; i++) {
            String fileName = Long.toString(System.currentTimeMillis()) + ".png";
            list_img[i] = fileName;
            File file = new File("./resource/image/"+dateNowStr);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            File dest = new File("./resource/image/"+dateNowStr+"/" + fileName);
            try {
                multipartFiles[i].transferTo(dest.getAbsoluteFile());
            } catch (Exception e) {
                e.printStackTrace();
                throw new NormalException("未知错误");
            }
        }
        androidFeedback androidFeedback=new androidFeedback();
        androidFeedback.setImg1("http://"+request.getServerName()+":9002/upload/"+dateNowStr+"/"+list_img[0]);
        androidFeedback.setImg2("http://"+request.getServerName()+":9002/upload/"+dateNowStr+"/"+list_img[1]);
        androidFeedback.setImg3("http://"+request.getServerName()+":9002/upload/"+dateNowStr+"/"+list_img[2]);
        androidFeedback.setContent(content);
        androidFeedback.setNumber(tokenUtils.getLoginUserDTO().getNumber());
        androidFeedback.setType(type);
        feedbackRepository.save(androidFeedback);
        return Result.<String>builder().success().data("反馈成功").build();

    }

    @Override
    public Result<List<androidFeedback>> getFeedback() {
       List<androidFeedback>androidFeedbackList=feedbackRepository.findAllByNumber(tokenUtils.getLoginUserDTO().getNumber());
       return Result.<List<androidFeedback>>builder().success().data(androidFeedbackList).build();
    }
}
