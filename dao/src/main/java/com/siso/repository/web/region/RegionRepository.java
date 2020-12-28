package com.siso.repository.web.region;



import com.siso.entity.web.region.area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

public interface RegionRepository extends JpaRepository<area,String>, JpaSpecificationExecutor<area> {


    area findOneById(Long id);

}
