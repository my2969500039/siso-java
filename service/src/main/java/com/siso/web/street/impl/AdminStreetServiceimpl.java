package com.siso.web.street.impl;

import com.siso.entity.web.market.adminMarket;
import com.siso.entity.web.region.market;
import com.siso.repository.web.street.adminStreetRepository;
import com.siso.web.street.AdminStreetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("admin_Street")
public class AdminStreetServiceimpl implements AdminStreetService {

    @Resource
    private adminStreetRepository adminStreetRepository;

    @Override
    public List<adminMarket>admin_market(String userNumber){
        System.out.println("账号"+userNumber+"请求查看商铺");
        return adminStreetRepository.findAllByNumber(userNumber);
    }

    @Override
    public List<market>market_id(String id){
//        return adminStreetRepository.findById(id);
        return null;
    }


}
