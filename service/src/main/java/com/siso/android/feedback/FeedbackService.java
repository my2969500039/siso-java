package com.siso.android.feedback;

import com.siso.Result.Result;
import com.siso.entity.android.feedback.androidFeedback;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FeedbackService {
    Result<String> feedback(MultipartFile[] multipartFiles, HttpServletRequest request,String content,
                            String type);

    Result<List<androidFeedback>> getFeedback();

}
