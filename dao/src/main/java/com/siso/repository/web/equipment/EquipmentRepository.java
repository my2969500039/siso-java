package com.siso.repository.web.equipment;



import com.siso.entity.web.equipMent.adminEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<adminEquipment,String> , JpaSpecificationExecutor<adminEquipment> {



    //查询名下设备
    List<adminEquipment> findAllByUserId(Long userId);

    adminEquipment findOneByUserIdAndId(Long userIdm,Long id);

    //通过超市查询名下设备
    List<adminEquipment> findAllByUserIdAndMarketId(Long userNumber,Long market);    //通过超市查询名下设备

    //通过超市查询设备
    List<adminEquipment> findAllByMarketId(String market);


//    List<adminEquipment> findAllBy(@Param("userNumber") String userNumber);
//
//    //划转名下设备
//    @Update("UPDATE admin_equipment SET staff=#{userNumber} where staff=#{delete_Number}")
//    public  boolean update_equipment(@Param("userNumber") String userNumber,
//                                     @Param("delete_Number") String delete_Number);
//
//    //划转指定设备
//    @Update("UPDATE admin_equipment SET staff=#{staff_Number} where id=#{equipment_id}")
//    public  boolean update_equipment_id(@Param("staff_Number") String staff_Number,
//                                        @Param("equipment_id") String equipment_id);
//
//    @Update("update admin_equipment set state=#{state},online=#{time} where name=#{name}")
//    public  boolean state(@Param("state") String state,@Param("name") String name,@Param("time") String time);
//
//
//    //添加设备
//    @Insert("INSERT INTO admin_equipment (id,name,stree,state,market,xh,staff) VALUES (null,#{name},1,0,#{market},#{xh},#{staff})")
//    public Boolean add_equi(@Param("name") String name,@Param("market") String market,@Param("xh") String xh,@Param("staff") String staff);

}
