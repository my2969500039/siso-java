package com.siso.entity.web.region;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.siso.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "area")
@Table(name = "area",uniqueConstraints = {})
public class area extends BaseEntity {

    private String name;

    private Long parentId;

    @Transient
    private String value;

    @Transient
    private String label;

    public String getValue(){
       return this.value=this.name;
    }

    public String getLabel(){
       return this.label=this.name;
    }


}
