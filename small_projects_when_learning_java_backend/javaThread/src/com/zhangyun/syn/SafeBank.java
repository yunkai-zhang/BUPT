package com.zhangyun.syn;

/**
 * 安全的取钱 同步块
 */
public class SafeBank {
    public static void main(String[] args) {
        Account1 account = new Account1(100, "结婚基金");
        Drawing1 you = new Drawing1(account, 50, "展堂");
        Drawing1 girlfriend = new Drawing1(account, 100, "sad");
        you.start();
        girlfriend.start();
    }
}

//账户
class Account1 {
    int money;//余额
    String cardName;//卡名

    public Account1(int money, String cardName) {
        this.money = money;
        this.cardName = cardName;
    }
}

//银行:模拟取款
class Drawing1 extends Thread {
    Account1 account;//账户
    int drawingMoney;//取金额
    int nowMoney;//你手里的钱

    public Drawing1(Account1 account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    //取钱
    @Override
    public void run() {
        //锁的对象就是变量的量,需要增删改查的对象
        synchronized (account) {
            //判断是否有钱
            if (account.money - drawingMoney < 0) {
                System.out.println(Thread.currentThread().getName() + "余额不足,不能进行取钱");
                return;
            }
            try {
                Thread.sleep(1000);//放大问题的发生性
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //卡内金额 = 余额-你的钱
            account.money = account.money - drawingMoney;
            //你手里的钱
            nowMoney = nowMoney + drawingMoney;
            System.out.println(account.cardName + "余额为:" + account.money);
            //this.getName()==Thread.currentThread().getName()
            System.out.println(this.getName() + "手里的钱:" + nowMoney);
        }
    }
}

