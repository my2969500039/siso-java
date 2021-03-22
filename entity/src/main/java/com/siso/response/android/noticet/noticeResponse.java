package com.siso.response.android.noticet;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class noticeResponse {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("管理员")
    private String admin;


    @ApiModelProperty("图片")
    private String image;


    @ApiModelProperty("内容")
    private String content;


    @ApiModelProperty("添加时间")
    private Date createTime;


    @ApiModelProperty("title")
    private String title;

}
