package com.bjtuhbxy.hb.serial;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class ComMeter implements SerialPortEventListener {

    Logger logger = LoggerFactory.getLogger(ComMeter.class);

    private final ReceiveUtil receiveUtil = new ReceiveUtil();
    private Map<String, String> datamap;

    private String recivenew = "";
    private static final String PORT_NAME = "COM1";
    private static final int BIT_RATE = 2400;
    public static final int DATA_BITS = SerialPort.DATABITS_8;
    public static final int STOP_BIT = SerialPort.STOPBITS_1;
    public static final int PARITY_BIT = SerialPort.PARITY_NONE;

    private static SerialPort serialPort;
    private static InputStream in;
    private static OutputStream out;
    private static ComMeter commUtil;

    private ComMeter(){}

    public static ComMeter getInstance(){
        if(commUtil==null){
            commUtil = new ComMeter();
            commUtil.init();
        }
        return commUtil;
    }

    public void init(){
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
        Map<String, String> map = (Map<String, String>) JSON.parse(input);
        datamap = map;
        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(PORT_NAME);
            if (portIdentifier.isCurrentlyOwned()){
                System.out.println("Error: Port is currently in use");
            }else if(portIdentifier.getPortType()==1){
                serialPort = (SerialPort) portIdentifier.open(PORT_NAME,1000);
                serialPort.setSerialPortParams(BIT_RATE,DATA_BITS,STOP_BIT,PARITY_BIT);

                in = serialPort.getInputStream();
                out = serialPort.getOutputStream();

                serialPort.addEventListener(this);
                serialPort.notifyOnDataAvailable(true);
            }else{
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void send(String message){
//        try {
//            byte[] bytes = hexStrToByteArray(message);
//            out.write(bytes);
//            Thread.sleep(1000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public String send(String message){
        try {
            byte[] bytes = hexStrToByteArray(message);
            out.write(bytes);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recivenew;
    }

    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()){
            case SerialPortEvent.DATA_AVAILABLE:
                String recive = receive();
                logger.info("电表接收：{}",recive);
                //电表接收
                recivenew = recive;
                send(receiveUtil.respond(recive,datamap));
                break;
        }
    }
    public String receive(){
        byte[] buffer = new byte[128];
        int data;
        String result = null;
        try{
            int len = 0;
            while ( ( data = in.read()) > -1 ){
                buffer[len++] = (byte) data;
            }
            byte[] copyValue = new byte[len];
            System.arraycopy(buffer,0,copyValue,0,len);
            result = ByteArrayToString(copyValue);
        }catch ( IOException e ){
            e.printStackTrace();
        }
        return result;
    }

    public void close(){
        try {
            in.close();
            out.close();
            serialPort.notifyOnDataAvailable(false);
            serialPort.removeEventListener();
            serialPort.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //16进制转byte数组
    public byte[] hexStrToByteArray(String str) {
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

    public String ByteArrayToString(byte[] by) {
        String str = "";
        for (int i = 0; i < by.length; i++) {
            String hex = Integer.toHexString(by[i] & 0xFF);
            if (hex.length() == 1) {
                hex = "0" + hex;
            }
            str += hex.toUpperCase();
        }
        return str;
    }


    public Map getdata(){
        return this.datamap;
    }

    public void setdata(Map map){
        this.datamap = map;
    }
}