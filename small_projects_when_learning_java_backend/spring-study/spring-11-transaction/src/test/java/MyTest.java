import com.zhangyk.mapper.UserMapperImpl;
import com.zhangyk.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapperImpl userMapper = context.getBean("userMapper", UserMapperImpl.class);
        List<User> users = userMapper.selectUser();

        for (User user : users) {
            System.out.println(user);
        }
    }
}
