package com.siso.web.equipment;

import com.siso.Result.Result;
import com.siso.entity.web.equipMent.adminEquipment;
import com.siso.request.web.equipment.PageEquipmentRequest;
import com.siso.request.web.equipment.UpdateEquipmentRequest;
import com.siso.request.web.equipment.AddEquipmentRequest;
import com.siso.response.web.equipment.equipmentResponse;
import org.springframework.data.domain.Page;


import java.io.NotActiveException;
import java.util.List;

public interface EquipmentService {

//    public Result<List<equipDetailResponse>> equipmentdetails(EquipmentRequest request) throws ParseException;

    List<adminEquipment> admin_equipment(String userNumber);

    Result<Page<adminEquipment>> findEquipment(PageEquipmentRequest request);

     Result<String> updateEquipment(UpdateEquipmentRequest request);

     Boolean update_equipment_id(String userNumber,String staff_Number,String equipment_id);

    Result<List<equipmentResponse>> add_equi(AddEquipmentRequest request) throws NotActiveException;

     List<adminEquipment> getEquipment(Long userNumber,Long market);


    }
