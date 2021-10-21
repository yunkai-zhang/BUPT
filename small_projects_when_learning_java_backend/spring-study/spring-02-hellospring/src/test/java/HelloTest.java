import com.zhangyk.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloTest {
    public static void main(String[] args) {

        //实例化容器：获取Spring的上下文对象，即拿到spring的容器
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //容器在手，天下我有。我们的对象现在都在Spring中管理了，我们要使用，直接去里面取就可以。不需要new。
        Hello hello = (Hello)context.getBean("hello");
        System.out.println(hello.toString());
    }
}
