package com.siso.request.web.region;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddRegionRequest {
    @ApiModelProperty("上级Id")
    private Long parentId;

    @ApiModelProperty("名称")
    private String name;
}
