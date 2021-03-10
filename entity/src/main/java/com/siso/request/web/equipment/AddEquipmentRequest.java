package com.siso.request.web.equipment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddEquipmentRequest {

    @ApiModelProperty("设备编号")
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
    @NotBlank(message = "管理账号不能为空")
    private String userId;

    @ApiModelProperty(value = "设备名称")
    @NotBlank(message = "设备名称")
    private String name;

    @ApiModelProperty(value = "街道")
    @NotBlank(message = "街道")
    private String street;

    @ApiModelProperty(value = "状态")
    @NotBlank(message = "状态")
    private String state;

    @ApiModelProperty(value = "超市Id")
    @NotBlank(message = "超市Id")
    private Long marketId;

    @ApiModelProperty(value = "设备编号")
    @NotBlank(message = "设备编号")
    private String number;

    @ApiModelProperty(value = "设备简介")
    private String detail;
}
