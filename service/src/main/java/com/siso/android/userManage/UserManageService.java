package com.siso.android.userManage;

import com.siso.Result.Result;
import com.siso.entity.android.userManage.androidUser;
import com.siso.request.android.userManage.userLoginRequest;
import com.siso.response.web.UserManage.LoginResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


public interface UserManageService {

    Result<LoginResponse> androidUserLogin(userLoginRequest request);

    Result<String> setPassword(String password);

    Result<String> register(userLoginRequest request);

}
