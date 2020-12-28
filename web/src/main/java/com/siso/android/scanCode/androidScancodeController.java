package com.siso.android.scanCode;


import com.alibaba.fastjson.JSONObject;
import com.siso.entity.web.equipMent.adminEquipment;

import com.siso.response.Re;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller("androidScancodeController")
@RequestMapping("androidScancode")
public class androidScancodeController {

    Re re= new Re();
//
//    @Autowired
//    private socket socket1;


    @Autowired
    private com.siso.repository.android.scanCode.androidScancodeService androidScancodeService;//安卓扫码相关Service

//    @Autowired
//    private adminStreetService admin_streetService;//超市相关Service

    //扫码获取设备信息
    @ApiOperation(value = "安卓登录接口", notes = "参数描述", code = 200, produces = "application/json")
    @RequestMapping(value = "/getequipment",method= RequestMethod.POST,produces="application/json;charset=UTF-8")//url注解，定义请求方式，字符串编码
    public  @ResponseBody String Scancode(@RequestParam("equipment_id") String equipment_id,@RequestParam("number") String number) throws InterruptedException {
        adminEquipment equipments =androidScancodeService.get_equipment(equipment_id, number);
        if (equipments==null){
            return re.pre(0,"False","设备不存在");
        }

        else {
//            List<market> markets=admin_streetService.market_id(equipments.listIterator(0).next().getMarket());

            List<JSONObject> list = new ArrayList<>();

            JSONObject jsonObject =new JSONObject();
//            jsonObject.put("market",markets.listIterator(0).next().getName());
//            jsonObject.put("Equipment_bame",equipments.listIterator(0).next().getName());
//            jsonObject.put("state",equipments.listIterator(0).next().getState());
//            jsonObject.put("id",equipments.listIterator(0).next().getId());

            list.add(jsonObject);
            return re.pre_json(1,"True",list);
        }

    }


    //启动设备
    @RequestMapping(value = "/start_Equipment",method= RequestMethod.POST,produces="application/json;charset=UTF-8")//url注解，定义请求方式，字符串编码
    public  @ResponseBody String start_Equipment(@RequestParam("equiment_name") String equipment_name,
                                                 @RequestParam("number") String number,
                                                 @RequestParam("produce") String produce,
                                                 @RequestParam("material") String material,
                                                 @RequestParam("yn") String yn,
                                                 @RequestParam("market") String market) throws InterruptedException {

        SimpleDateFormat time= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");

        //保存相关数据至数据库，利用Netty向设备发送开启请求，并将接收的检查信息存入数据库，同时返回给安卓（可异步）

        List<JSONObject> list = new ArrayList<>();
        JSONObject jsonObject =new JSONObject();
        TimeUnit.SECONDS.sleep(5);

//        Boolean result=androidScancodeService.insert_equipment("猪肉",produce,material,"2",yn,"2"
//                ,"2","2","2","2","2",time.toString(),market,number);
//
//        socket1.seed(equipment_name,"89"); todo




        jsonObject.put("market",market);
        jsonObject.put("time",time.toString());
        jsonObject.put("Volatile","设备还用不了");
        jsonObject.put("Colony","数据只能写死");
        jsonObject.put("Ph","没办法");
        jsonObject.put("Ferrimyoglobin","都是死的");
        jsonObject.put("Oxymyoglobin","数据写死了");
        jsonObject.put("Deoxymyoglobin","就这样");

        list.add(jsonObject);
        return  re.pre_json(1,"True",list);

     }


    //获取扫码记录
    @RequestMapping(value = "/scancode_record",method= RequestMethod.POST,produces="application/json;charset=UTF-8")//url注解，定义请求方式，字符串编码
    public  @ResponseBody String scancode_record(@RequestParam("number") String number){

//        List<produce> produces = androidScancodeService.scancode_record(number);
        if (1==0){
            return re.pre(0,"False","暂无记录");
        }

        else {
            List<JSONObject> list = new ArrayList<>();

//            for (int i=0;i<produces.size();i++){
//                JSONObject jsonObject =new JSONObject();
//                jsonObject.put("name",produces.listIterator(i).next().getName());
//                jsonObject.put("produce",produces.listIterator(i).next().getProduce());
//                jsonObject.put("material",produces.listIterator(i).next().getMaterial());
//                jsonObject.put("Packaging",produces.listIterator(i).next().getPackaging());
//                jsonObject.put("yn",produces.listIterator(i).next().getYn());
//                jsonObject.put("Deoxymyoglobin",produces.listIterator(i).next().getDeoxymyoglobin());
//                jsonObject.put("Oxymyoglobin",produces.listIterator(i).next().getOxymyoglobin());
//                jsonObject.put("Ferrimyoglobin",produces.listIterator(i).next().getFerrimyoglobin());
//                jsonObject.put("Ph",produces.listIterator(i).next().getPh());
//                jsonObject.put("Volatile",produces.listIterator(i).next().getVolatile());
//                jsonObject.put("Colony",produces.listIterator(i).next().getColony());
//                jsonObject.put("time",produces.listIterator(i).next().getTime());
//                jsonObject.put("id",produces.listIterator(i).next().getId());
//
//                List<market>markets=admin_streetService.market_id(produces.listIterator(i).next().getMarket());
//                jsonObject.put("market",markets.listIterator(0).next().getName());
//                list.add(jsonObject); todo
//            }
            return re.pre_json(1,"True",list);

        }

    }


    @RequestMapping(value = "qq/",method= RequestMethod.POST,produces="application/json;charset=UTF-8")//url注解，定义请求方式，字符串编码
    public  void test() throws Exception {
       throw new Exception("异常抛出");
    }


}
