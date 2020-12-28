package com.siso.web.role.impl;

import com.siso.Result.Result;
import com.siso.entity.web.permission.adminPermission;
import com.siso.entity.web.role.Role;
import com.siso.entity.web.role.RolePermission;
import com.siso.exception.NormalException;
import com.siso.repository.web.permission.PermissionRepository;
import com.siso.repository.web.role.RolePermissionRepository;
import com.siso.repository.web.role.RoleRepository;
import com.siso.request.web.role.AddRoleRequest;
import com.siso.request.web.role.SetRolePermissionRequest;
import com.siso.response.web.permission.PermissionResponse;
import com.siso.response.web.region.RegionResponse;
import com.siso.web.role.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    public Result<List<Role>> search() {
        List<Role>roleList=roleRepository.findAll();
        return Result.<List<Role>>builder().success().data(roleList).build();
    }

    @Override
    public Result<String> add(AddRoleRequest addRoleRequest) {
        Role role=new Role();
        BeanUtils.copyProperties(addRoleRequest,role);
        roleRepository.save(role);
        return Result.<String>builder().success().message("添加成功").build();
    }

    @Override
    public Result<String> delete(Long id) {
        Role role=roleRepository.findOneById(id);
        if (role==null)
            throw new NormalException("角色不存在");
        roleRepository.delete(role);
        return Result.<String>builder().success().message("删除成功").build();
    }

    @Override
    public Result<List<PermissionResponse>> searchPermission() {
        List<adminPermission>adminPermissionList=permissionRepository.findAll();
        List<PermissionResponse>permissionResponseList=new ArrayList<>();
        adminPermissionList.forEach(a->{
            PermissionResponse permissionResponse=new PermissionResponse();
            BeanUtils.copyProperties(a,permissionResponse);
            permissionResponseList.add(permissionResponse);
        });
        return Result.<List<PermissionResponse>>builder().success().data(makeTree(permissionResponseList)).build();
    }

    @Override
    @Transactional
    public Result<String> setPermission(SetRolePermissionRequest request) {
        Role role=roleRepository.findOneById(request.getRoleId());
        if (role==null)
            throw new NormalException("角色不存在");
        rolePermissionRepository.deleteAllByRoleId(request.getRoleId());
        List<RolePermission>rolePermissionList=new ArrayList<>();
        request.getPermissionIds().forEach(permissionId->{
            RolePermission permission=new RolePermission();
            permission.setPermissionId(permissionId);
            permission.setRoleId(request.getRoleId());
            rolePermissionList.add(permission);
        });
        rolePermissionRepository.saveAll(rolePermissionList);
        return Result.<String>builder().success().message("添加成功").build();
    }

    public List<PermissionResponse> makeTree(List<PermissionResponse> responses) {
        List<PermissionResponse> list = new LinkedList<>();
        Map<Long, PermissionResponse> labelMap =responses.stream().collect(Collectors.toMap(PermissionResponse::getId, v->v,(v1, v2)->v1));
        for (PermissionResponse e : responses) {
            if (e.getParentId() == null ||  e.getParentId()==0  ) {
                list.add(e);
            }
            if (e.getParentId() != null && labelMap.containsKey(e.getParentId())) {
                labelMap.get(e.getParentId()).getChildren().add(e);
            }
        }
        return list;
    }


}
