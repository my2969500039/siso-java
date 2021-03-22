package com.siso.web.userManage;

import com.siso.dto.CMSUserDTO;
import com.siso.entity.web.userManage.AdminUser;
import com.siso.request.android.userManage.registerUserRequest;
import com.siso.request.android.userManage.userLoginRequest;
import com.siso.request.web.UserManage.editPasswordRequest;
import com.siso.response.Re;
import com.siso.Result.Result;
import com.siso.response.web.UserManage.LoginResponse;
import com.siso.web.equipment.EquipmentService;
import com.siso.web.street.AdminStreetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(value = "/user",tags = {"用户模块"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private AdminStreetService adminstreetService;//超市相关Service




    Re re=new Re();

    /** * 用户登录
     * @return */
    @ApiOperation(value = "用户登录", notes = "登录", code = 200, produces = "application/json")
    @PostMapping(value = "/userLogin",produces="application/json;charset=UTF-8")//url注解，定义请求方式，字符串编码
    public Result<LoginResponse> UserLogin(@Valid @RequestBody userLoginRequest request, BindingResult result, HttpServletRequest httpServletRequest) {//注解方式接收json数据
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return Result.<LoginResponse>builder().fail().code(500).message(error.getDefaultMessage()).build();
            }
        }
        return userService.userLogin(request,httpServletRequest);
    }




    /** * 用户注册
     * @return */
    @PostMapping(value = "/userRegister",produces="application/json;charset=UTF-8")//url注解，定义请求方式，字符串编码
    @ApiOperation(value = "用户注册")
    public Result<String> userRegister(@Valid @RequestBody registerUserRequest registerUserRequest,BindingResult result){
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return Result.<String>builder().fail().code(500).message(error.getDefaultMessage()).build();
            }
        }
        return userService.userRegister(registerUserRequest);
    }

    /** *修改用户密码
     * @return */
    @PutMapping(value = "/editPassword",produces="application/json;charset=UTF-8")//url注解，定义请求方式，字符串编码
    public Result<String> editPassword(@Valid @RequestBody editPasswordRequest request,BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return Result.<String>builder().fail().code(500).message(error.getDefaultMessage()).build();
            }
        }
        return userService.editPassword(request);
    }


    /** *查询下级用户
     * @return */
    @GetMapping("/User_query")//url注解，定义请求方式，字符串编码
    @ApiOperation("获取下级用户")
    public Result<List<CMSUserDTO>> userQuery() {

        return   userService.userQuery();

    }

    /** *删除下级用户
     * @return */
    @RequestMapping(value = "/User_delete",method= RequestMethod.POST,produces="application/json;charset=UTF-8")//url注解，定义请求方式，字符串编码
    public @ResponseBody String User_delete(@RequestParam("userNumber") String userNumber,@RequestParam("delete_Number") String delete_Number) {
        if (userNumber.equals(delete_Number)){
            return re.pre(0,"False","不能删除自己");
        }

            Integer result = userService.User_delete(userNumber,delete_Number);
        if (result==1){

               Boolean result1=userService.update_Superior_id(userNumber,delete_Number);//将下级用户划转给管理员


//               Boolean result2=equipmentService.updateEquipment(null);//将下级设备划转给管理员

              if (result1 ){
                  return re.pre(1,"True","删除成功");
              }
              else {
                  return re.pre(1,"True","删除成功,资源丢失请提交工单！");
              }

        }

        else {
            return  re.pre(0,"Flase","删除失败");
        }

    }



    /** *添加员工
     * @return */
    @RequestMapping(value = "/User_add",method= RequestMethod.POST,produces="application/json;charset=UTF-8")//url注解，定义请求方式，字符串编码
    public @ResponseBody String add_user(@RequestParam("userNumber") String userNumber,
                                         @RequestParam("add_Number") String add_Number,@RequestParam("add_password") String add_password){

        AdminUser user=userService.userlogin1(userNumber);
        if (user.getAuthority().equals("4")){
            return re.pre(0,"False","权限不足!");
        }

        else {
            AdminUser user_add=userService.userlogin1(add_Number);
            if (user_add==null){
                Boolean result=userService.User_add(userNumber,add_Number,add_password,Integer.decode(user.getAuthority())+1);
                if (result){
                    return  re.pre(1,"True","员工添加成功!");
                }
                else {
                    return  re.pre(0,"False","员工添加失败!");
                }
            }

            else    {return  re.pre(0,"False","员工已存在!");}


        }

    }






}
