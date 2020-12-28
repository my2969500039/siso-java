package com.siso.repository.android.scanCode;



import com.siso.entity.web.equipMent.adminEquipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface androidScancodeRepository extends JpaRepository<adminEquipment,String> {

    //扫码获取设备信息
    adminEquipment findAllById(String equipmentId);

//    //临时（添加扫码记录）
//    @Insert("INSERT INTO android_produce (id,name,produce,material,Packaging,yn,Deoxymyoglobin,Oxymyoglobin,Ferrimyoglobin,Ph,Colony,Volatile,time,market,number)" +
//            " VALUES (null,#{name},#{produce},#{material}," +
//            "#{Packaging},#{yn},#{Deoxymyoglobin},#{Oxymyoglobin},#{Ferrimyoglobin},#{Ph},#{Colony},#{Volatile},#{time},#{market},#{number})")
//    public Boolean insert_equipment(
//                                                    @Param("name") String name, @Param("produce") String produce, @Param("material") String material, @Param("Packaging") String Packaging,
//                                                    @Param("yn") String yn, @Param("Deoxymyoglobin") String Deoxymyoglobin, @Param("Oxymyoglobin") String Oxymyoglobin, @Param("Ferrimyoglobin") String Ferrimyoglobin,
//                                                    @Param("Ph") String Ph, @Param("Colony") String Colony, @Param("Volatile") String Volatile,
//                                                    @Param("time") String time, @Param("market") String market, @Param("number") String number
//                                                 );



    //获取扫码记录
//    @Select("select * from android_produce where number=#{number}")
//    List<produce>findAllByNumber(String number); //todo



}
