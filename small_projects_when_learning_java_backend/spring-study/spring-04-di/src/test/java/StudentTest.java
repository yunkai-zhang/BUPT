import com.zhangyk.pojo.Student;
import com.zhangyk.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.annotation.Target;

public class StudentTest {

    public static void main(String[] args) {
        //记忆：CPX 擦皮鞋
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = context.getBean("student", Student.class);
        System.out.println(student.toString());
        /*
        * 测试结果：
        * Student{
        * name='张云',
        * address=Address{address='中国'},
        * books=[西游记, 三国演义, 红楼梦, 水浒传],
        * hobbies=[吃饭, 学习],
        * cards={身份证=362310, 银行卡=123456},
        * games=[王者, 吃鸡], wife='null',
        * info={学号=xuehao, password=123456, url=com.zhangyk, driver=indodb, username=zhangyk}
        * }
        * */
    }

    @Test
    public void testUser(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println("======================user======================");
        User user = context.getBean("user", User.class);
        System.out.println(user.toString());

        System.out.println("======================user2======================");
        User user2 = context.getBean("user2", User.class);
        System.out.println(user2.toString());
    }

    @Test
    public void testSingleton(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        User user = context.getBean("user", User.class);
        User user2 = context.getBean("user", User.class);
        System.out.println(user==user2);
    }


}
