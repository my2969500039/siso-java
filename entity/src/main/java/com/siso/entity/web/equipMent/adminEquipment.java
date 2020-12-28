package com.siso.entity.web.equipMent;

import com.siso.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "admin_equipment")
@Table(name = "admin_equipment")
public class adminEquipment extends BaseEntity {
    private String name;
    private String street;
    private String state;
    private String marketId;
    private String  xh;
    private Long userId;
    private String market;
    private String number;

}
