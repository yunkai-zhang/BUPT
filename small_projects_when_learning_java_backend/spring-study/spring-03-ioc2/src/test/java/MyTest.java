import com.zhangyk.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        System.out.println("============自己new对象=============");
        User user = new User();

        System.out.println("============通过spring获取对象=============");
        /*读xml获取上下文的时候，bean就创建了。会调用User类的无参构造。
        创建好对象后，xml中的property标签给对象的各属性赋值，赋值使用的是各属性的set方法
        */
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //这一步根据getbean的输入，提取出创建好的对象
        User user1 = (User)context.getBean("userAlias");
        user1.show();
    }
}
