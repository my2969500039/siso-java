package com.siso.web.member;

import com.siso.Result.Result;
import com.siso.entity.android.userManage.androidUser;
import com.siso.response.android.login.userResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.loader.custom.ResultRowProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
@Api(value = "/member",tags = {"会员管理"})
public class memberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/members")
    @ApiOperation("获取所有会员")
    public Result<List<userResponse>> all(){
        return memberService.all();
    }
}
