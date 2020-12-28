package com.siso.request.android.userManage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class registerUserRequest {

    @ApiModelProperty("账号")
    @NotBlank(message="账号不能为空")
    private String number;

    @ApiModelProperty("密码")
    @NotBlank(message="密码不能为空")
    private String password;

    @ApiModelProperty("重复密码")
    @NotBlank(message="重复密码不能为空")
    private String repeatPassword;

    private String authority;

    @ApiModelProperty("上级Id")
    @NotBlank(message="非法操作")
    private String superiorId;
}
