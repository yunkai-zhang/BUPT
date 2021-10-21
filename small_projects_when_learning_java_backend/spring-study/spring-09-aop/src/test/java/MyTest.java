import com.zhangyk.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    public static void main(String[] args) {
        //Spring测试三部曲：下面三行即为三步

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        /*
        * 【注意】动态代理代理的是接口，而不是接口的实现类。
        * 所以写成UserServiceImpl userService = context.getBean("userService", UserServiceImpl.class);会报错
        * */
        UserService userService = context.getBean("userService", UserService.class);

        userService.add();
    }
}
