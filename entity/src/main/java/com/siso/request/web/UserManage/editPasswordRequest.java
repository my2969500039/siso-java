package com.siso.request.web.UserManage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class editPasswordRequest {
    @ApiModelProperty("密码")
    @NotBlank(message="密码不能为空")
    private String password;

    @ApiModelProperty("重复密码")
    @NotBlank(message="重复密码不能为空")
    private String repeatPassword;
}
