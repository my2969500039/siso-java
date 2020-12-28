package com.siso.entity.web.permission;

import com.siso.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "admin_user_permission")
@Table(name = "admin_user_permission")
public class adminUserPermission extends BaseEntity {

    private Long userId;

    private Long permissionId;
}
