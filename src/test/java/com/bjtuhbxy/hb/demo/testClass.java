package com.bjtuhbxy.hb.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class testClass {
    public static void main(String[] args) {

       String input = "\n" +
               "{\n" +
               "  \"04000401\": \"000000000001\",\n" +
               "\n" +
               "  \"00900100\": \"00000000\",\n" +
               "  \"00900101\": \"00000000\",\n" +
               "  \"00900200\": \"00001500\",\n" +
               "  \"00900201\": \"00000000\",\n" +
               "  \"000B0000\": \"00010000\",\n" +
               "  \"000B0001\": \"00009900\",\n" +
               "\n" +
               "  \"0280000B\": \"00055000\",\n" +
               "\n" +
               "  \"03320101\": \"2104010000\",\n" +
               "  \"03320201\": \"55\",\n" +
               "  \"03320301\": \"00006000\",\n" +
               "  \"03320401\": \"00000300\",\n" +
               "  \"03320501\": \"00006000\",\n" +
               "  \"03320601\": \"00900000\",\n" +
               "\n" +
               "  \"00010000\": \"00000000\",\n" +
               "\n" +
               "  \"00010100\": \"00000000\",\n" +
               "  \"00010200\": \"00000000\",\n" +
               "  \"00010300\": \"00000143\",\n" +
               "\n" +
               "  \"02010100\": \"2200\",\n" +
               "  \"02010200\": \"2200\",\n" +
               "  \"02010300\": \"2200\",\n" +
               "  \"0201FF00\": \"2200\",\n" +
               "\n" +
               "  \"02020100\": \"001000\",\n" +
               "  \"02020200\": \"001000\",\n" +
               "  \"02020300\": \"001000\",\n" +
               "  \"0202FF00\": \"001000\",\n" +
               "\n" +
               "  \"02030000\": \"053090\",\n" +
               "  \"02030100\": \"021990\",\n" +
               "  \"02030200\": \"015550\",\n" +
               "  \"02030300\": \"015550\"\n" +
               "\n" +
               "}";
        JSONObject jsonObject = JSONObject.parseObject(input);
//
        Map<String, String> map = (Map<String, String>) JSON.parse(input);

        System.out.println(map.get("04000401"));
    }


}
