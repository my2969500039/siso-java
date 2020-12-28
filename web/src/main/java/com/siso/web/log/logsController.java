package com.siso.web.log;

import com.alibaba.fastjson.JSONObject;
import com.siso.response.Re;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Controller("logsController")
@RequestMapping("/log")
public class logsController {
    Re re=new Re();


    @RequestMapping(value = "/getlogs",method = RequestMethod.POST,produces="application/json;charset=UTF-8")//url注解，定义请求方式，字符串编码
    public @ResponseBody String getlogs(@RequestParam("userNumber") String userNumber){
        List<JSONObject> filelist = new ArrayList<>();
        File dir = new File("/etc/tomcat/apache-tomcat-9.0.31/logs/");
        File[] files = dir.listFiles();

        JSONObject jsonObject=new JSONObject();
        for (int i=0;i<files.length;i++){
            File tempFile =new File( files[i].toString().trim());
            String fileName = tempFile.getName();
            jsonObject.put(fileName,files[i].toString());
        }
        filelist.add(jsonObject);
        return re.pre_json(1,"True",filelist);
    }





     @RequestMapping(value = "/getlog",method = RequestMethod.POST,produces="application/json;charset=UTF-8")//url注解，定义请求方式，字符串编码
    public @ResponseBody String get_log(@RequestParam("url") String url){
         File file = new File(url);
         String result = "";
         try {
             BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
             String s = null;
             while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                 result = result + "<br>" + s;
             }
             br.close();
         } catch (Exception e) {
             return re.pre(0,"False","异常捕获，获取失败");
         }
         return re.pre(1,"True",result);
    }

}
