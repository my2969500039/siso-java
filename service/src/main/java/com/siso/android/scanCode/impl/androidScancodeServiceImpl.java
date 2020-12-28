package com.siso.repository.android.scanCode.impl;


import com.siso.repository.android.scanCode.androidScancodeService;
import com.siso.entity.web.equipMent.adminEquipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("androidScancode")
public class androidScancodeServiceImpl implements androidScancodeService {

    @Autowired
    private com.siso.repository.android.scanCode.androidScancodeRepository androidScancodeRepository;

    @Override
    public adminEquipment get_equipment(String equipment_id, String number){
        System.out.println("用户"+number+"扫码成功");
        return androidScancodeRepository.findAllById(equipment_id);
    };

//
//    @Override
//    public Boolean insert_equipment(String name,String produce,String material,String Packaging,String yn,String Deoxymyoglobin,
//                                    String Oxymyoglobin,String Ferrimyoglobin,String Ph,String Colony,String Volatile,String time,String market,String number){
//
//        return androidScancodeRepository.insert_equipment(name, produce, material, Packaging, yn, Deoxymyoglobin, Oxymyoglobin, Ferrimyoglobin, Ph, Colony, Volatile, time, market, number);
//    };

//
//    @Override
//    public List<produce> scancode_record(String number){
//        System.out.println("用户"+number+"获取扫码记录");
//        return androidScancodeRepository.find(number);
//    };
//

}
