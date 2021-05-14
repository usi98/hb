package com.bjtuhbxy.hb.serial;

import com.alibaba.fastjson.JSON;
import com.bjtuhbxy.hb.entity.Meter;
import com.bjtuhbxy.hb.service.MeterService;
import gnu.io.SerialPortEvent;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class ReceiveUtil {

    Logger logger = LoggerFactory.getLogger(ReceiveUtil.class);

    @Autowired
    ComMeter comMeter;

    @Autowired
    MeterService meterService;

    //receive 为电表接收数据
    public String respond(String receive, Map map){
        if("FEFEFEFE".equals(receive.substring(0,8))) {
            receive = receive.substring(8);
        }else{
            logger.info("从站收到错误请求，无数据前缀！");
            return "";
        }

        int length = receive.length();

        String CS = Operation.getCs(receive.substring(0,receive.length()-4));
        if(!CS.equals(receive.substring(receive.length()-4,receive.length()-2))){
            logger.info("从站收到错误请求，校验和错误！");
            return "000000";
        }
        String address = Operation.HtoL(receive.substring(2,14));

        if(!address.equals(StringUtils.clean((String) map.get("04000401")))){
            logger.info("从站收到错误请求，通讯地址错误！");
            return "000000";
        }
        //字节长度
        int L = Integer.parseInt(receive.substring(18,20),16);

//        System.out.println("L:"+L);

        String DATA = receive.substring(20,20+L*2);

//        System.out.println("DATA:"+DATA);

        String D3_0 =Operation.HtoL(Operation.red33H(DATA.substring(0,8)));

        DATA = Operation.HtoL(Operation.red33H(DATA));

        //控制码
        String C = receive.substring(16,18);
        System.out.println("控制码="+C);


        String ret="";
        switch (C){
            case "11":
                //读数据
                ret = readdata(map, D3_0);
                break;
            case "1C":
                //跳闸、合闸允许、报警、报警解除、保电、保电解除
                ret = trip(receive, map, D3_0);
                break;
        }
        return ret;

    }

    private String readdata(Map map,String D3_0){
        logger.info("读"+D3_0);
        String Nm_1 = StringUtils.clean((String)map.get(D3_0));
        String address = StringUtils.clean((String)map.get("04000401"));
        String DATA = Operation.add33H(Operation.HtoL(D3_0.substring(0,8)))+Operation.add33H(Operation.HtoL(Nm_1));

        String str = "68"+Operation.HtoL(address)+"68"+"91"+Operation.ByteToHex((byte) (DATA.length()/2))+DATA;
        String CS = Operation.getCs(str);

        return str+CS+"16";
    }

    //控制码为1C 调用此方法  跳闸、合闸允许、报警、报警解除、保电、保电解除
    private String trip(String receive, Map map, String D3_0){

        logger.info("跳闸、合闸允许、报警、报警解除、保电、保电解除"+"D3_0"+D3_0);

        String N1 = receive.substring(36,38);

//        switch (N1){
//            case "1A":
//                //跳闸
//            case "1B":
//                //合闸允许
//            case "2A":
//                //报警
//            case "2B":
//                //报警解除
//            case "3A":
//                //保电
//            case "3B":
//                //保电解除
//        }

        String address = StringUtils.clean((String)map.get("04000401"));
        String C = "9C";
        String L = "00";
        String str = "68"+Operation.HtoL(address)+"68"+C+L;
        String CS = Operation.getCs(str);

        return "FEFEFEFE"+str+CS+"16";
    }


}
