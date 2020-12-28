package com.siso.web.equipment.impl;

import com.google.common.collect.Lists;
import com.siso.Result.Result;
import com.siso.dto.CMSUserDTO;
import com.siso.entity.web.equipMent.adminEquipment;
import com.siso.request.web.equipment.PageEquipmentRequest;
import com.siso.request.web.equipment.UpdateEquipmentRequest;
import com.siso.repository.web.equipment.EquipmentRepository;
import com.siso.request.web.equipment.AddEquipmentRequest;
import com.siso.response.web.equipment.equipmentResponse;
import com.siso.token.TokenUtils;
import com.siso.web.equipment.EquipmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.io.NotActiveException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Resource
    private EquipmentRepository equipmentRepository;

    @Autowired
    private TokenUtils tokenUtils;
//    @Override
//    public Result<List<equipDetailResponse>> equipmentdetails(EquipmentRequest request) throws ParseException {
//        String time= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
//        List<equipDetailResponse> equipmentResponseList=equipmentdetails.findAllById(request.getEquipmentId());
//        for (equipDetailResponse e:equipmentResponseList
//             ) {
//            if(e.getState().equals("1")){
//                e.setOnline("在线时长   "+date(e.getOnline(),time));
//            }
//            else e.setOnline("离线时长  "+date(e.getOnline(),time));
//        }
//        return Result.< List<equipDetailResponse>>builder().success().data(equipmentResponseList).build();
//    };


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


    //管理员查询名下设备
    @Override
    public List<adminEquipment> admin_equipment(String userNumber){
        CMSUserDTO cmsUserDTO=tokenUtils.getLoginUserDTO();
        return equipmentRepository.findAllByUserId(cmsUserDTO.getId());
    };



    //划转名下设备
    @Override
    public Result<String> updateEquipment(UpdateEquipmentRequest request){
        CMSUserDTO cmsUserDTO=tokenUtils.getLoginUserDTO();
        adminEquipment adminEquipment = equipmentRepository.findOneByUserIdAndId(cmsUserDTO.getId(),request.getId());
        return Result.<String>builder().success().message("划转成功").build();
    };



    //划转指定设备
    @Override
    public Boolean update_equipment_id(String userNumber,String staff_Number,String equipment_id){
        System.out.println("账号"+userNumber+"请求划转名下设备");
//        return  equipmentdetails.update_equipment_id(staff_Number,equipment_id);
        return  null;

    };

    //添加设备
    @Override
    public Result<List<equipmentResponse>> add_equi(AddEquipmentRequest request) throws NotActiveException {
        adminEquipment equipment=new adminEquipment();
        BeanUtils.copyProperties(request,equipment);
        equipment=equipmentRepository.save(equipment);
        if (equipment.getId()!=null) {
            List<adminEquipment> equipmentResponseList=equipmentRepository.findAllByMarketId(request.getMarket());
            List<equipmentResponse>equipmentResponses=new ArrayList<>();
            equipmentResponseList.forEach(e->{
                equipmentResponse equipmentResponse = new equipmentResponse();
                BeanUtils.copyProperties(e,equipmentResponse);
                equipmentResponses.add(equipmentResponse);
            });
            return Result.< List<equipmentResponse>>builder().success().data(equipmentResponses).build();
        }
            else throw new NotActiveException("添加失败");

    }


    //通过超市查询名下设备
    @Override
    public List<adminEquipment> getEquipment(Long userNumber,Long market){
        return equipmentRepository.findAllByUserIdAndMarketId(userNumber, market);
    };




}
