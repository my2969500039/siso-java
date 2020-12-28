package com.siso.repository.web.permission;

import com.siso.entity.web.permission.adminPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<adminPermission,Long> {
}
