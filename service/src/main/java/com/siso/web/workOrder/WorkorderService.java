package com.siso.web.workOrder;

import com.siso.Result.Result;
import com.siso.entity.android.feedback.androidFeedback;
import com.siso.response.web.workOrder.adminFeedbackResponse;

import java.util.List;

public interface WorkorderService {

    //获取所有工单
    Result<List<adminFeedbackResponse>> getWorkOrder();

    Boolean post_feedback_android(String number,String type,String content,String img1,String img2,String img3);

    List<androidFeedback> get_feedback_android(String number);


}
