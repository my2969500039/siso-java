package com.siso.request.web.equipment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EquipmentRequest {

    @ApiModelProperty("设备id")
    @NotBlank(message = "设备id不能为空")
    @NotNull(message = "设备id不能为空")
    private String equipmentId;

}
