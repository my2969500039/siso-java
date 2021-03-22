package com.siso.entity.web.permission;

import com.siso.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "admin_permission")
@Table(name = "admin_permission")
public class AdminPermission extends BaseEntity {

    private String remark;

    private String name;

    private String title;

    private String path;

    private Long parentId;

    private String priority;

    private String src;



}
