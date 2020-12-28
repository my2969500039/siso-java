package com.siso.response;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.util.List;

/**
 * return类，接收返回值信息处理成json数据返回
 * **/
public class Re {
    public String pre(int a,String b,String c){
        JSONObject object = new JSONObject();
        object.put("value", a);
        object.put("result", b);
        object.put("return", c);
        JSONObject json = JSONObject.parseObject("" + object.toString() + "");
        String pretty = JSON.toJSONString(json, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
        return pretty;
    }


    public String pre_json(int a, String b, List<JSONObject> c){
        JSONObject object = new JSONObject();
        object.put("value", a);
        object.put("result", b);
        object.put("return", c);
        JSONObject json = JSONObject.parseObject("" + object.toString() + "");
        String pretty = JSON.toJSONString(json, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
        return pretty;
    }


}