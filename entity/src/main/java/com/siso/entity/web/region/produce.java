package com.siso.entity.web.region;

import com.siso.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "produce")
@Table(name = "produce")
public class produce extends BaseEntity {

    private String name;
    private String produce;
    private String material;
    private String Packaging;
    private String yn;
    private String detoxifying;
    private String oxymoron;
    private String Hemoglobin;
    private String Ph;
    private String Colony;
    private String Volatile;
    private String market;
    private String number;

}