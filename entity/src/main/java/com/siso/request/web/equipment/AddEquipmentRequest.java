package com.siso.request.web.equipment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddEquipmentRequest {

    @ApiModelProperty("用户名")
    @NotNull(message = "用户名不能为空")
    @NotBlank(message = "用户名不能为空")
    private String userNumber;

    @ApiModelProperty("设备编号")
    @NotNull(message = "设备编号不能为空")
    @NotBlank(message = "设备编号不能为空")
    private String equipment_name;

    @ApiModelProperty("超市id")
    @NotNull(message = "超市id不能为空")
    @NotBlank(message = "超市id不能为空")
    private String market;

    @ApiModelProperty("型号")
    @NotNull(message = "型号不能为空")
    @NotBlank(message = "型号不能为空")
    private String xh;

    @ApiModelProperty("管理员")
    @NotNull(message = "管理账号不能为空")
    @NotBlank(message = "管理账号不能为空")
    private String staff;
}
