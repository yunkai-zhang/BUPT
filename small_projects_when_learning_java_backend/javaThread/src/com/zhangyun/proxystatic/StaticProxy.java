package com.zhangyun.proxystatic;
/**
 * 静态代理:结婚案例
 */
public class StaticProxy {


        public static void main(String[] args) {
            WeddingCompany weddingCompany = new WeddingCompany(new You());
            weddingCompany.happyMarry();
        }
}

//结婚
interface Marry {
    void happyMarry();
}

//真实角色:你去结婚
class You implements Marry {
    @Override
    public void happyMarry() {
        System.out.println("张三要结婚了,超开心");
    }
}

//代理角色:帮助你结婚
class WeddingCompany implements Marry {
    private Marry target;//代理-->真实目标角色角色,帮谁结婚

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        after();
        this.target.happyMarry();
        before();
    }

    private void after() {
        System.out.println("结婚之前,布置现场");
    }

    private void before() {
        System.out.println("结婚之后,付尾款");
    }

}
