package com.siso.entity.web.feedback;

import com.siso.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "admin_feedback")
@Table(name = "admin_feedback")
public class adminFeedback extends BaseEntity {

    @Column(columnDefinition = "varchar(36) comment '工单编号' ")
    private String jobNumber;

    @Column(columnDefinition = "tinyint(1) comment '工单类型' ")
    private String type;

    @Column(columnDefinition = "text comment '工单内容' ")
    private String content;

    @Column(columnDefinition = "tinyint(1) comment '工单状态' ")
    private String state;

    @Column(columnDefinition = "text comment '处理内容' ")
    private String receive;

    @Column(columnDefinition = "varchar(36) comment '工单发送人' ")
    private String seedNumber;

    @Column(columnDefinition = "varchar(36) comment '处理人' ")
    private String receiveNumber;

    @Column(columnDefinition = "varchar(100) comment '图片1' ")
    private String sImage;

    @Column(columnDefinition = "varchar(100) comment '图片2' ")
    private String mImage;

    @Column(columnDefinition = "varchar(100) comment '图片3' ")
    private String bImage;

}
