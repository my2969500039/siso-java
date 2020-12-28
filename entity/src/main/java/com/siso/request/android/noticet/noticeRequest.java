package com.siso.request.android.noticet;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class noticeRequest {
    @ApiModelProperty("账号")
    @NotNull(message = "number不能为空")
    @NotBlank(message = "number不能为空")
    private String number;

    private String type;
}
