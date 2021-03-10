package com.siso.web.equipment.impl;

import com.google.common.collect.Lists;
import com.siso.Result.Result;
import com.siso.dto.CMSUserDTO;
import com.siso.entity.web.equipMent.adminEquipment;
import com.siso.exception.NormalException;
import com.siso.request.web.equipment.PageEquipmentRequest;
import com.siso.request.web.equipment.UpdateEquipmentRequest;
import com.siso.repository.web.equipment.EquipmentRepository;
import com.siso.token.TokenUtils;
import com.siso.web.equipment.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Resource
    private EquipmentRepository equipmentRepository;

    @Autowired
    private TokenUtils tokenUtils;

    public String date(String endtime,String greattime) throws ParseException {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        java.util.Date now = df.parse(greattime);
        java.util.Date date=df.parse(endtime);
        long l=now.getTime()-date.getTime();
        long day=l/(24*60*60*1000);
        long hour=(l/(60*60*1000)-day*24);
        long min=((l/(60*1000))-day*24*60-hour*60);
        long s=(l/1000-day*24*60*60-hour*60*60-min*60);
        return  (day+"天"+hour+"小时"+min+"分"+s+"秒");
    }

    //查询名下设备
    @Override
    public Result<Page<adminEquipment>> findEquipment(PageEquipmentRequest request) {
        CMSUserDTO cmsUserDTO=tokenUtils.getLoginUserDTO();
        Pageable pageable = PageRequest.of(request.getPageNum(), request.getPageSize(), Sort.by(Sort.Direction.DESC,"createTime"));
        Specification<adminEquipment> specification = (Specification<adminEquipment>) (root, criteriaQuery, criteriaBuilder) -> {
            List<javax.persistence.criteria.Predicate> predicates = Lists.newArrayList();
            if (request.getMarketId()!=null){
                predicates.add(criteriaBuilder.equal(root.get("marketId").as(Long.class), request.getMarketId()));
            }
            predicates.add(criteriaBuilder.equal(root.get("userId").as(Long.class), cmsUserDTO.getId()));
            if (request.getKeyWord()!=null)
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(root.get("name").as(String.class), "%"+request.getKeyWord()+"%"),
                        criteriaBuilder.like(root.get("market").as(String.class), "%"+request.getKeyWord()+"%")
                ));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        Page<adminEquipment>adminEquipments=equipmentRepository.findAll(specification,pageable);
        return Result.<Page<adminEquipment>>builder().success().data(adminEquipments).build();
    };



    //划转名下设备
    @Override
    @Transactional
    public Result<String> updateEquipment(UpdateEquipmentRequest request){
        CMSUserDTO cmsUserDTO=tokenUtils.getLoginUserDTO();
        adminEquipment adminEquipment = equipmentRepository.findOneByUserIdAndId(cmsUserDTO.getId(),request.getId());
        if (adminEquipment==null)
            throw new NormalException("设备不存在");
        adminEquipment.setUserId(request.getUserId());
        equipmentRepository.save(adminEquipment);
        return Result.<String>builder().success().message("划转成功").build();
    };

    @Override
    public Result<adminEquipment>getOne(Long id){
        adminEquipment adminEquipment=equipmentRepository.findOneById(id);
        if (adminEquipment==null)
            throw new NormalException("设备不存在");
        return Result.<adminEquipment>builder().success().data(adminEquipment).build();
    };




}
