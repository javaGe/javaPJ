package thread;

import java.util.concurrent.*;

/**
 * @description： 线程池的使用
 * Java通过Executors提供四种线程池，分别为：
    newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
    newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
    newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
    newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 * @author: geguangfu
 * @date: 2017/11/19
 * @time: 13:53
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        // 自定义线程池
        ExecutorService ThreadPool = new ThreadPoolExecutor(
                3, 5, 2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10), new ThreadPoolExecutor.DiscardOldestPolicy());

        // 创建一个缓冲线程池
        //ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // 创建一个固定大小的线程池
        //ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        for (int i=0; i<3; i++) {
            final int index = i;
            try {
                Thread.sleep(i*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 创建线程
            ThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                      System.out.println("线程【"+Thread.currentThread().getName()+"】输出"+index);
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
        }

        ThreadPool.shutdown();
        try {
            // 判断线程池中的任务是否全部执行完 返回boolean类型的值
            ThreadPool.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
