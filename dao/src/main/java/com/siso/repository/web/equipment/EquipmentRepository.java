package com.siso.repository.web.equipment;



import com.siso.entity.web.equipMent.adminEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<adminEquipment,Long> , JpaSpecificationExecutor<adminEquipment> {



    //查询名下设备
    List<adminEquipment> findAllByUserId(Long userId);

    adminEquipment findOneByUserIdAndId(Long userIdm,Long id);

    adminEquipment findOneById(Long id);


}
