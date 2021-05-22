package com.siso.web.role;

import com.siso.Result.Result;
import com.siso.entity.web.role.Role;
import com.siso.request.web.member.SearchRequest;
import com.siso.request.web.role.AddRoleRequest;
import com.siso.request.web.role.SetRolePermissionRequest;
import com.siso.response.web.permission.PermissionResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleService {

    Result<Page<Role>> page(SearchRequest searchRequest);

    Result<String>add(AddRoleRequest addRoleRequest);

    Result<String>delete(Long id);

    Result<List<PermissionResponse>>searchPermission();

    Result<String>setPermission(SetRolePermissionRequest request);
}
