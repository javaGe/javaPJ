import com.ggf.ssm.mapper.UserMapper;
import com.ggf.ssm.pojo.User;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by PanYuanJin on 2017/10/10.
 */
public class TestUser {
    public static Logger logger = Logger.getLogger(TestUser.class);
    @Test
    public void test_1() {
        //加载配置文件
        String[] config = {"spring/applicationContext.xml","spring/springmvc-config.xml"};
        //获取上下文对象
        AbstractApplicationContext ac = new ClassPathXmlApplicationContext(config);
        System.out.println(ac);
        UserMapper userMapper = ac.getBean(UserMapper.class);
        List<User> users = userMapper.findAll();
        for(User user : users) {
            System.out.println(user.getAdderss());
        }
    }
}
