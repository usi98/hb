package com.bjtuhbxy.hb.config;

import com.bjtuhbxy.hb.serial.CommUtil;
import com.bjtuhbxy.hb.serial.ComMeter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommUtilConfig {
    @Bean
    public CommUtil commUtil(){
        return CommUtil.getInstance();
    }

    @Bean
    public ComMeter meter(){
        return ComMeter.getInstance();
    }
}
