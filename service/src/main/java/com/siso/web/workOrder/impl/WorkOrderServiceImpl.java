package com.siso.web.workOrder.impl;

import com.siso.Result.Result;
import com.siso.entity.android.feedback.androidFeedback;
import com.siso.entity.web.feedback.adminFeedback;
import com.siso.repository.web.workOrder.workOrderRepository;
import com.siso.response.web.workOrder.adminFeedbackResponse;
import com.siso.web.workOrder.WorkorderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkOrderServiceImpl implements WorkorderService {

    @Autowired
    private workOrderRepository workOrderRepository;

    //管理员获取工单
    @Override
    public Result<List<adminFeedbackResponse>> getWorkOrder()
    {
        List<adminFeedback> feedbacks = workOrderRepository.findAll();
        List<adminFeedbackResponse>adminFeedbackResponseList=new ArrayList<>();
        feedbacks.forEach(f->{
            adminFeedbackResponse feedbackResponse=new adminFeedbackResponse();
            BeanUtils.copyProperties(f,feedbackResponse);
            adminFeedbackResponseList.add(feedbackResponse);
        });
        return Result.<List<adminFeedbackResponse>>builder().success().data(adminFeedbackResponseList).build();
    }


    //安卓用户提交反馈
    @Override
    public Boolean post_feedback_android(String numnber,String type,String content,String img1,String img2,String img3){
//        return workOrderRepository.post_android_feedback(content,img1,img2,img3,numnber,type);
        return false;
    };


    //安卓用户获取反馈
    @Override
    public List<androidFeedback> get_feedback_android(String numnber){
//        return wordorderDao.get_feedback_android(numnber);
        return null;
    };


}
