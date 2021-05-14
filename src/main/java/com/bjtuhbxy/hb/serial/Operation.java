package com.bjtuhbxy.hb.serial;

import com.sun.deploy.util.StringUtils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Operation {

    //发送跳闸指令
    public static String trip(String A5_0, String P2_0, String PA, String C3_0, String N1){
        //N1=1AH代表跳闸，N1=1BH代表合闸允许，N1=2AH代表报警，N1=2BH代表报警解除，N1=3AH代表保电，N1=3BH代表保电解除。N2保留。N3～N8代表命令有效截止时间，数据格式为ssmmhhDDMMYY。
        Date date = new Date(System.currentTimeMillis()+ 24*60*60*1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        String C = "1C";
        String N2 = "09";
        String N8_3=sdf.format(date);
        String DATA = add33H(HtoL(P2_0+PA) + HtoL(C3_0)+ HtoL(N8_3+N2+N1));
        String L = ByteToHex((byte)(DATA.length()/2));
        String str = "68"+HtoL(A5_0)+"68"+C+L+DATA;
        String CS = getCs(str);
        return "FEFEFEFE"+str+CS+"16";
    }

    //D3_0数据标识
    public static String read(String A5_0, String D3_0){
        String atad33 = add33H(HtoL(D3_0));
        String str = "68"+HtoL(A5_0)+"68"+"11"+"04"+atad33;
        String CS = getCs(str);
        return "FEFEFEFE"+str+CS+"16";
    }

//    public static String readReturn(String readRes){
//
//        String atad33 = add33H(HtoL(D3_0));
//        String str = "68"+HtoL(A5_0)+"68"+"11"+"04"+atad33;
//        String CS = getCs(str);
//        return str+CS+"16";
//    }
//D3_0数据标识

    //    DIODI1DI2DI3+PAP0P1P2+C0C1C2C3+DATA
    //C3_0 操作者代码
    public static String write(String A5_0, String D3_0, String P2_0, String PA, String C3_0, String DATA){
        String dataField = HtoL(D3_0)+HtoL(P2_0+PA)+HtoL(C3_0)+HtoL(DATA);
        String dataField33 = add33H(dataField);
        String L = Integer.toHexString(dataField33.length()/2);
        String str = "68"+HtoL(A5_0)+"68"+"14"+L+dataField33;
        String CS = getCs(str);
        return str+CS+"16";
    }

    public static String add33H(String d) {
        List<String> s = new ArrayList<>();
        for (int i =0;i<d.length();i+=2){
            int t =Integer.parseInt(d.substring(i,i+2),16);
            //51 = 33H
            t +=51;
            s.add(ByteToHex((byte) t));
        }
        return StringUtils.join(s,"");
    }

    public static String red33H(String d) {
        List<String> s = new ArrayList<>();
        String temp;
        for (int i =0;i<d.length();i+=2){
            int t =Integer.parseInt(d.substring(i,i+2),16);
            t -=51;
            s.add(ByteToHex((byte) t));
        }
        return StringUtils.join(s,"");
    }

    //byte 转 两位16进制字符串
    public static String ByteToHex(byte b) {
        return Integer.toHexString((b & 0xff)|0xFFFFFF00).substring(6).toUpperCase();
    }

    public static String HtoL(String s) {
        List<String> strs = new ArrayList();
        //List初始化
        for (int i = 0; i<s.length()/2;i++){
            strs.add("");
        }
        for (int i = 0; i <s.length() ; i+=2) {
            strs.add((s.length()-i)/2,s.substring(i,i+2));
        }
        return StringUtils.join(strs,"");
    }

    //计算校验码
    public static String getCs(String data) {
        byte[] bt = hexStrToByteArray(data);
        int a = 0;
        for (byte b: bt) {
            a= a+b;
        }
        return (Integer.toHexString(a%256).toUpperCase());
    }

    //16进制转byte数组
    public static byte[] hexStrToByteArray(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return new byte[0];
        }
        byte[] byteArray = new byte[str.length() / 2];
        for (int i = 0; i < byteArray.length; i++) {
            String subStr = str.substring(2 * i, 2 * i + 2);
            byteArray[i] = ((byte) Integer.parseInt(subStr, 16));
        }
        return byteArray;
    }
}
