package com.siso.WSnetty;

import java.util.*;

public class modubs {

    private final static  String head1="0x38 ";
    private final static  String head2="0x78";
    private final static  String head3=" 0x23";
    private final static  String head4=" 0x23";

    public static void main(String[] args){
        List<String>list=new ArrayList<>();
        list.add("17");
        list.add("18");
        System.out.println(Bytes2HexString("111111111111111",list));
    }



    public static String Bytes2HexString(String b,List<String> list) {
        String ret = "";
        for (char value : b.toCharArray()) {
            String hex = Integer.toHexString(Integer.parseInt(String.valueOf(value)));
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += " 0x" + hex;
        }

        for (String value:list){
            String hex = Integer.toHexString(Integer.parseInt(String.valueOf(value)));
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += " 0x" + hex;
        }

        return head1+head2+ret+head3+head4;
    }





}


