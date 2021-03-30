package com.bjtuhbxy.hb.controller.admin.parameter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bjtuhbxy.hb.controller.admin.NoticeController;
import com.bjtuhbxy.hb.entity.Room;
import com.bjtuhbxy.hb.result.Result;
import com.bjtuhbxy.hb.result.ResultFactory;
import com.bjtuhbxy.hb.service.ArticleService;
import com.bjtuhbxy.hb.service.RoomService;
import com.bjtuhbxy.hb.util.MyPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@ResponseBody
@Controller
public class PowerController {
    Logger logger = LoggerFactory.getLogger(PowerController.class);

    @Autowired
    RoomService roomService;

    @CrossOrigin
    @PostMapping(value = "api/updatePowerAll")
    public Result updatePowerAll(@RequestBody String reqJson){

        JSONObject json=JSON.parseObject(reqJson);
        int power = json.getIntValue("power");
        logger.info("更新："+"api/updatePowerAll");
        Integer num =  roomService.updatePowerMaxAll(power);
        return ResultFactory.buildSuccessResult(num);

    }


    @CrossOrigin
    @PostMapping(value = "api/updatePower")
    public Result updatePower(@RequestBody String reqJson){

        JSONObject json=JSON.parseObject(reqJson);
        int power = json.getIntValue("power");
        int bid = json.getIntValue("buildId");
        int rid = json.getIntValue("roomId");
        logger.info("更新："+"api/updatePower");
        Integer num =  roomService.updatePower(power,bid,rid);
        return ResultFactory.buildSuccessResult(num);

    }

    @CrossOrigin
    @GetMapping("/api/powerInfo/{size}/{page}")
    public Result listRooms(@PathVariable("size") int size, @PathVariable("page") int page) {


        MyPage myPage = roomService.list(page - 1, size);
//        logger.info("Rooms myPageJSON:{}", JSON.toJSONString(myPage));
//        logger.info("Rooms RESULTJSON:{}", ResultFactory.buildSuccessResult(myPage).getResult());

        return ResultFactory.buildSuccessResult(myPage);
    }
}
