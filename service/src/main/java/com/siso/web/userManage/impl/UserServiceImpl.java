package com.siso.web.userManage.impl;

import com.siso.Result.Result;
import com.siso.dto.CMSPermissionDTO;
import com.siso.dto.CMSUserDTO;
import com.siso.entity.web.permission.AdminPermission;
import com.siso.entity.web.role.Role;
import com.siso.entity.web.userManage.AdminUser;
import com.siso.exception.NormalException;
import com.siso.repository.android.userLogin.androidUserRepository;
//import com.siso.repository.web.role.RolePermissionRepository;
import com.siso.repository.web.role.RoleRepository;
import com.siso.repository.web.userManage.AdminPermissionRepository;
import com.siso.repository.web.userManage.UserRepository;
import com.siso.repository.web.userManage.AdminUserPermissionRepository;
import com.siso.request.android.userManage.registerUserRequest;
import com.siso.request.android.userManage.userLoginRequest;
import com.siso.request.web.UserManage.editPasswordRequest;
import com.siso.response.web.UserManage.LoginResponse;
import com.siso.response.web.UserManage.UserTokenResponse;
import com.siso.token.TokenUtils;
import com.siso.web.userManage.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private TokenUtils tokenUtils;
    @Resource
    private AdminPermissionRepository adminPermissionRepository;
    @Autowired
    private RoleRepository roleRepository;

    //用户登录
    @Override
    public Result<LoginResponse> userLogin(userLoginRequest request, HttpServletRequest httpServletRequest) {
        AdminUser aUsers = userRepository.findOneByNumber(request.getNumber());;
        if (aUsers==null){
            throw new NormalException("账号不存在");
        }
        if (aUsers.getPassword().equals(request.getPassword())){
            if (aUsers.getRoleIds()==null){
                throw new NormalException("未绑定角色");
            }
            List<Role>roleList=roleRepository.findAllByIdIn(aUsers.getRoleIds());
            if (roleList.isEmpty())
                throw new NormalException("权限不足");
            List<Long>permissionIds= new LinkedList<>();
            roleList.forEach(a->{
                permissionIds.addAll(a.getPermissions());
            });
            if (permissionIds.isEmpty())
                throw new NormalException("权限不足");
            List<AdminPermission>adminPermissionList=adminPermissionRepository.findAllByIdIn(permissionIds);
            List<CMSPermissionDTO> cmsPermissionDTOList=new ArrayList<>();
            adminPermissionList.forEach(a->{
                CMSPermissionDTO cmsPermissionDTO=new CMSPermissionDTO();
                BeanUtils.copyProperties(a,cmsPermissionDTO);
                cmsPermissionDTOList.add(cmsPermissionDTO);
            });
            UserTokenResponse userTokenResponse =new UserTokenResponse();//token
            BeanUtils.copyProperties(aUsers, userTokenResponse);
            LoginResponse loginResponse =new LoginResponse();//登录
            loginResponse.setUserTokenResponse(userTokenResponse);
            String token=tokenUtils.generateTokeCode(aUsers,cmsPermissionDTOList);
            loginResponse.setToken(token);
            loginResponse.setAdminPermission(adminPermissionList);
            return Result.<LoginResponse>builder().success().data(loginResponse).build();
        }
        throw new NormalException("账号或密码错误");
    }


    @Override
    public AdminUser userlogin1(String number){
        return userRepository.findOneByNumber(number);
    };

    //用户注册
    @Override
    @Transactional
    public Result<String> userRegister(registerUserRequest registerUserRequest) {
        if (!registerUserRequest.getRepeatPassword().equals(registerUserRequest.getPassword()))
            throw new NormalException("重复密码不一致");
        if (userRepository.findOneByNumber(registerUserRequest.getNumber())!=null)
            throw new NormalException("账号已存在");
        if (userRepository.findOneByNumber(registerUserRequest.getSuperiorId())==null)
            throw new NormalException("上级不存在");
        AdminUser adminUser=new AdminUser();
        BeanUtils.copyProperties(registerUserRequest,adminUser);
//        adminUser.setAvailable(true);
        userRepository.save(adminUser);
        return Result.<String>builder().success().message("注册成功").build();

    }


    @Override
    public Result<List<CMSUserDTO>> userQuery(){
        CMSUserDTO cmsUserDTO=tokenUtils.getLoginUserDTO();
        List<AdminUser>adminUserList=userRepository.findAllByParentId(cmsUserDTO.getId());
        List<CMSUserDTO> cmsUserDTOList=new ArrayList<>();
        adminUserList.forEach(a->{
            CMSUserDTO cmsUser=new CMSUserDTO();
            BeanUtils.copyProperties(a,cmsUser);
            cmsUserDTOList.add(cmsUser);
        });
        return Result.<List<CMSUserDTO>>builder().success().data(cmsUserDTOList).build();
    }

    //修改密码
    @Override
    public Result<String> editPassword(editPasswordRequest request){
        if (!request.getPassword().equals(request.getRepeatPassword()))
            throw new NormalException("重复密码不一致");
        CMSUserDTO cmsUserDTO=tokenUtils.getLoginUserDTO();
        AdminUser adminUser=userRepository.findOneById(cmsUserDTO.getId());
        adminUser.setPassword(request.getPassword());
        userRepository.save(adminUser);
        return Result.<String>builder().success().message("修改成功").build();
    };


    // 下级查询管理员账号
    @Override
    public List<AdminUser> User_query_admin(String userNumber){
        System.out.println("管理员"+userNumber+"查询用户数据");
        return userRepository.findAll();
    };



    //删除下级员工
    @Override
    public Integer User_delete(String userNumber, String delete_number){
        return userRepository.deleteByNumber(delete_number);
    };


    //添加下级员工
    @Override
    public  boolean User_add(String userNumber,String add_number,String add_password,int author){
        System.out.println("账号"+userNumber+"请求添加员工"+add_number);
//        return userRepository.User_add(userNumber,add_number,add_password,author);
        return false;
    };


    // 用户分配
    @Override
    public boolean update_Superior_id(String userNumber,String delete_number){
        System.out.println("账号"+userNumber+"请求划转员工"+delete_number);
//        return userRepository.update_Superior_id(userNumber,delete_number);
        return false;
    };








}
