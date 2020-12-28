package com.siso.repository.web.userManage;

import com.siso.entity.web.permission.adminUserPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminUserPermissionRepository extends JpaRepository<adminUserPermission,String> {
    List<adminUserPermission>findAllByUserId(Long userId);
}
