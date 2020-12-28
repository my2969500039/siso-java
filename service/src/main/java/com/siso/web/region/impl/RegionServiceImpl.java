package com.siso.web.region.impl;

import com.siso.Result.Result;
import com.siso.entity.web.region.area;
import com.siso.exception.NormalException;
import com.siso.repository.web.region.RegionRepository;
import com.siso.request.web.region.AddRegionRequest;
import com.siso.response.web.region.RegionResponse;
import com.siso.web.region.RegionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("regionService")
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionRepository regionRepository;



    @Override
    public Result<List<RegionResponse>> region(){
        List<area>areaList=regionRepository.findAll();
        List<RegionResponse>regionResponses=new ArrayList<>();
        List<RegionResponse> finalRegionResponses = regionResponses;
        areaList.forEach(a->{
            RegionResponse regionResponse=new RegionResponse();
            BeanUtils.copyProperties(a,regionResponse);
            finalRegionResponses.add(regionResponse);
        });
        regionResponses=makeTree(regionResponses);
        return Result.<List<RegionResponse>>builder().success().data(regionResponses).build();
    }


    public List<RegionResponse> makeTree(List<RegionResponse> responses) {
        List<RegionResponse> list = new LinkedList<>();
        Map<Long, RegionResponse> labelMap =responses.stream().collect(Collectors.toMap(RegionResponse::getId, v->v,(v1, v2)->v1));
        for (RegionResponse e : responses) {
            if (e.getParentId() == null ||  e.getParentId()==0  ) {
                list.add(e);
            }
            if (e.getParentId() != null && labelMap.containsKey(e.getParentId())) {
                labelMap.get(e.getParentId()).getChildren().add(e);
            }
        }
        return list;
    }



    @Override
    @RequiresPermissions(value = "applicationCenter:basicConfigure:detail")
    public Result<String>addRegion(AddRegionRequest request){
        area area=new area();
        BeanUtils.copyProperties(request,area);
        area regionRepositoryOneById=regionRepository.findOneById(request.getParentId());
        if (regionRepositoryOneById == null)
            throw  new NormalException("上级栏目不存在");
        regionRepository.save(area);
        return  Result.<String>builder().success().message("添加成功").build();
    }





}
