package com.siso.entity.android.userManage;

import com.siso.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "android_user")
@Table(name = "android_user")
public class androidUser extends BaseEntity implements Serializable {

    private String number;
    private String password;
    private String ip;
    private String sweep;
    private  String name;
    private String phone;
    private  String sex;
    private  Integer age;

 }
