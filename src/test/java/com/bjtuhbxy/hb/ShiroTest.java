package com.bjtuhbxy.hb;

import com.bjtuhbxy.hb.entity.User;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.util.ByteSource;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HbApplication.class)
public class ShiroTest {

////生成token
//    UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("username", "requestUser.getPassword()");
//   //获取验证信息
//    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("userName", "passwordInDB", ByteSource.Util.bytes("salt"), getName());
//
//    AuthenticationToken token = usernamePasswordToken;
//    String userName = token.getPrincipal().toString();
//    User user = userService.getByUserName(userName);
//    String passwordInDB = user.getPassword();
//    String salt = user.getSalt();
//


}


