package com.siso.web.role;

import com.siso.Result.Result;
import com.siso.entity.web.permission.adminPermission;
import com.siso.entity.web.role.Role;
import com.siso.request.web.role.AddRoleRequest;
import com.siso.request.web.role.SetRolePermissionRequest;
import com.siso.response.web.permission.PermissionResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface RoleService {

    Result<List<Role>> search();

    Result<String>add(AddRoleRequest addRoleRequest);

    Result<String>delete(Long id);

    Result<List<PermissionResponse>>searchPermission();

    Result<String>setPermission(SetRolePermissionRequest request);
}
