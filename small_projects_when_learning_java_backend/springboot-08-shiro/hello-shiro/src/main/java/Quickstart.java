import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
//import org.apache.shiro.ini.IniSecurityManagerFactory;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
//import org.apache.shiro.lang.util.Factory;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Simple Quickstart application showing how to use Shiro's API.
 *
 * @since 0.9 RC2
 */
public class Quickstart {
    //使用日志门面输出，用sout也能输出，但是效果没有用日志这么好。
    private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);


    public static void main(String[] args) {

        /*
        * 看到factory想到工厂模式。
        *
        * IniSecurityManagerFactory工厂读取ini文件中的配置信息，并返回一个SecurityManager实例。
        *
        * 下面两行不算重要代码，因为这是写死的。之前学jdbc mybatis也有类似写死的代码行。
        * */
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        /*
        * SecurityManager使成为单例模式
        *
        * 这行代码也是写死的。
        * */
        SecurityUtils.setSecurityManager(securityManager);

        /***以上是设置Shiro environment完毕，接下来看看我们能用shiro做什么实际的事***/

        /*
        * ！！！获取当前用户对象，对象叫做subject
        * */
        Subject currentUser = SecurityUtils.getSubject();

        /*
        * ！！！下面第一行：通过当前用户对象拿到session（不需要web或EJB容器）
        *
        * 这不是http的session，这是shiro的session
        *
        * 下面六行代码讲了subject如何通过shiro自带的session存值和取值
        * */
        Session session = currentUser.getSession();
        session.setAttribute("someKey", "aValue");
        String value = (String) session.getAttribute("someKey");
        if (value.equals("aValue")) {
            log.info("Retrieved the correct value! [" + value + "]");
        }

        /*
        * ！！！判断当前用户是否被认证
        * */
        if (!currentUser.isAuthenticated()) {
            //通过用户的账号和密码生成一个令牌。这个用户名和密码在shiro.ini中有配置。
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            //设置“记住我”
            token.setRememberMe(true);
            try {
                //执行了登录的操作。看不到细节，点进源码也什么都看不到。
                currentUser.login(token);
            }
            //异常：用户不存在时
            catch (UnknownAccountException uae) {
                log.info("There is no user with username of " + token.getPrincipal());
            }
            //异常：用户密码不对时
            catch (IncorrectCredentialsException ice) {
                log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            }
            //异常：用户被冻结时。比如五次密码都不对，就可以冻结该用户。
            catch (LockedAccountException lae) {
                log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
            }
            //可以连续添加更多的异常
            //在最后这捕捉没预料到的异常。UnknownAccountException,IncorrectCredentialsException等都是AuthenticationException的子类。
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            }
        }

        //！！！打印认证信息，展示谁登录了。在本例中，认证信息为username，即打印username。
        log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");

        //！！！测试当前用户有什么角色
        if (currentUser.hasRole("schwartz")) {
            log.info("May the Schwartz be with you!");
        } else {
            log.info("Hello, mere mortal.");
        }

        /*
        * ！！！粗粒度的行为许可测试
        *
        * 测试当前用户能有什么行为，不同role可以有不同的行为。本例测试当前用户有没有lightsaber:wield的行为。
        * */
        if (currentUser.isPermitted("lightsaber:wield")) {
            log.info("You may use a lightsaber ring.  Use it wisely.");
        } else {
            log.info("Sorry, lightsaber rings are for schwartz masters only.");
        }

        /*
        * ！！！细粒度的行为许可测试
        * */
        if (currentUser.isPermitted("winnebago:drive:eagle5")) {
            log.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                    "Here are the keys - have fun!");
        } else {
            log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        }

        //！！！登出
        currentUser.logout();

        //结束系统
        System.exit(0);
    }
}
