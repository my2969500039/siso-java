package com.siso.web.rolePermission;

import com.siso.Result.Result;
import com.siso.entity.web.role.Role;
import com.siso.request.web.role.AddRoleRequest;
import com.siso.request.web.role.SetRolePermissionRequest;
import com.siso.response.web.permission.PermissionResponse;
import com.siso.web.role.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/role")
@Api(value = "/role",tags = {"角色管理"})
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("获取所有角色")
    @GetMapping("/role")
    @RequiresPermissions("role:search")
    public Result<List<Role>>search(){
        return roleService.search();
    }

    @ApiOperation("添加角色")
    @PostMapping("/role")
    @RequiresPermissions("role:add")
    public Result<String>add(@Valid @RequestBody AddRoleRequest addRoleRequest, BindingResult result){
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return Result.<String>builder().fail().code(500).message(error.getDefaultMessage()).build();
            }
        }
        return  roleService.add(addRoleRequest);
    }


    @ApiOperation("删除指定角色")
    @DeleteMapping("/role/{id}")
    @RequiresPermissions("role:delete")
    public Result<String>delete(@PathVariable("id")Long id){

        return  roleService.delete(id);
    }




    @ApiOperation("获取所有权限")
    @GetMapping("/permission")
    @RequiresPermissions("permission:search")
    public Result<List<PermissionResponse>>searchPermission(){
        return  roleService.searchPermission();
    }



    @ApiOperation("添加权限")
    @PostMapping("/permission")
    @RequiresPermissions("role:permission")
    public Result<String>setPermission(@Valid @RequestBody SetRolePermissionRequest request,BindingResult result){
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return Result.<String>builder().fail().code(500).message(error.getDefaultMessage()).build();
            }
        }
        return  roleService.setPermission(request);
    }



}
