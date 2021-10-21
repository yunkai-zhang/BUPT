import com.zhangyk.config.ZhangConfig;
import com.zhangyk.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserTest {

    @Test
    public void test1(){
        ApplicationContext context = new AnnotationConfigApplicationContext(ZhangConfig.class);
        User user = context.getBean("user", User.class);

        System.out.println(user.toString());
    }
}
