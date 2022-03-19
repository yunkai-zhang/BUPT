package com.zhangyun.syn;

/**
 * 不安全的取钱
 */
public class UnsafeBank {

    public static void main(String[] args) {
        Account account = new Account(100, "结婚基金");
        Drawing you = new Drawing(account, 50, "展堂");
        Drawing girlfriend = new Drawing(account, 100, "sad");
        you.start();
        girlfriend.start();
    }
}

//账户
class Account {
    int money;//余额
    String cardName;//卡名

    public Account(int money, String cardName) {
        this.money = money;
        this.cardName = cardName;
    }
}

//银行:模拟取款
class Drawing extends Thread {
    Account account;//账户
    int drawingMoney;//取金额
    int nowMoney;//你手里的钱

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    //取钱
    @Override
    public void run() {
        //判断是否有钱
        if (account.money - drawingMoney < 0) {
            System.out.println(Thread.currentThread().getName() + "余额不足,不能进行取钱");
            return;
        }
        try {
            //放大并发问题的发生性；用1s的延时，让先进入临界区的线程等待一下，让you和gf都进入临界区
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //卡内金额 = 余额-你的钱
        account.money = account.money - drawingMoney;
        //你手里的钱
        nowMoney = nowMoney + drawingMoney;
        System.out.println(account.cardName + "余额为:" + account.money);
        //this.getName()==Thread.currentThread().getName()
        //网友：用this.getname是因为本类继承了thread类，thread类有getname方法，所以本类可以直接用getname方法；没重写就不用super。
        System.out.println(this.getName() + "手里的钱:" + nowMoney);
    }
}
