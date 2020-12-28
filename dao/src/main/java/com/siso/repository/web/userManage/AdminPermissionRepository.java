package com.siso.repository.web.userManage;

import com.siso.entity.web.permission.adminPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminPermissionRepository extends JpaRepository<adminPermission,String> {
    List<adminPermission>findAllByIdIn(List<Long> ids);
}
