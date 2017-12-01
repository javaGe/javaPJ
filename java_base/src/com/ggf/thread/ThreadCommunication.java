package com.ggf.thread;

import java.util.Random;
import java.util.concurrent.*;

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
//        demo1_1(); // 一个线程执行完，在执行另一个
//        demo2(); // 两个程序交叉运行
//        runDAfterABC(); // 一个线程等待其他线程运行完，才运行
//        runABCWhenAllReady(); // 所有线程准备好后运行
        doTaskWithResultInWorker(); // 子线程返回结果
    }

    //==================================================================
    //1.如何让两个线程依次执行？
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

    //==================================================================
    // 2.那如何让 两个线程按照指定方式有序交叉运行呢？
    // 这里可以使用同一个锁控制，然后调用wait（）方法，和notify（）方法
    // 线程一打印1，然后线程二打印1,2,3 线程一在打印2,3
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
                    // 线程二执行完，唤醒线程一
                    System.out.println("t1>>print:2");
                    System.out.println("t1>>print:3");
                }
            }
        });

        // 线程二
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (objLock) {
                    // 线程二输出内容
                    System.out.println("t2>>print:1");
                    System.out.println("t2>>print:2");
                    System.out.println("t2>>print:3");
                    // 唤醒wait中的线程
                    objLock.notify();
                }
            }
        });

        // 启动线程
        t1.start();
        t2.start();

    }


    //==============================================================================
    // 3.四个线程 A B C D，其中 D 要等到 A B C 全执行完毕后才执行，而且 A B C 是同步运行的
    /*
        1) 创建一个计数器，设置初始值，CountdownLatch countDownLatch = new CountDownLatch(2);
        2) 在 等待线程 里调用 countDownLatch.await() 方法，进入等待状态，直到计数值变成 0；
        3) 在 其他线程 里，调用 countDownLatch.countDown() 方法，该方法会将计数值减小 1；
        4) 当 其他线程 的 countDown() 方法把计数值变成 0 时，等待线程 里的 countDownLatch.await() 立即退出，继续执行下面的代码。
        CountDownLatch 适用于一个线程去等待多个线程的情况。
     */

    /**
     * 运行完ABC线程后，执行D线程
     */
    public static void runDAfterABC() {
        // 定义计数器的计数值
        int worker = 3;
        // 创建计数器
        final CountDownLatch countDownLatch = new CountDownLatch(worker);

        // 创建线程D
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("D线程等待ABC线程运行。。。。");
                try {
                    // 线程等待
                    countDownLatch.await();
                    System.out.println("ABC线程运行完，D线程运行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 创建ABC线程
        for (char threadName='A'; threadName<='C'; threadName++) {
            // char转换为String
            final String tN = String.valueOf(threadName);

            //创建线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程"+tN+"运行中。。");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程"+tN+"结束");
                    // 计数器减一
                    countDownLatch.countDown();
                }
            }).start();
        }
    }


    //============================================================================
    // 4. 线程 A B C 各自开始准备，直到三者都准备完毕，然后再同时运行
    /*
        1) 先创建一个公共 CyclicBarrier 对象，设置 同时等待 的线程数，CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        2）这些线程同时开始自己做准备，自身准备完毕后，需要等待别人准备完毕，这时调用 cyclicBarrier.await(); 即可开始等待别人；
        3） 当指定的 同时等待 的线程数都调用了 cyclicBarrier.await();时，意味着这些线程都准备完毕好，然后这些线程才 同时继续执行。
     */

    /**
     * 所有线程准备完后，一起运行
     */
    public static void runABCWhenAllReady() {
        // 定义运行线程数
        int runner = 3;

        // 创建CyclicBarrier 对象
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(runner);
        // 创建随机数类，产生准备时间
        final Random random = new Random();
        // 创建线程
        for (char threadName='A'; threadName <= 'C'; threadName++) {
            final String tN = String.valueOf(threadName);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 随机生成准备时间
                    long prepareTime = random.nextInt(1000)+100;
                    System.out.println("线程"+tN+"准备中。。");

                    try {
                        Thread.sleep(prepareTime);
                        System.out.println("线程"+tN+"准备完成，等待其他线程！");
                        // 线程准备完毕
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    // 所有线程准备好后，运行
                    System.out.println("线程"+tN+"运行中。。");
                }
            }).start();
        }
    }

    //=====================================================================
    // 5. 子线程运行完后，将结果返回给主线程
    /*
        实现接口类：Callable,在 v call（）方法中编写运行代码
        将结果返回主线程，使用FutureTask类的get方法，将数据传回去，
        get方法会阻塞线程，然后call方法执行
     */

    /**
     * 子线程返回结果给主线程
     */
    public static void doTaskWithResultInWorker() {
        // 创建Callable对象
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                // 创建任务
                System.out.println("任务开始");
                int result = 0;
                for (int i=0; i<=100; i++) {
                    result += i;
                }
                System.out.println("任务结束，返回结果");
                // 返回结果
                return result;
            }
        };

        // 创建FutureTask，获取返回值
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
        // 启动线程
        new Thread(futureTask).start();

        // 线程阻塞，获取返回值
        try {
            System.out.println("结果："+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

