package com.siso.request.web.equipment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateEquipmentRequest {
    @NotBlank(message = "设备id不能为空")
    @NotNull(message = "设备id不能为空")
    private Long Id;

    @NotBlank(message = "接收人不能为空")
    @NotNull(message = "接收人不能为空")
    private Long userId;
}
