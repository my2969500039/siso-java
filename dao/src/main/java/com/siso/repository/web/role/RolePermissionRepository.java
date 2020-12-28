package com.siso.repository.web.role;

import com.siso.entity.web.role.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface RolePermissionRepository extends JpaRepository<RolePermission,Long>, QueryByExampleExecutor<RolePermission> {

    @Query(value = "select permission_id from role_permission where role_id in ?1",nativeQuery = true)
    List<Long> findPermissionIdByRoleIdIn(List<Long> roleId);

    Integer deleteAllByRoleId(Long roleId);
}
