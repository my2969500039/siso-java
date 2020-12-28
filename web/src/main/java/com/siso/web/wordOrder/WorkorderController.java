package com.siso.web.wordOrder;


import com.alibaba.fastjson.JSONObject;
import com.siso.entity.android.feedback.androidFeedback;
import com.siso.response.Re;
import com.siso.Result.Result;
import com.siso.response.web.workOrder.adminFeedbackResponse;
import com.siso.web.workOrder.WorkorderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/workOrder")
@Api(value = "/workOrder",tags = {"工单处理"})
public class WorkorderController {

    @Autowired
    private WorkorderService workorderService;


    Re re = new Re();


    @ApiOperation(value = "提交工单")
    @PostMapping(value = "/workOrder",produces = "application/json;charset=UTF-8")
    public Result<String> addWordOrder(){
        return null;
    }



    @GetMapping(value = "/getWork", produces = "application/json;charset=UTF-8")
    @RequiresPermissions("workOrder:search")
    @ApiOperation(value = "获取工单")
    public Result<List<adminFeedbackResponse>> getWorkOrder() {
        return workorderService.getWorkOrder();
    }




    //安卓获取反馈
    @RequestMapping(value = "/get_android_feedback", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String get_android_feedback(@RequestParam("number") String number) {
        List<androidFeedback>android_feedbacks=workorderService.get_feedback_android(number);
        if (android_feedbacks.size()==0){
            return re.pre(0,"False","暂无记录");
        }
        List<JSONObject>list=new ArrayList<>();
        for (int i=0;i<android_feedbacks.size();i++){
            List<String>list_img=new ArrayList<>();
            JSONObject jsonObject_androidfeedback=new JSONObject();
//            jsonObject_androidfeedback.put("time",android_feedbacks.listIterator(i).next().getTime());
            list_img.add(android_feedbacks.listIterator(i).next().getImg1());
            list_img.add(android_feedbacks.listIterator(i).next().getImg2());
            list_img.add(android_feedbacks.listIterator(i).next().getImg3());
            jsonObject_androidfeedback.put("image",list_img);
            jsonObject_androidfeedback.put("content",android_feedbacks.listIterator(i).next().getContent());
            jsonObject_androidfeedback.put("number",android_feedbacks.listIterator(i).next().getNumber());
            jsonObject_androidfeedback.put("type",android_feedbacks.listIterator(i).next().getType());
            jsonObject_androidfeedback.put("state",android_feedbacks.listIterator(i).next().getState());
//            jsonObject_androidfeedback.put("r_content",android_feedbacks.listIterator(i).next().getR_content());
//            jsonObject_androidfeedback.put("r_number",android_feedbacks.listIterator(i).next().getR_number());
//            jsonObject_androidfeedback.put("r_time",android_feedbacks.listIterator(i).next().getR_time());
            list.add(jsonObject_androidfeedback);
        }
        return re.pre_json(1,"True",list);


    }



}
