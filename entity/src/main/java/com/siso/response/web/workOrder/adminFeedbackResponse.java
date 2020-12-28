package com.siso.response.web.workOrder;

import com.siso.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;

@EqualsAndHashCode(callSuper = true)
@Data
public class adminFeedbackResponse extends BaseEntity {

    @ApiModelProperty("工单编号")
    private String jobNumber;

    @ApiModelProperty("工单类型")
    private String type;

    @ApiModelProperty("工单内容")
    private String content;

    @ApiModelProperty("工单状态")
    private String state;

    @ApiModelProperty("处理内容")
    private String receive;

    @ApiModelProperty("工单发送人")
    private String seedNumber;

    @ApiModelProperty("处理人")
    private String receiveNumber;

    @ApiModelProperty("图片1")
    private String sImage;

    @ApiModelProperty("图片2")
    private String mImage;

    @ApiModelProperty("图片3")
    private String bImage;
}
