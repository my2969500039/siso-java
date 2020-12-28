package com.siso.entity.android.market;

import com.siso.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "android_market")
@Table(name = "android_market")
public class androidMarket extends BaseEntity {


    private int marketId;
    private int number;


}
