package com.bjtuhbxy.hb.controller.user;

import cn.hutool.json.JSONString;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bjtuhbxy.hb.entity.RechargeLog;
import com.bjtuhbxy.hb.entity.Room;
import com.bjtuhbxy.hb.entity.User;
import com.bjtuhbxy.hb.result.Result;
import com.bjtuhbxy.hb.result.ResultFactory;
import com.bjtuhbxy.hb.serial.CommUtil;
import com.bjtuhbxy.hb.serial.Operation;
import com.bjtuhbxy.hb.service.RechargeLogService;
import com.bjtuhbxy.hb.service.RoomService;
import com.bjtuhbxy.hb.service.UserService;
import com.bjtuhbxy.hb.util.MyPage;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    CommUtil commUtil;

    @Autowired
    UserService userService;

    @Autowired
    RoomService roomService;


    @Autowired
    RechargeLogService rechargeLogService;

    @CrossOrigin
    @PostMapping("api/user/searchUserInfo")
    @ResponseBody
    public Result searchInfo(@RequestBody User user) {
        User userinfo = userService.findByUsername(user.getUsername());
        return ResultFactory.buildSuccessResult(userinfo);
    }
    @CrossOrigin
    @PostMapping("api/user/saveUserInfo")
    @ResponseBody
    public Result saveInfo(@RequestBody User user) {
        User user1 = userService.getById(user.getId());
        user1.setName(user.getName());
        user1.setPhone(user.getPhone());
        user1.setEmail(user.getEmail());
        user1.setSex(user.getSex());
        user1.setDate(user.getDate());
        user1.setMajor(user.getMajor());
        user1.setClasses(user.getClasses());
        userService.save(user1);
        return ResultFactory.buildSuccessResult(user1);
    }

    @CrossOrigin
    @PostMapping("api/user/searchRoomInfo")
    @ResponseBody
    public Result searchRoomInfo(@RequestBody  User u) {
        String username = u.getUsername();
        User user = userService.findByUsername(username);
        int bid = user.getBuildingId();
        int rid = user.getRoomId();
        Room room  = roomService.getRoomAndStudentsInfo(bid, rid);

        List<Room> roomList = new ArrayList<>();
        roomList.add(room);
        logger.info("searchRoomInfo: username{} roominfo{}",username,JSON.toJSONString(roomList));
        return ResultFactory.buildSuccessResult(roomList);
    }

    @CrossOrigin
    @PostMapping("api/user/recharge")
    @ResponseBody
    public Result recharge(@RequestBody String jsonString) {
        logger.info("recharge : {}",jsonString);
        JSONObject jsonObject = JSON.parseObject(jsonString);
        RechargeLog log = new RechargeLog();
        BigDecimal money = new BigDecimal("0");
        String balance = jsonObject.getString("balance");
        if(StringUtils.isNotEmpty(balance)){
            money = jsonObject.getBigDecimal("balance");
        }

        Integer bid = jsonObject.getInteger("buildingId");
        Integer rid =  jsonObject.getInteger("roomId");
        Room room = roomService.getRoomInfo(bid ,rid);
        log.setMoney(money);
        money = money.add(room.getBalance());
        room.setBalance(money);
        roomService.save(room);

        log.setUsername(jsonObject.getString("username"));

        log.setBuildingId(jsonObject.getInteger("buildingId"));
        log.setRoomId(jsonObject.getInteger("roomId"));
        log.setDescription("充值电费成功，充值金额为："+log.getMoney()+",当前余额为："+money);
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.setDate(sdf.format(date));
        //保存日志 到数据库
        rechargeLogService.save(log);

        return ResultFactory.buildSuccessResult(money);
    }

    @CrossOrigin
    @GetMapping("api/user/loadRechargeInfo/{size}/{page}")
    @ResponseBody
    public Result loadRechargeInfo(@PathVariable("size") int size,@PathVariable("page") int page) {
        logger.info("loadRechargeInfo : {},{}", size, page);
        MyPage myPage = rechargeLogService.list(page-1, size);
        System.out.println(myPage.getContent());
        return ResultFactory.buildSuccessResult(myPage);
    }


    @CrossOrigin
    @GetMapping("api/user/com/{s}")
    @ResponseBody
    public Result com(@PathVariable("s") String s) {
//        String a = commUtil.send("FEFEFEFE6800000000111168110433333433D416");

        String read = Operation.read("000000000001","00900200");
        String a = commUtil.send("FEFEFEFE"+read);
        return ResultFactory.buildSuccessResult(a);
    }
}
