package com.bjtuhbxy.hb;

import com.bjtuhbxy.hb.entity.User;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HbApplication.class)
public class JPATest {

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
}
