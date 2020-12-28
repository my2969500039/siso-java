package com.siso.request.android.userManage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class userLoginRequest {

   @NotBlank(message = "账号不能为空")
   @ApiModelProperty("账号")
   private String number;

   @NotBlank(message = "密码不能为空")
   @ApiModelProperty("密码")
   private String password;

   private String name;

   @ApiModelProperty("IP地址")
   private String ip;

   private String phone;

    @ApiModelProperty("定位消息")
    private String headers;

    public String getPhone(){
        return this.phone=this.number;
    }


}
