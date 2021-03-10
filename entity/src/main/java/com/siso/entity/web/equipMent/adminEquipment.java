package com.siso.entity.web.equipMent;

import com.siso.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "admin_equipment")
@Table(name = "admin_equipment")
public class adminEquipment extends BaseEntity {
    @ApiModelProperty(value = "设备名称")
    private String name;

    @ApiModelProperty(value = "街道")
    private String street;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "超市Id")
    private Long marketId;

    @ApiModelProperty(value = "型号")
    private String  xh;

    @ApiModelProperty(value = "用户Id")
    private Long userId;

    @ApiModelProperty(value = "超市Id")
    private String market;

    @ApiModelProperty(value = "设备编号")
    private String number;

    @ApiModelProperty(value = "设备简介")
    private String detail;

}
