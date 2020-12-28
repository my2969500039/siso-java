package com.siso.entity.web.role;

import com.siso.BaseEntity;
import com.siso.converter.UserConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "admin_role")
@Table(appliesTo = "admin_role",comment = "CMS权限表")
public class Role extends BaseEntity {

    private String name;
    @Column(columnDefinition = "text")
    private String content;
    @Convert(converter = UserConverter.class)
    @Column(columnDefinition="varchar(200)")
    private List<String> permissions;

}
