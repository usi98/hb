package com.bjtuhbxy.hb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bjtuhbxy.hb.entity.Meter;
import com.bjtuhbxy.hb.entity.Room;
import com.bjtuhbxy.hb.entity.User;
import com.bjtuhbxy.hb.service.MeterService;
import com.bjtuhbxy.hb.service.RoomService;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HbApplication.class)
public class JPATest {

    @Autowired
    RoomService roomService;

    @Autowired
    MeterService meterService;

    //生成EntityManger
    protected EntityManager em;

    @Test
    public void japTest() {


        String sql = "select * from user";
        //执行原生SQL
        Query nativeQuery = em.createNativeQuery(sql);
//指定返回对象类型
        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(User.class));
        //返回对象
        List<User> resultList = nativeQuery.getResultList();

        for (User u : resultList) {
            System.out.println(u.toString());

        }
    }


    @Test
    public void testNative(){

        Query query = em.createNativeQuery("select * from user limt 0,3");
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List rows = query.getResultList();
        for (Object obj : rows) {
            Map row = (Map) obj;
            System.out.println("id = " + row.get("ID"));
            System.out.println("name = " + row.get("NAME"));
            System.out.println("age = " + row.get("AGE"));
        }
    }

    @Test
    public void addMeterInfoTest(){

        String input = "\n" +
                "{\n" +
                "\t\"address\": \"000000000001\",\n" +
                "\n" +
                "\t\"04000401\": \"000000000001\",\n" +
                "\n" +
                "\t\"04000C01\": \"000000\",\n" +
                "\t\"04000C02\": \"000000\",\n" +
                "\t\"04000C03\": \"000000\",\n" +
                "\t\"04000C04\": \"000000\",\n" +
                "\t\"04000C05\": \"000000\",\n" +
                "\t\"04000C06\": \"000000\",\n" +
                "\t\"04000C07\": \"000000\",\n" +
                "\t\"04000C08\": \"000000\",\n" +
                "\t\"04000C09\": \"000000\",\n" +
                "\t\"04000C0A\": \"000000\",\n" +
                "\n" +
                "                 \n" +
                "    \"00900100\": \"00000000\",\n" +
                "    \"00900101\": \"00000000\",\n" +
                "    \"00900200\": \"00001500\",\n" +
                "\t\"00900201\": \"00000000\",\n" +
                "\t\"000B0000\": \"00010000\",\n" +
                "\t\"000B0001\": \"00009900\",\n" +
                "\t\n" +
                "\t\"0280000B\": \"00055000\",\n" +
                "\t\n" +
                "    \"03320101\": \"2104010000\",\n" +
                "    \"03320201\": \"55\",\n" +
                "\t\"03320301\": \"00006000\",\n" +
                "\t\"03320401\": \"00000300\",\n" +
                "    \"03320501\": \"00006000\",\n" +
                "\t\"03320601\": \"00900000\",\n" +
                "\n" +
                "\t\"00010000\": \"00000000\",\n" +
                "\t\n" +
                "    \"00010100\": \"00000000\",\n" +
                "\t\"00010200\": \"00000000\",\n" +
                "\t\"00010300\": \"00000143\",\n" +
                "\n" +
                "\t\"02010100\": \"2200\",\n" +
                "\t\"02010200\": \"2200\",\n" +
                "\t\"02010300\": \"2200\",\n" +
                "\t\"0201FF00\": \"2200\",\n" +
                "\n" +
                "\t\"02020100\": \"001000\",\n" +
                "\t\"02020200\": \"001000\",\n" +
                "\t\"02020300\": \"001000\",\n" +
                "\t\"0202FF00\": \"001000\",\n" +
                "\n" +
                "\t\"02030000\": \"053090\",\n" +
                "\t\"02030100\": \"021990\",\n" +
                "\t\"02030200\": \"015550\",\n" +
                "\t\"02030300\": \"015550\",\n" +
                "\t\n" +
                "\t\"04000E01\": \"010000\",\n" +
                "\t\"04000E02\": \"010000\",\n" +
                "\t\"04000E03\": \"2200\",\n" +
                "\t\"04000E04\": \"2200\",\n" +
                "\t\n" +
                "\n" +
                "\n" +
                "}";

        Integer num = 1000;


        List<Room> list = roomService.findAll();
        Map<String, String> map = (Map<String, String>) JSON.parse(input);
        for (Room room: list){
            num++;
            String no = "000000000"+num.toString().substring(1);


            map.put("address",no);
            map.put("04000401",no);
            room.setMeterAddress(no);
            room.setMeterInfo(JSON.toJSONString(map));
            roomService.save(room);
            Meter meter   = new Meter();
            meter.setAddress(no);
            meter.setInfo(JSON.toJSONString(map));
            meterService.save(meter);
        }



    }

}
