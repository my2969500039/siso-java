package com.siso.request.web.UserManage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class userLoginRequest  {

    @ApiModelProperty("账号")
    @NotBlank(message = "账号不能为空")
    @NotNull(message = "账号不能为空")
    private String number;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    @NotNull(message = "密码不能为空")
    @Min(message = "密码不能小于八位", value = 8)
    private String password;
}
