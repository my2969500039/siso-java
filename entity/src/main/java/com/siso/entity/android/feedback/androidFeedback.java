package com.siso.entity.android.feedback;


import com.siso.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "android_feedback")
@Table(name = "android_feedback")
public class androidFeedback extends BaseEntity {
    @ApiModelProperty("反馈内容")
    private String content;
    @ApiModelProperty("图片1")
    private String img1;
    @ApiModelProperty("图片2")
    private String img2;
    @ApiModelProperty("图片3")
    private String img3;
    @ApiModelProperty("用户")
    private String number;
    @ApiModelProperty("类型")
    private String type;
    @ApiModelProperty("状态1--以回复，0--未回复")
    private String state;
    @ApiModelProperty("回复内容")
    private String returnContent;
    @ApiModelProperty("回复账号")
    private String returnNumber;
    @ApiModelProperty("回复时间")
    private String returnTime;

}