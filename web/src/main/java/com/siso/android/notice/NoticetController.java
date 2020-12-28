package com.siso.android.notice;



import com.siso.request.android.noticet.noticeRequest;
import com.siso.Result.Result;
import com.siso.response.android.noticet.noticeResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@Validated
@RequestMapping("/notice/")
@Api(value = "/notice/", tags = {"公告"})
public class NoticetController {

    @Autowired
    private noticeService noticeService;

    @ApiOperation(value = "获取公告接口", notes = "获取公告", code = 200, produces = "application/json")
    @RequestMapping(value = "/getNotice",method= RequestMethod.POST,produces="application/json;charset=UTF-8")//url
    public Result<List<noticeResponse>> getnotice(@Valid noticeRequest noticeRequest, BindingResult result){
        if (result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                return Result.<List<noticeResponse>>builder().fail().code(500).message(error.getDefaultMessage()).build();
            }
        }
        return noticeService.getnotice(noticeRequest);
    }


    @GetMapping("/article/{type}/{id}")
    @ApiOperation("获取文章接口")
    public Result<noticeResponse> article(@PathVariable("type")String type,@PathVariable("id")Long id){
        return noticeService.article(id,type);
    }


}
