package com.siso.web.region;

import com.siso.Result.Result;
import com.siso.request.web.region.AddRegionRequest;
import com.siso.response.web.region.RegionResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


@RestController("regionController")
@RequestMapping("/region")
@Api(value = "/region" ,tags ={"城市模块"} )
public class regionController {


    @Resource
    private RegionService regionService;

    @GetMapping(value = "/region",produces="application/json;charset=UTF-8")//url注解，定义请求方式，字符串编码
    @ApiOperation(value = "/region")
    public Result<List<RegionResponse>> region(){

        return regionService.region();
    }

    @PostMapping(value = "/region",produces="application/json;charset=UTF-8")
    @ApiOperation(value = "/region")
    public Result<String>addRegion(@Valid @RequestBody AddRegionRequest request, BindingResult result){
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return Result.<String>builder().fail().code(500).message(error.getDefaultMessage()).build();
            }
        }
        return regionService.addRegion(request);
    }


//
//    @RequestMapping(value = "/find_equipment",method = RequestMethod.POST,produces="application/json;charset=UTF-8")//url注解，定义请求方式，字符串编码
//    public @ResponseBody String region(@RequestParam("userNumber") String userNumber,@RequestParam("market_id") String market_id){
//        List<JSONObject>list= new ArrayList<>();
//        List<admin_equipment>Admin_equipment;
//        if(userService.userlogin1(userNumber).getAuthority()==1){
//           Admin_equipment=equipmentService.get_equiment_admin(userNumber, market_id);
//        }
//        else {
//            Admin_equipment=equipmentService.get_equiment(userNumber, market_id);
//        }
//        for (int i=0;i<Admin_equipment.size();i++){
//            JSONObject jsonObject=new JSONObject();
//            jsonObject.put("name",Admin_equipment.listIterator(i).next().getName());
//            jsonObject.put("street",Admin_equipment.listIterator(i).next().getStree());
//            List<market> markets=admin_streetService.market_id(Admin_equipment.listIterator(i).next().getMarket());//根据id获取超市名称
//            jsonObject.put("market",markets.listIterator(0).next().getName());//getName获取超市名称
//            jsonObject.put("time",Admin_equipment.listIterator(i).next().getTime());
//            jsonObject.put("model",Admin_equipment.listIterator(i).next().getXh());
//            jsonObject.put("size",Admin_equipment.size());
//            jsonObject.put("state",Admin_equipment.listIterator(i).next().getState());
//            list.add(jsonObject);
//        }
//        return re.pre_json(1,"True",list);
//
//    }


}
