package com.bjtuhbxy.hb.serial;

import com.alibaba.fastjson.JSON;
import com.bjtuhbxy.hb.service.MeterService;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Scanner;

public class CommUtil implements SerialPortEventListener {
    Logger logger = LoggerFactory.getLogger(CommUtil.class);

    private String recivenew = "";
    private static final String PORT_NAME = "COM3";
    private static final int BIT_RATE = 2400;
    public static final int DATA_BITS = SerialPort.DATABITS_8;
    public static final int STOP_BIT = SerialPort.STOPBITS_1;
    public static final int PARITY_BIT = SerialPort.PARITY_NONE;

    private static SerialPort serialPort;
    private static InputStream in;
    private static OutputStream out;
    private static CommUtil commUtil;

    private CommUtil(){}

    public static CommUtil getInstance(){
        if(commUtil==null){
            commUtil = new CommUtil();
            commUtil.init();
        }
        return commUtil;
    }

    public void init(){
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
                logger.info("从站应答：{}",recive);
                recivenew = recive;
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

}