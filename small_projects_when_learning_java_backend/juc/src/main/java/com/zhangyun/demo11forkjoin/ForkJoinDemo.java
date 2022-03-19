package com.zhangyun.demo11forkjoin;

import java.util.concurrent.RecursiveTask;

/*
* 求和计算的任务
* 工资定级与解决方案：3000 6000（ForkJoin） 9000（Stream并行流）
*
* 步骤：
* 1、forkjoinPooL通过它来执行
* 2、计算任务forkjoinPooL.execute(ForkJoinTask task)
* 3.计算类要继承ForkJoinTask
* */
public class ForkJoinDemo extends RecursiveTask<Long> {
    private long star;
    private long end;
    /** 临界值 */
    private long temp = 1000000L;

    //构造器
    public ForkJoinDemo(long star, long end) {
        this.star = star;
        this.end = end;
    }

    /**
     * 计算方法
     * @return
     */
    @Override
    protected Long compute() {
        if ((end - star) < temp) {//差值比较小就让他正常计算
            Long sum = 0L;
            for (Long i = star; i < end; i++) {
                sum += i;
            }
            return sum;
        }else {
            // 差值比较大，就使用ForkJoin 分而治之 计算
            //1 . 计算平均值。建议使用start+（end-start）/2防止溢出
            long middle = (star + end) / 2;

            //把一个任务拆成两个任务
            ForkJoinDemo forkJoinDemo1 = new ForkJoinDemo(star, middle);
            forkJoinDemo1.fork();// 把负责子任务的线程压入线程队列
            ForkJoinDemo forkJoinDemo2 = new ForkJoinDemo(middle, end);
            forkJoinDemo2.fork();// 把负责子任务的线程压入线程队列

            //把多个子结果规约成最终结果；像递归
            long taskSum = forkJoinDemo1.join() + forkJoinDemo2.join();
            return taskSum;
        }
    }
}

