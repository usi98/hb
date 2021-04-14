package com.bjtuhbxy.hb;

import com.alibaba.fastjson.JSON;
import com.bjtuhbxy.hb.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HbApplication.class)
public class RedisTest {

    @Autowired
    RedisService redisService;
    @Test
    public void redisAddTest(){
        String str = "[\n" +
                "        {\n" +
                "        value: '1',\n" +
                "        label: '1层',\n" +
                "        children: [{\n" +
                "          value: '101',\n" +
                "          label: '101'\n" +
                "        }, {\n" +
                "          value: '102',\n" +
                "          label: '102'\n" +
                "        }, {\n" +
                "          value: '103',\n" +
                "          label: '103'\n" +
                "        }, {\n" +
                "          value: '104',\n" +
                "          label: '104'\n" +
                "        },\n" +
                "        {\n" +
                "          value: '105',\n" +
                "          label: '105'\n" +
                "        }, {\n" +
                "          value: '106',\n" +
                "          label: '106'\n" +
                "        }, {\n" +
                "          value: '107',\n" +
                "          label: '107'\n" +
                "        }, {\n" +
                "          value: '108',\n" +
                "          label: '108'\n" +
                "        }, {\n" +
                "          value: '109',\n" +
                "          label: '109'\n" +
                "        }, {\n" +
                "          value: '110',\n" +
                "          label: '110'\n" +
                "        }]\n" +
                "      }, {\n" +
                "        value: '2',\n" +
                "        label: '2层',\n" +
                "        children: [{\n" +
                "          value: '201',\n" +
                "          label: '201'\n" +
                "        }, {\n" +
                "          value: '202',\n" +
                "          label: '202'\n" +
                "        }, {\n" +
                "          value: '203',\n" +
                "          label: '203'\n" +
                "        }, {\n" +
                "          value: '204',\n" +
                "          label: '204'\n" +
                "        }, {\n" +
                "          value: '205',\n" +
                "          label: '205'\n" +
                "        }, {\n" +
                "          value: '206',\n" +
                "          label: '206'\n" +
                "        }, {\n" +
                "          value: '207',\n" +
                "          label: '207'\n" +
                "        }, {\n" +
                "          value: '208',\n" +
                "          label: '208'\n" +
                "        }, {\n" +
                "          value: '209',\n" +
                "          label: '209'\n" +
                "        }, {\n" +
                "          value: '210',\n" +
                "          label: '210'\n" +
                "        }]\n" +
                "      }, {\n" +
                "        value: '3',\n" +
                "        label: '3层',\n" +
                "        children: [{\n" +
                "          value: '301',\n" +
                "          label: '301'\n" +
                "        }, {\n" +
                "          value: '302',\n" +
                "          label: '302'\n" +
                "        }, {\n" +
                "          value: '303',\n" +
                "          label: '303'\n" +
                "        }, {\n" +
                "          value: '304',\n" +
                "          label: '304'\n" +
                "        }, {\n" +
                "          value: '305',\n" +
                "          label: '305'\n" +
                "        }, {\n" +
                "          value: '306',\n" +
                "          label: '306'\n" +
                "        }, {\n" +
                "          value: '307',\n" +
                "          label: '307'\n" +
                "        }, {\n" +
                "          value: '308',\n" +
                "          label: '308'\n" +
                "        }, {\n" +
                "          value: '309',\n" +
                "          label: '309'\n" +
                "        }, {\n" +
                "          value: '310',\n" +
                "          label: '310'\n" +
                "        }]\n" +
                "      }, {\n" +
                "        value: '4',\n" +
                "        label: '4层',\n" +
                "        children: [{\n" +
                "          value: '401',\n" +
                "          label: '401'\n" +
                "        }, {\n" +
                "          value: '402',\n" +
                "          label: '402'\n" +
                "        }, {\n" +
                "          value: '403',\n" +
                "          label: '403'\n" +
                "        }, {\n" +
                "          value: '404',\n" +
                "          label: '404'\n" +
                "        }, {\n" +
                "          value: '405',\n" +
                "          label: '405'\n" +
                "        }, {\n" +
                "          value: '406',\n" +
                "          label: '406'\n" +
                "        }, {\n" +
                "          value: '407',\n" +
                "          label: '407'\n" +
                "        }, {\n" +
                "          value: '408',\n" +
                "          label: '408'\n" +
                "        }, {\n" +
                "          value: '409',\n" +
                "          label: '409'\n" +
                "        }, {\n" +
                "          value: '410',\n" +
                "          label: '410'\n" +
                "        }]\n" +
                "      }, {\n" +
                "        value: '5',\n" +
                "        label: '5层',\n" +
                "        children: [{\n" +
                "          value: '501',\n" +
                "          label: '501'\n" +
                "        }, {\n" +
                "          value: '502',\n" +
                "          label: '502'\n" +
                "        }, {\n" +
                "          value: '503',\n" +
                "          label: '503'\n" +
                "        }, {\n" +
                "          value: '504',\n" +
                "          label: '504'\n" +
                "        }, {\n" +
                "          value: '505',\n" +
                "          label: '505'\n" +
                "        }, {\n" +
                "          value: '506',\n" +
                "          label: '506'\n" +
                "        }, {\n" +
                "          value: '507',\n" +
                "          label: '507'\n" +
                "        }, {\n" +
                "          value: '508',\n" +
                "          label: '508'\n" +
                "        }, {\n" +
                "          value: '509',\n" +
                "          label: '509'\n" +
                "        }, {\n" +
                "          value: '510',\n" +
                "          label: '510'\n" +
                "        }]\n" +
                "      }, {\n" +
                "        value: '6',\n" +
                "        label: '6层',\n" +
                "        children: [{\n" +
                "          value: '601',\n" +
                "          label: '601'\n" +
                "        }, {\n" +
                "          value: '602',\n" +
                "          label: '602'\n" +
                "        }, {\n" +
                "          value: '603',\n" +
                "          label: '603'\n" +
                "        }, {\n" +
                "          value: '604',\n" +
                "          label: '604'\n" +
                "        }, {\n" +
                "          value: '605',\n" +
                "          label: '605'\n" +
                "        }, {\n" +
                "          value: '606',\n" +
                "          label: '606'\n" +
                "        }, {\n" +
                "          value: '607',\n" +
                "          label: '607'\n" +
                "        }, {\n" +
                "          value: '608',\n" +
                "          label: '608'\n" +
                "        }, {\n" +
                "          value: '609',\n" +
                "          label: '609'\n" +
                "        }, {\n" +
                "          value: '610',\n" +
                "          label: '610'\n" +
                "        }]\n" +
                "      }]";
        redisService.set("room_list", JSON.parse(str));
    }
    @Test
    public void redisGetTest(){
        redisService.get("a:a1");
    }



}
