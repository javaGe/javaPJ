package com.ggf.thread;

/**
 * @description: 线程之间的通信
                1.如何让两个线程依次执行？
                2.那如何让 两个线程按照指定方式有序交叉运行呢？
                3.四个线程 A B C D，其中 D 要等到 A B C 全执行完毕后才执行，而且 A B C 是同步运行的
                4.三个运动员各自准备，等到三个人都准备好后，再一起跑子线程完成某件任务后，把得到的结果回传给主线程
 * @author: geguangfu
 * @date：2017/11/30
 * @time：20:11
 * @version: v1.0
 */
public class ThreadCommunication {
    public static void main(String[] args) {
//        demo1(); // 两个线程间依次执行
        demo1_1(); // 一个线程执行完，在执行另一个
    }

    //1.如何让两个线程依次执行？
    //==================================================================
    public static void demo1() {
        // 线程一
        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                printNumber("t1");
            }
        });

        // 线程二
        final Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                printNumber("t2");
            }
        });

        // 启动线程
        t1.start();
        t2.start();
    }

    public static void demo1_1() {
        // 线程一
        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                printNumber("t1");
            }
        });

        // 线程二
        final Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 等到线程一执行完，再执行线程二
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printNumber("t2");
            }
        });

        // 启动线程
        t1.start();
        t2.start();
    }

    /**
     * 线程调用的方法
     * @param threadName
     */
    public static void printNumber(String threadName) {
        int i = 0;
        while (i++ < 3) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(threadName+">>pirnt:"+i);
        }
    }

    //2.那如何让 两个线程按照指定方式有序交叉运行呢？
    //这里可以使用同一个锁控制，然后调用wait（）方法，和notify（）方法
    //==================================================================
    //线程一打印1，然后线程二打印1,2,3 线程一在打印2,3
    public static void demo2() {
        // 创建一个对象，用来作为锁
        final Object objLock = new Object();

        //线程一
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 同步代码块
                synchronized (objLock){
                    System.out.println("t1>>print:1");
                    try {
                        objLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
