package com.zhangyk.demo01;

public class Proxy implements Rent{

    private Host host;

    public Proxy() {
    }

    public Proxy(Host host) {
        this.host = host;
    }

    public void rent() {
        //传给他的是哪个房东，他就调用哪个房东
        host.rent();
        seeHouse();
        signContract();
        fee();
    }

    //看房
    public void seeHouse(){
        System.out.println("中介带你看房");
    }

    //签租赁合同
    public void signContract(){
        System.out.println("签租赁合同");
    }

    //收中介费
    public void fee(){
        System.out.println("收中介费");
    }
}
