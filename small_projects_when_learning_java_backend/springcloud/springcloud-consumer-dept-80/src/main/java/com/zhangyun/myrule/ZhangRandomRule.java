package com.zhangyun.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
//import edu.umd.cs.findbugs.annotations.SuppressWarnings;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/*
* 自定义为：每个服务访问五次后换下一个服务。（三个服务，要实现回头，大于3置零）
*
* 指针total默认为0，如果=5，指向下一个服务节点。
* index标记节点，如果total=5则index+1；total等于3的时候要置零。
* */
public class ZhangRandomRule extends AbstractLoadBalancerRule {
    public ZhangRandomRule() {
    }

    //当前节点已被调用的次数
    private int total=0;
    //当前是哪个节点在提供服务
    private int currentIndex=0;

    @SuppressWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"})
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                //获得还活着的服务
                List<Server> upList = lb.getReachableServers();
                //获得全部的服务
                List<Server> allList = lb.getAllServers();
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }
/*======================================自定义访问算法==========================================
* 算法有缺陷，因为upList.get的获取在一头一尾；三个服务阶段用完置零的时候，total=0,currindex=i会被打印两次，服务也被用两次。
* 解决方案就是else中发现循环完毕后getcurrent直接为0，再total++，那么走到total<5就使用的是total=1，不会重复total=0了
* */
//                //获得区间随机数
//                int index = this.chooseRandomInt(serverCount);
//                //从活着的服务列表中随机获取一个服务
//                server = (Server)upList.get(index);
                if(total<5){
                    System.out.println("total="+total+",currindex="+currentIndex);
                    server=upList.get(currentIndex);
                    total++;
                }else{
                    total=0;
                    currentIndex++;
                    if(currentIndex>=upList.size()){
                        currentIndex=0;
                    }
                    System.out.println("total="+total+",currindex="+currentIndex);
                    server=upList.get(currentIndex);
                }
/*======================================自定义访问算法==========================================*/

                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
