package com.bjtuhbxy.hb.controller.search;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjtuhbxy.hb.entity.Params;
import com.bjtuhbxy.hb.redis.RedisService;
import com.bjtuhbxy.hb.result.Result;
import com.bjtuhbxy.hb.result.ResultFactory;
import com.bjtuhbxy.hb.service.OperateLogService;
import com.bjtuhbxy.hb.service.ParamsService;
import com.bjtuhbxy.hb.service.RoomService;
import com.bjtuhbxy.hb.service.ViolationLogService;
import com.bjtuhbxy.hb.util.MyPage;
import jdk.nashorn.internal.runtime.JSONListAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ResponseBody
@Controller
public class SearchController {
    Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    OperateLogService operateLogService;

    @Autowired
    ViolationLogService violationLogService;

    @Autowired
    ParamsService paramsService;

    @Autowired
    RedisService redisService;

    @CrossOrigin
    @GetMapping("/api/roomlist")
    public JSONArray listRooms() {
        return (JSONArray) redisService.get("room_list");
    }


    @CrossOrigin
    @GetMapping("api/loadOperateLog/{size}/{page}")
    @ResponseBody
    public Result loadOperateLog(@PathVariable("size") int size,@PathVariable("page") int page) {
        logger.info("loadOperateLog : {},{}", size, page);
        MyPage myPage = operateLogService.list(page-1, size);
        return ResultFactory.buildSuccessResult(myPage);
    }

    @CrossOrigin
    @GetMapping("api/loadViolationLog/{size}/{page}")
    @ResponseBody
    public Result loadViolationLog(@PathVariable("size") int size,@PathVariable("page") int page) {
        logger.info("loadViolationLog : {},{}", size, page);
        MyPage myPage = violationLogService.list(page-1, size);
        return ResultFactory.buildSuccessResult(myPage);
    }

    @CrossOrigin
    @GetMapping("api/vacation")
    @ResponseBody
    public Result loadvacation() {

        Params sparams = paramsService.getparamByName("startday");
        Params eparams = paramsService.getparamByName("endday");
        Map<String, String> map = new HashMap<>();
        map.put("start", sparams.getValue1());
        map.put("end", eparams.getValue1());
        return ResultFactory.buildSuccessResult(map);
    }

}
