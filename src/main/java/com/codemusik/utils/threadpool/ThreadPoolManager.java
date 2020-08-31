package com.codemusik.utils.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: thinkit
 * @date: 2020/4/14 10:09
 * @description: 线程池
 */
public class ThreadPoolManager {
    /**
     * 业务用线程池
     */
    private static volatile ThreadPoolExecutor businessThreadPool;

    private ThreadPoolManager(){}

    /**
     * 单例
     * 获取单线程的线程池
     * 无缓冲，阻塞
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor getBusinessThreadPool(){
        if(businessThreadPool == null){
            synchronized (ThreadPoolManager.class){
                if(businessThreadPool == null){
                    /*
                     * corePoolSize： 线程池核心线程数
                     * maximumPoolSize：线程池最大数
                     * keepAliveTime： 空闲线程存活时间
                     * unit： 时间单位
                     * workQueue： 线程池所使用的缓冲队列
                     * threadFactory：线程池创建线程使用的工厂(不传参数，使用默认)
                     * handler： 线程池对拒绝任务的处理策略
                     */
                    businessThreadPool = new ThreadPoolExecutor(
                            5,
                            10,
                            60L,
                            TimeUnit.SECONDS,
                            new SynchronousQueue<>(),
                            new MyRejectedExecutionHandler()
                    );
                }
            }
        }
        return businessThreadPool;
    }
}

/**
 * @author: thinkit
 * @date: 2020/4/14 10:10
 * @description: 自定义拒绝策略：阻塞等待
 */
class MyRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        try {
            // 核心改造点，由blocking queue的offer改成put阻塞方法
            executor.getQueue().put(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
