package com.siso.request.web.equipment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PageEquipmentRequest {

    @ApiModelProperty("超市id")
    private Long marketId;

    @ApiModelProperty("页")
    @NotBlank(message = "参数不能为空")
    private Integer pageNum;

    @ApiModelProperty("每页数量")
    @NotBlank(message = "参数不能为空")
    private Integer pageSize;

    @ApiModelProperty("关键词")
    private String keyWord;

}
