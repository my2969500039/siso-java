package com.siso.web.userManage;

import com.siso.Result.Result;
import com.siso.dto.CMSPermissionDTO;
import com.siso.dto.CMSUserDTO;
import com.siso.entity.android.userManage.androidUser;
import com.siso.entity.web.userManage.adminUser;
import com.siso.request.android.userManage.registerUserRequest;
import com.siso.request.android.userManage.userLoginRequest;
import com.siso.request.web.UserManage.editPasswordRequest;
import com.siso.response.web.UserManage.LoginResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {


    // 用户登录
    Result<LoginResponse> userLogin(userLoginRequest request, HttpServletRequest httpServletRequest);




    adminUser userlogin1(String number);

    // 用户注册
    public Result<String> userRegister(registerUserRequest registerUserRequest);


    Result<String> editPassword(editPasswordRequest request);


    // 下级查询
    Result<List<CMSUserDTO>> userQuery();


    // 下级查询(超管权限)
    public List<adminUser> User_query_admin(String userNumber);


    //删除下级员工
    public Integer User_delete(String userNumber, String delete_number);


    //添加下级员工
    public  boolean User_add(String userNumber,String add_number,String add_password,int author);


    // 用户分配
    public boolean update_Superior_id(String userNumber,String delete_number);


}
