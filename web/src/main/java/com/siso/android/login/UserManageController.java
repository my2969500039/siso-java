package com.siso.android.login;


import com.siso.Result.Result;
import com.siso.android.userManage.UserManageService;
import com.siso.request.android.userManage.userLoginRequest;
import com.siso.response.web.UserManage.LoginResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/android/")
@Api(value = "/android/", tags = {"安卓用户接口"})
public class UserManageController {

    @Resource
    private UserManageService androiduserManageService;

    @PostMapping(value = "/androidLogin")//url注解，定义请求方式，字符串编码
    @ApiOperation("安卓用户登录")
    public Result<LoginResponse> android_userLogin(@Valid @RequestBody userLoginRequest request, BindingResult result){
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return Result.<LoginResponse>builder().fail().code(500).message(error.getDefaultMessage()).build();
            }
        }
        return androiduserManageService.androidUserLogin(request);
    }

    @PutMapping(value = "/android")//url注解，定义请求方式，字符串编码
    @ApiOperation("安卓用户修改密码")
    public Result<String> setPassword(@RequestParam("password")String password){
        return androiduserManageService.setPassword(password);
    }


    @PostMapping(value = "/androidRegister")//url注解，定义请求方式，字符串编码
    public Result<String> register(@Valid @RequestBody userLoginRequest request, BindingResult result){
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return Result.<String>builder().fail().code(500).message(error.getDefaultMessage()).build();
            }
        }
        return androiduserManageService.register(request);
    }




}
