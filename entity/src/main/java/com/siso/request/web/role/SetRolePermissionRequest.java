package com.siso.request.web.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class SetRolePermissionRequest {

    @ApiModelProperty("角色id")
    @NotBlank(message = "角色id不能为空")
    private Long roleId;

    @ApiModelProperty("权限Id列表")
    @NotBlank(message = "权限不能为空")
    private List<Long>permissionIds;
}
