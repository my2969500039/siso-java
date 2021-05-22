package com.siso.web.member;

import com.siso.Result.Result;
import com.siso.entity.android.userManage.AndroidUser;
import com.siso.request.web.member.SearchRequest;
import com.siso.response.android.login.userResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@Api(value = "/member",tags = {"会员管理"})
public class memberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/page")
    @ApiOperation("获取所有会员")
    public Result<Page<AndroidUser>> page(@RequestBody SearchRequest request){
        return memberService.page(request);
    }
}
