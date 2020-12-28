package com.siso.entity.web.market;

import com.siso.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "admin_market")
@Table(name = "admin_market")
public class adminMarket extends BaseEntity {

    private String number;
    private String marketId;
    private String streetId;


}
