package com.siso.android.userManage;

import com.siso.Result.Result;
import com.siso.request.android.userManage.userLoginRequest;
import com.siso.response.web.UserManage.LoginResponse;


public interface UserManageService {

    Result<LoginResponse> androidUserLogin(userLoginRequest request);

    Result<String> setPassword(String password);

    Result<String> register(userLoginRequest request);

}
