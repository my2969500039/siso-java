package com.siso.web.equipment;

import com.siso.entity.web.equipMent.adminEquipment;
import com.siso.request.web.equipment.PageEquipmentRequest;
import com.siso.request.web.equipment.UpdateEquipmentRequest;
import com.siso.Result.Result;
import com.siso.web.equipment.impl.EquipmentServiceImpl;
import com.siso.web.userManage.UserService;
import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller("设备接口")
@RequestMapping("/equipment")
@RestController
@Api(value = "/equipment", tags = {"设备管理"})
public class equipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private UserService userService;

    /** * 获取名下设备
     * @return */
    @ApiOperation(value = "获取名下所有设备")
    @PostMapping(value = "/Equipment")//url注解，定义请求方式，字符串编码
    @RequiresPermissions("equipment:eqseek")
    public Result<Page<adminEquipment>>findEquipment(@Valid @RequestBody PageEquipmentRequest request, BindingResult result){
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return Result.<Page<adminEquipment>>builder().fail().code(500).message(error.getDefaultMessage()).build();
            }
        }
            return equipmentService.findEquipment(request);
    }


    /** * 划转名下设备
     * @return */
    @ApiOperation(value = "划转名下设备")
    @PostMapping(value = "/updateEquipment")//url注解，定义请求方式，字符串编码
    public  @ResponseBody Result<String> updateEquipment(@Valid @RequestBody UpdateEquipmentRequest request,BindingResult result){
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return Result.<String>builder().fail().code(500).message(error.getDefaultMessage()).build();
            }
        }
        return equipmentService.updateEquipment(request);
    }



//
//    /** * 添加设备
//     * @return */// TODO: 2020/10/25 权限管理，需要管理员权限
//    @ApiOperation(value = "添加设备")
//    @RequestMapping(value = "/add_equiment",method= RequestMethod.POST,produces="application/json;charset=UTF-8")//url注解，定义请求方式，字符串编码
//    public  Result<List<EquipmentResponse>> add_equiment(@Valid AddEquipmentRequest request, BindingResult result) throws NotActiveException {
//        if (result.hasErrors()){
//            for (ObjectError error : result.getAllErrors()) {
//                return Result.<List<EquipmentResponse>>builder().fail().code(500).message(error.getDefaultMessage()).build();
//            }
//        }
//        return equipmentService.add_equi(request);
//
//
//    }

    @ApiOperation(value = "获取设备详情")
    @PostMapping(value = "/one/{id}")//url
    public Result<adminEquipment>getOne(@ApiParam("设备Id")@PathVariable("id")Long id) {
        return equipmentService.getOne(id);
    }




}
