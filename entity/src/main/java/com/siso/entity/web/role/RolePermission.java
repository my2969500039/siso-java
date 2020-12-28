package com.siso.entity.web.role;

import com.siso.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "role_permission")
@Table(appliesTo = "role_permission")
public class RolePermission extends BaseEntity {
    @Column(nullable = false)
    private Long roleId;

    @Column(nullable = false)
    private Long permissionId;
}
