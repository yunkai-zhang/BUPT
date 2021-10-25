package com.zhangyun.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZhangRuleConfig {

    /*
     * IRule:通过这个接口去实现自定义负载均衡。这个接口有很多实现类，可以看看实现类来照猫画虎。
     * RoundRobinRule：轮询策略（默认）
     * RandomRule：随机策略
     * AvailabilityFilteringRule ： 会先过滤掉跳闸的or访问故障的服务~，对剩下的进行轮询~。本质还是轮询，只不过优化了。
     * RetryRule ： 重试；会先按照轮询获取服务~，如果服务获取失败，则会在指定的时间内进行。
     * */
    //使用自定义负载均衡规则ZhangRandomRule。@Bean把配置类注入到spring容器中。
    @Bean
    public IRule myRule(){
        return new ZhangRandomRule();
    }

}
