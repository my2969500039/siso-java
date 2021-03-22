package com.siso.repository.web.permission;

import com.siso.entity.web.permission.AdminPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<AdminPermission,Long> {
}
