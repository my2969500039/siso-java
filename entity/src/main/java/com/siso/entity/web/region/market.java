package com.siso.entity.web.region;

import com.siso.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "market")
@Table(name = "market")
public class market extends BaseEntity {

    private String name;
    private String streetId;

}
