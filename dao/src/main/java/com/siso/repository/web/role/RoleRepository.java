package com.siso.repository.web.role;

import com.siso.entity.web.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RoleRepository  extends JpaRepository<Role,Long>, JpaSpecificationExecutor<Role> {

    Role findOneById(Long id);

    List<Role> findAllByIdIn(List<Long> ids);
}
