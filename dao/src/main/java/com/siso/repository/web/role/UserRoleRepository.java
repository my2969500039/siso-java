package com.siso.repository.web.role;

import com.siso.entity.web.role.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

    @Query(value = "select role_id from user_role where user_id = ?1", nativeQuery = true)
    List<Long> findRoleIdByUserId(Long userId);
}
