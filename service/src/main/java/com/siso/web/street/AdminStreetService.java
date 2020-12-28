package com.siso.web.street;

import com.siso.entity.web.market.adminMarket;
import com.siso.entity.web.region.market;


import java.util.List;

public interface AdminStreetService {

    //返回用户管理商铺
     List<adminMarket> admin_market(String userNumber);


    //根据id返回超市
     List<market>market_id(String id);
}
