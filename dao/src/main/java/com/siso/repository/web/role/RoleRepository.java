package com.siso.repository.web.role;

import com.siso.entity.web.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleRepository  extends JpaRepository<Role,Long>, JpaSpecificationExecutor<Role> {

    Role findOneById(Long id);
}
