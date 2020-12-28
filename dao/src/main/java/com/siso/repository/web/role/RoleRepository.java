package com.siso.repository.web.role;

import com.siso.entity.web.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role,Long> {

    Role findOneById(Long id);
}
