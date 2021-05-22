package com.siso.entity.android.userManage;

import com.siso.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "android_user")
@Table(name = "android_user")
public class AndroidUser extends BaseEntity implements Serializable {

    @ApiModelProperty("账号")
    private String number;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("上次登录ip")
    private String ip;

    @ApiModelProperty("分页参数")
    private String sweep;

    @ApiModelProperty("姓名")
    private  String name;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("性别")
    private  String sex;

    @ApiModelProperty("年龄")
    private  Integer age;

 }
