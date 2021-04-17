package com.siso.WSnetty;

import com.siso.Result.Result;
import com.siso.entity.web.equipMent.adminEquipment;
import com.siso.repository.web.equipment.EquipmentRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
public class ModubusChannel {

    private final static  String head1="0x38";//帧头
    private final static  String head2="0x78";
    private final static  String head3="0x23";//帧尾
    private final static  String head4="0x23";

    @Autowired
    private EquipmentRepository equipmentRepository;

    public static String Bytes2HexString(String b,List<String> list) {
        String ret = "";
        for (char value : b.toCharArray()) {
            String hex = Integer.toHexString(Integer.parseInt(String.valueOf(value)));
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += "0x" + hex;
        }
        for (String value:list){
            String hex = Integer.toHexString(Integer.parseInt(String.valueOf(value)));
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += "0x" + hex;
        }
        return head1+head2+ret+head3+head4;
    }


    public String tenToSix(String msg){
        String ret = "";
        for (char value : msg.toCharArray()) {
            String hex = Integer.toHexString(Integer.parseInt(String.valueOf(value)));
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += "0x" + hex;
        }
        return ret;
    }


    public String sixToTen(List<String> msg){
        AtomicReference<String> ret= new AtomicReference<>("");
        msg.forEach(m->{
            ret.set(ret + String.valueOf(Long.parseLong("23", 16)));
        });
        return ret.get();
    }

    //注册方法
    public Result register(String msg) {
        List<String> msglist = Arrays.stream(msg.split("0x")).filter(StringUtils::isNoneBlank).collect(Collectors.toList());
        Integer length = msglist.size();
        if (length < 7)
            return Result.builder().data(false).build();
        //帧头帧尾不匹配直接舍弃
        if (msglist.get(0).equals("38") && msglist.get(1).equals("78") && msglist.get(length - 2).equals("23") && msglist.get(length - 1).equals("23")) {
            List<String> ids = msglist.subList(2, 4);
            List<String> ml = msglist.subList(4, 5);
            String code = msglist.subList(length - 3, length - 2).get(0);
            if (sum(msglist).equals(code)) {
                if (ml.get(0).equals("76")) {
                    String sheId = sixToTen(ids);
                    adminEquipment adminEquipment = equipmentRepository.findAllByNumber(sheId);
                    if (adminEquipment != null)
                    {
                        adminEquipment.setAvailable(true);
                        equipmentRepository.save(adminEquipment);
                        return Result.builder().message(sheId).data(true).code(201).build();
                    }
                }
            }
        }
        return Result.builder().data(false).build();
    }

    //移除注册方法
    public void removeRegister(String number){
        adminEquipment adminEquipment= equipmentRepository.findAllByNumber(number);
        if (adminEquipment!=null) {
            adminEquipment.setAvailable(false);
            equipmentRepository.save(adminEquipment);
        }
    }

    public Result distribution(List<String> ids,List<String> ml,List<String> mlContent){
        //其他命令字验证
        return Result.builder().data(false).build();
    }

    public String sum(List<String> msglist){
        Integer length=msglist.size();
        String value=String.valueOf(msglist.subList(2,length-3).stream().map(a->Integer.parseInt(a)).mapToInt(Integer::intValue).sum());
        System.out.println(value.substring(value.length()-2,value.length()));
        return value.substring(value.length()-2,value.length());
    }


    public Result validation(String msg){
        List<String> msglist= Arrays.stream(msg.split("0x")).filter(StringUtils::isNoneBlank).collect(Collectors.toList());
        Integer length=msglist.size();
        //综合计算命令长度最少七位，少于七位直接舍弃，释放资源
        if (length<7)
            return Result.builder().data(false).build();
        //帧头帧尾不匹配直接舍弃
        if (msglist.get(0).equals("38") && msglist.get(1).equals("78")&&msglist.get(length-2).equals("23")&&msglist.get(length-1).equals("23"))
        {
            //设备id
            List<String> ids=msglist.subList(2,4);
            //命令字
            List<String>ml=msglist.subList(4,5);
            //命令内容
            List<String>mlContent=msglist.subList(5,length-3);
            //校验码
            String code=msglist.subList(length-3,length-2).get(0);
            if (sum(msglist).equals(code)){
                return distribution(ids,ml,mlContent);
            }
        }
        return Result.builder().data(false).build();
   }

    public static void main(String[] args) {
        System.out.println("123456".charAt(-2));
    }


}


