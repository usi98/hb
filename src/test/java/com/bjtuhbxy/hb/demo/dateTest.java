package com.bjtuhbxy.hb.demo;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class dateTest {
    public static void main(String[] args) {
        Date date = new Date(System.currentTimeMillis()+ 24*60*60*1000);


        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");

        System.out.println(sdf.format(date));
    }
}
