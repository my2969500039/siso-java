package com.siso.request.web.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchRequest {

    @ApiModelProperty("分页参数")
    private Integer pageNum;

    @ApiModelProperty("分页参数")
    private Integer pageSize;

    @ApiModelProperty("1--最新，2-所有")
    private String type;

    @ApiModelProperty("搜索关键词")
    private String keyWord;
}
