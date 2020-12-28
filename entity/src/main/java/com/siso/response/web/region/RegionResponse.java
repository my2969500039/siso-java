package com.siso.response.web.region;

import com.siso.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegionResponse extends BaseEntity {
    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("上级ID")
    private Long parentId;

    private String value;

    private String label;

    private boolean  isLeaf;

    private List<RegionResponse> children=new ArrayList<>();

    public boolean getIsLeaf(){
        if (this.children.size()==0){
            return this.isLeaf=true;
        }
        else return this.isLeaf=false;
    }

}
