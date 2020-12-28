package com.siso.entity.web.role;

import com.siso.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "user_role")
@Table(appliesTo = "user_role",comment = "用戶角色表")
public class UserRole extends BaseEntity {

    private Long userId;

    private Long roleId;
}
