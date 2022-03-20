public class Student {
    //重写object类的toString方法；Student类肯定是Object类的子类吗，所以可以重写
    @Override
    public String toString(){
        return "hello student";
    }

    public static void main(String[] args) {
        Student student = new Student();

        /*
        * 能输出hello的原因：
        * 1. 运行new Student()，会首先去跟加载器中找；rt.jar包中没有Student类，所以根加载器中找不到Student类。（尽量不要修改RT.jar包，容易导致整个jRE崩溃；比如把RT.jar包中的String类改成自定义的Stirng类）
        * 2. 根加载器中找不到，jvm就会到扩展类加载器中找Student类；由于jdk/kre/lib/ext中没有包含Student类的jar包，所以扩展类加载器找不到Student类
        * 3. 发现扩展类加载器和根加载器都没有Student类，就会执行当前应用程序加载器，即加载自定义的Student类。
        * */
        System.out.println(student.toString());
        //打印对象student的加载器，即可知道对象student对应的类Student是从哪个类加载器出来的
        System.out.println(student.getClass().getClassLoader());
    }
}
