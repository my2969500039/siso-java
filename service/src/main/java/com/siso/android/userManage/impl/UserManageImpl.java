package com.siso.android.userManage.impl;

import com.siso.Result.Result;
import com.siso.dto.CMSUserDTO;
import com.siso.exception.NormalException;
import com.siso.android.userManage.UserManageService;
import com.siso.entity.android.userManage.androidUser;
import com.siso.repository.android.userLogin.androidUserRepository;
import com.siso.repository.android.userLogin.userLoginRepository;
import com.siso.request.android.userManage.userLoginRequest;
import com.siso.response.web.UserManage.LoginResponse;
import com.siso.response.web.UserManage.UserTokenResponse;
import com.siso.token.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserManageImpl implements UserManageService {

    @Resource
    private androidUserRepository androidUserRepository;
    @Resource
    private userLoginRepository userLoginRepository;
    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public Result<LoginResponse> androidUserLogin(userLoginRequest request){
        androidUser androidUsers = androidUserRepository.findOneByNumber(request.getNumber());
        if (androidUsers==null){
            throw new NormalException("账号不存在");
        }
        if (androidUsers.getPassword().equals(request.getPassword())){
            BeanUtils.copyProperties(request,androidUsers);
            userLoginRepository.save(androidUsers);
            LoginResponse loginResponse=new LoginResponse();
            UserTokenResponse userTokenResponse=new UserTokenResponse();
            BeanUtils.copyProperties(androidUsers,userTokenResponse);
            loginResponse.setToken(tokenUtils.generateTokeCodeAndroid(androidUsers));
            loginResponse.setUserTokenResponse(userTokenResponse);
            return Result.<LoginResponse>builder().success().data(loginResponse).build();
        }
        else {
            throw new NormalException("账号或密码错误");
        }

    }

    @Override
    public Result<String> setPassword(String password) {
        CMSUserDTO androidUserDTO=tokenUtils.getLoginUserDTO();
        androidUser androidUser=androidUserRepository.findOneById(androidUserDTO.getId());
        androidUser.setPassword(password);
        return Result.<String>builder().success().message("修改成功").build();
    }

    @Override
    public Result<String> register(userLoginRequest request){
        Long size=androidUserRepository.countAllByNumber(request.getNumber());
        if (size!=0)
            throw new NormalException("账号已存在");
        androidUser androidUsers = new androidUser();
        BeanUtils.copyProperties(request,androidUsers);
        androidUserRepository.save(androidUsers);
        return Result.<String>builder().success().message("注册成功").build();
    };
}
