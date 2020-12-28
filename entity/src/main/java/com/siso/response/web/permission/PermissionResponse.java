package com.siso.response.web.permission;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PermissionResponse {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("上级栏目")
    private Long parentId;

    @ApiModelProperty("优先级")
    private String priority;

    @ApiModelProperty("图片地址")
    private String src;

    @ApiModelProperty("下级权限")
    private List<PermissionResponse> children=new ArrayList<>();
}
