package com.siso.WSnetty;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Component
public class information {

//    @Autowired
//    EquipmentDao equipmentdao;
//
//
//    private static EquipmentDao equipmentDa;
//
//    @PostConstruct
//    public void init(){
//        equipmentDa = this.equipmentdao;  //将注入的对象交给静态对象管理
//    }

    public boolean state(String state,String name){
        String time= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
//        return equipmentDa.state(state,name,time);
        return false;
    }

    public String verification(String str){
      String  ml=str.substring(0,2);
      String nr=str.substring(2,str.length());
      if (ml.equals("re")){
        if(state("1",nr)){
            return nr;
        }
        else {
            return "false";
        }
      }
      return "false";

    }


}
