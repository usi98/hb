package com.bjtuhbxy.hb.dao;

import com.bjtuhbxy.hb.HbApplication;
import com.bjtuhbxy.hb.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HbApplication.class)// 就是你springboot的启动类
public class UserDaoTest {

    @Autowired
    UserDAO userDAO;

    @Test
    public void userDaoTest(){
        userDAO.findByUsername("test");
    }


    @Test
    public void addTest(){
        //调用方法 把学生放进宿舍里面
        String names="越茹雪红灵秋森安柏犁梓榆谏寻巧敖芷文零婉静友诗兰朋绿竹戏秀丽阳桐欣行秀英罗傲松余芮波野三姗遇水风玉楠楠仝玲珑郏怀莲锁华乐邴代蓝稽香桃奇白亦天翠芙城听筠虞新蕾逢香洁邢霁芸丑怀曼严安双速天真宝凌波闪芷荷函以筠丁丽姿连语蓉雷天曼邰文敏柳丽华抄沈思鲁晓燕禚初南贲春荷岳元蝶北香巧赫易巧酒惜雪揭苇然卫芷烟甄半青杜雨兰京靓影乐思萱欧荏苒力天巧兴绮琴别冰莹剑之卉田靖易佼晓曼户顺美官雅惠吕依云仆依白郗晨曦凌念露漆雨凝薄叶春祝姗姗冀又夏周秋柔郜启颜蒯晓彤盘夜雪奕馨兰藤安露植冷雁区安春亢沛萍磨思松冼醉柳忻文君称紫云尉夏波文鹤梦佛以晴厚安卉夔代双葛绿海夙天青殳欣然滕幻儿常尔丝家依柔愚冬易庆珠佩步怜南芒新之童盼丹能馨欣郸可儿屠丰雅佟璇娟肖依丝孙琪华奈白萱由若华慕南露平曼雁毓莘莘悉依瑶铁梦琪刑布侬骆依丝次南霜祢瑛瑶斯嘉禾斋安双洛凝丝焉晓昕勇芷琪卢问薇凤香天随惜文台安容奚丽芳史映真后天籁睦长钰庾佳文管杉月错忆雪旷若山夕灵雨熊贝莉类孤菱烟菀柳果思莹甫晴画宜玉兰危涵涵通雁丝虎晨欣景迎曼谭香桃鹿半香臧依薇答寄真司佳妍权瑞彩宛盼夏言冰蓝茹从蓉冠芷波梅友卉利文惠农悦欣弥寄蕾章书艺斛蕴和荤清悦环尔真蛮菊月武雅柔昔水悦高瑜蓓谬芷文宾雪枫郁佁然德韶敏夏书文线新瑶卜霓云求清霁郁美偲梁红旭施念珍咎寻雪项松雪兆清妍泷涵菱空梦槐建小晨裴秋白盖书易犹恬然岑从筠孔菲菲爱馨欣候飞烟频怀玉郎新语万涵阳赏访烟冒柔蔓义书兰全佳晨摩尔琴英和悌华春华谌岚风茂沛白孛秋柳东又亦理忆梅竹依珊箕彤雯励琼华展采梦种雪曼顿觅丹伟新林牟夏真衣映秋都梓颖池代桃韩琼芳苏洁静海雪绿庚诗蕊穆秀曼阙逸丽赵梓柔隆水凡受颖初何向晨度思卉续泽恩苌冬亦朱海伦董冷萱黎寒凝六半梅胡初珍其夜春巧叶吉聂戈雅宁紫雪书若山莫河灵单怡宁";
        System.out.println("length:"+names.length());
        List<String> nlist = new ArrayList<>();
        int index=0;
        for (int i = 0;i<=240;i++){
            nlist.add(i,names.substring(index,index+3));
            index+=3;
        }
        System.out.println(nlist.get(62));
//        //18级男生 170001-170240
//        addStoBuild(200001,7,nlist);

        //20级女生 200241-200480
        addStoBuild(200241,8,nlist);

    }
    public void addStoBuild(int sno,int build,List<String> nList){
        int username=sno;
        int nameindex=0;
        User student = new User();
        //i 是楼层号码
        for (int i = 1; i <= 6; i++) {
            int r = i*100+1;
            for (int roomid = r; roomid <= r + 9; roomid++) {

                //宿舍成员添加
                for (int l = 1; l <= 4; l++) {
                    student.setId(0);
                    student.setUsername(String.valueOf(username));
                    username++;

                    student.setSalt("ThWHqcFcEyoOtU8qIH4/hw==");
                    student.setPassword("6405ab611603a989e7c398b40e27be54");
                    student.setEnabled(1);
                    student.setName(nList.get(nameindex));
                    nameindex++;

                    student.setRoomId(roomid);
                    student.setBuildingId(build);
                    userDAO.save(student);
                }
            }
        }
    }

}
