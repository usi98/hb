package com.bjtuhbxy.hb.controller.search;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjtuhbxy.hb.redis.RedisService;
import com.bjtuhbxy.hb.result.Result;
import com.bjtuhbxy.hb.result.ResultFactory;
import com.bjtuhbxy.hb.service.RoomService;
import com.bjtuhbxy.hb.util.MyPage;
import jdk.nashorn.internal.runtime.JSONListAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@Controller
public class SearchController {
    Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    RedisService redisService;

    @CrossOrigin
    @GetMapping("/api/roomlist")
    public JSONArray listRooms() {

        return (JSONArray) redisService.get("room_list");

    }


}
