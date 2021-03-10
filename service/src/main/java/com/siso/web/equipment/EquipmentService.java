package com.siso.web.equipment;

import com.siso.Result.Result;
import com.siso.entity.web.equipMent.adminEquipment;
import com.siso.request.web.equipment.PageEquipmentRequest;
import com.siso.request.web.equipment.UpdateEquipmentRequest;
import com.siso.request.web.equipment.AddEquipmentRequest;
import com.siso.response.web.equipment.equipmentResponse;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import javax.validation.Valid;
import java.io.NotActiveException;
import java.util.List;

public interface EquipmentService {



    Result<Page<adminEquipment>> findEquipment(PageEquipmentRequest request);

    Result<String> updateEquipment(UpdateEquipmentRequest request);

    Result<adminEquipment>getOne(Long id);

    Result<String> add( AddEquipmentRequest request);

    Result<String> delete(Long id);

}
