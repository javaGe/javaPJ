package thread;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description：
 * @author: geguangfu
 * @date: 2017/11/18
 * @time: 17:43
 */
public class ThreadDemo {
    static Logger logger = Logger.getLogger(ThreadDemo.class);
    public static void main(String[] args) {
        PropertyConfigurator.configure(ThreadDemo.class.getClassLoader().getResource("threadlog4j.properties"));
        System.out.println("程序启动");
        logger.info("程序启动");
        long start = System.currentTimeMillis();

        logger.info("创建线程池");
        ExecutorService cachedThreadPool = new ThreadPoolExecutor(
                3, 5, 2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10), new ThreadPoolExecutor.DiscardOldestPolicy());

        //ExecutorService cachedThread = Executors.newCachedThreadPool();
        List<String[]> lists = new ArrayList<String[]>();
        lists.add(new String[]{"a","b","c"});
        lists.add(new String[]{"d","e","f"});
        lists.add(new String[]{"h","i","j"});

        for (final String[] strs : lists) {
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程【"+Thread.currentThread().getName()+"】处理");
                    ThreadDemo.process(strs);
                }
            });
        }
        cachedThreadPool.shutdown();
        try {
            cachedThreadPool.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*logger.info("实例化线程类");
        ShowThread sh1 = new ShowThread(new String[]{"a", "b", "c"});
        ShowThread sh2 = new ShowThread(new String[]{"d", "e", "f"});
        ShowThread sh3 = new ShowThread(new String[]{"g", "h", "i"});

        Thread t1 = new Thread(sh1);
        Thread t2 = new Thread(sh2);
        Thread t3 = new Thread(sh3);

        System.out.println("启动线程");
        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("程序结束，用时：【"+(System.currentTimeMillis()-start)+"ms】");
    }

    public static void process(String[] array) {
        for (String str : array) {
            System.out.println("输出数组内值：【" + str +"】");
        }

    }
}

/*class ShowThread implements Runnable {
    static Logger logger = Logger.getLogger(ShowThread.class);
    private String[] array;
    public ShowThread(String[] array) {
        this.array = array;
    }
    public ShowThread(){}
    @Override
    public void run() {
        System.out.println("线程【"+Thread.currentThread().getName()+"】开始处理");
        logger.info("线程【"+Thread.currentThread().getName()+"】开始处理");
        process(array);
        System.out.println("线程【"+Thread.currentThread().getName()+"】结束处理");
        logger.info("线程【"+Thread.currentThread().getName()+"】结束处理");

    }

    *//**
     * 处理方法
     * @param array
     *//*
    public void process(String[] array) {
        for (String str : array) {
            System.out.println("输出数组内值：【" + str +"】");
        }

    }
}*/

