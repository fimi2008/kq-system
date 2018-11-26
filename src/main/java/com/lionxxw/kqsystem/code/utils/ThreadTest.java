package com.lionxxw.kqsystem.code.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by lionxxw on 2016/8/9.
 */
public class ThreadTest {
    public static void main(String[] args) {
        demo();
    }

    public static void demo(){
        System.out.println("程序---start...");
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        System.out.println("程序---start...ing");
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("异步通知开始+++++++++++++++++++++++++");
                    for (int i = 0; i< 10; i++){
                        try {
                            System.out.println(Thread.currentThread().getName());
                            System.out.println("sleeping...start");
                            Thread.sleep(1000);
                            System.out.println("sleeping...end");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        System.out.println(i);
                    }
                System.out.println("异步通知结束+++++++++++++++++++++++++");
            }
        });
        System.out.println("程序---end...");
    }

    /**
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
     */
    public static void test1(){
        System.out.println("start");
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(index);
                }
            });
        }
        System.out.println("end");
    }

    /**
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     */
    public static void test2(){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 建一个定长线程池，支持定时及周期性任务执行
     */
    public static void test3(){
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);//表示延迟3秒执行
    }

    public static void test4(){
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println("delay 1 seconds, and excute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS); // 表示延迟1秒后每3秒执行一次。
    }

    /**
     * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
     */
    public static void test5(){
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void test6(){
        ExecutorService singleThreadExecutor = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                public void run() {
                    try {
                        while(true) {
                            System.out.println(Thread.currentThread().getName());
                            System.out.println(index);
                            Thread.sleep(10 * 1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
