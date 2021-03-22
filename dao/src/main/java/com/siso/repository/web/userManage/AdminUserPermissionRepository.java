package com.siso.repository.web.userManage;

import com.siso.entity.web.permission.AdminUserPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminUserPermissionRepository extends JpaRepository<AdminUserPermission,String> {
    List<AdminUserPermission>findAllByUserId(Long userId);
}
