package com.siso.repository.web.street;


import com.siso.entity.web.market.adminMarket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface adminStreetRepository extends JpaRepository<adminMarket,String> {

    //查看商铺
//    @Select("select * from admin_market where number=#{userNumber} ")
    public List<adminMarket>findAllByNumber(String userNumber);
//
//    //根据id查找商铺
//    @Select("select * from market where id=#{market_id} ")
//    public List<market>market(@Param("market_id") String market_id);

}
