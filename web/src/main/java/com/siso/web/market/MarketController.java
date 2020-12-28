//package com.siso.web.market;
//
//
//import com.alibaba.fastjson.JSONObject;
//import com.siso.entity.web.market.adminMarket;
//import com.siso.response.Re;
//import com.siso.repository.web.street.adminStreetService;
//import com.siso.repository.web.userManage.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller("marketcontroller")
//@RequestMapping("/market")
//public class MarketController {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private adminStreetService adminstreetService;
//
//
//
//    Re re=new Re();
//
//    /** * 获取管理商铺
//     * @return */
//    @RequestMapping(value = "/find_market",method= RequestMethod.POST,produces="application/json;charset=UTF-8")//url注解，定义请求方式，字符串编码
//    public @ResponseBody String admin_market(@RequestParam("userNumber") String userNumber){
//        List<adminMarket> admin_markets =admin_streetService.admin_market(userNumber);
//        if (admin_markets.size()==0){
//            return re.pre(0,"False","暂未管理任何超市");
//        }
//        else {
//            List<JSONObject> list_street = new ArrayList<>();
//            for (int i = 0; i< admin_markets.size(); i++){
//                JSONObject jsonObject=new JSONObject();
//                jsonObject.put("id", admin_markets.listIterator(i).next().getId());
//
//
//                List<market> markets=admin_streetService.market_id(admin_markets.listIterator(i).next().getMarket_id());
//                jsonObject.put("name",markets.listIterator(i).next().getName());
//
//
//                jsonObject.put("Street", admin_markets.listIterator(i).next().getStreet_id());
//
//
//
//                list_street.add(jsonObject);
//            }
//
//            return re.pre_json(1,"True",list_street);
//        }
//
//
//
//    }
//
//}
