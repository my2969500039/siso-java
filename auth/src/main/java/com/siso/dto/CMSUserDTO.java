package com.siso.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 *
 * @author cnyuchu@gmail.com
 * @create 2019-09-05 09:44
 **/
@Data
@Resource
public class CMSUserDTO implements Serializable {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "账号")
    private String number;

    @ApiModelProperty(value = "账户昵称")
    private String name;

    private String market;

    @ApiModelProperty(value = "紧急联系人")
    private String urgencyContent;


    @ApiModelProperty(value = "权限列表")
    private List<CMSPermissionDTO> permissions;

    /**
     * 安卓用户
     */
    private String ip;
    private String sweep;
    private String phone;
    private  String sex;
    private  Integer age;

}
