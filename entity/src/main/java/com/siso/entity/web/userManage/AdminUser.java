package com.siso.entity.web.userManage;

import com.siso.BaseEntity;
import com.siso.converter.UserConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "admin_user")
@Table(name = "admin_user")
public class AdminUser extends BaseEntity {
    private String number;
    private String password;
    private String authority;
    private Long parentId;
    private String market;
    private String name;
    @Convert(converter = UserConverter.class)
    @Column(columnDefinition="varchar(200)")
    private List<Long>roleIds=new LinkedList<>();

}
