package com.siso.repository.web.userManage;

import com.siso.entity.web.permission.AdminPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminPermissionRepository extends JpaRepository<AdminPermission,String> {
    List<AdminPermission>findAllByIdIn(List<Long> ids);
}
