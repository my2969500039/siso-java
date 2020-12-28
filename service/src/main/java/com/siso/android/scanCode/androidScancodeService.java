package com.siso.repository.android.scanCode;

import com.siso.entity.web.equipMent.adminEquipment;

public interface androidScancodeService {

    //扫码获取设备信息（准备启动）
    adminEquipment get_equipment(String equipment_id, String number);

//    //添加扫码记录
//    public Boolean insert_equipment(String name,String produce,String material,String Packaging,String yn,String Deoxymyoglobin,
//                                    String Oxymyoglobin,String Ferrimyoglobin,String Ph,String Colony,String Volatile,String time,String market,String number);
//
//   //获取扫码记录
//    public List<produce> scancode_record(String number);

}
