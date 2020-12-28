package com.siso.entity.web.userManage;

import com.siso.BaseEntity;
import com.siso.converter.UserConverter;
import com.siso.entity.android.noticet.Notice;
import com.siso.entity.web.permission.adminPermission;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
//@EqualsAndHashCode(callSuper = true)
@Entity(name = "admin_user")
@Table(name = "admin_user")
public class adminUser extends BaseEntity {
    private String number;
    private String password;
    private String authority;
    private Long parentId;
    private String market;
    private String name;
    @Convert(converter = UserConverter.class)
    @Column(columnDefinition="varchar(200)")
    private List<String>roleIds;

}
