package com.bjtuhbxy.hb.controller.search;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bjtuhbxy.hb.entity.Params;
import com.bjtuhbxy.hb.entity.Room;
import com.bjtuhbxy.hb.entity.User;
import com.bjtuhbxy.hb.result.Result;
import com.bjtuhbxy.hb.result.ResultFactory;
import com.bjtuhbxy.hb.service.ParamsService;
import com.bjtuhbxy.hb.service.RoomService;
import com.bjtuhbxy.hb.service.UserService;
import com.bjtuhbxy.hb.util.MyPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class RoomInfoController {
    Logger logger = LoggerFactory.getLogger(RoomInfoController.class);

    @Autowired
    RoomService roomService;

    @Autowired
    UserService userService;

    @Autowired
    ParamsService paramsService;

    @CrossOrigin
    @GetMapping("/api/roomInfo/{size}/{page}")
    public Result listRooms(@PathVariable("size") int size, @PathVariable("page") int page) {
        MyPage myPage = roomService.listWithSInfo(page - 1, size);
        //logger.info("Rooms listRooms JSON:{}", JSON.toJSONString(myPage));
        return ResultFactory.buildSuccessResult(myPage);
    }

    @CrossOrigin
    @GetMapping("/api/roomInfo/{size}/{page}/{state}/{bid}")
    public Result listRoomsBy(@PathVariable("size") int size, @PathVariable("page") int page, @PathVariable("state") String state, @PathVariable("bid") String bid) {
        //带条件的分页查询
        MyPage myPage = roomService.listWithSInfo(page - 1, size, state, bid);
        return ResultFactory.buildSuccessResult(myPage);
    }

    @CrossOrigin
    @PostMapping("/api/changedate")
    public Result changedate(@RequestBody String reqJson) {

        JSONObject jsonObject= JSON.parseObject(reqJson);
        Params sparams = paramsService.getparamByName("startday");
        sparams.setValue1(jsonObject.getString("start"));
        paramsService.save(sparams);

        Params eparams = paramsService.getparamByName("endday");
        eparams.setValue1(jsonObject.getString("end"));
        eparams = paramsService.save(eparams);
        return ResultFactory.buildSuccessResult("success");
    }

    @CrossOrigin
    @PostMapping("/api/updateUserInfo")
    public Result updateUserInfo(@RequestBody String reqJson) {
        JSONObject jsonObject= JSON.parseObject(reqJson);

        Integer id = jsonObject.getInteger("id");
        Integer bid = jsonObject.getInteger("bid");
        Integer rid = jsonObject.getInteger("rid");
        String username = jsonObject.getString("username");
        String name = jsonObject.getString("name");
        User user = userService.getById(id);
        user.setBuildingId(bid);
        user.setRoomId(rid);
        user.setUsername(username);
        user.setName(name);
        userService.save(user);

        return ResultFactory.buildSuccessResult("updateUserInfo success");
    }

    @CrossOrigin
    @GetMapping("/api/circular")
    public Result circular() {

        Map<String,Integer> map = roomService.getPowerStatus();

//        Map<String, String> m = new HashMap<>();
//        m.put("ups", map.get("ups").toString());
//        m.put("offs", map.get("offs").toString());
        return ResultFactory.buildSuccessResult(map);
    }
}

