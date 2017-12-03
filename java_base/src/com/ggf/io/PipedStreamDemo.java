package com.ggf.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @description: 管道流的学习，在同一个进程的不同线程，使用管道流进行通信
 * 相当于生产者（output）和消费者（input）的关系，两者之间进行通信
 * @author: geguangfu
 * @date：2017/12/1
 * @time：17:03
 * @version: v1.0
 */
public class PipedStreamDemo {
    public static void main(String[] args) throws IOException {
        InputPiped inputPiped = new InputPiped();
        OutputPiped outputPiped = new OutputPiped();
        // 输入流连接输出流
        outputPiped.getOutputStream().connect(inputPiped.getInputStream());
        System.out.println("启动输出流");
        new Thread(outputPiped).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("启动输入流");
        new Thread(inputPiped).start();
    }

}

/**
 * 输入管道
 */
class InputPiped implements Runnable {
    // 管道输出流
    private final PipedInputStream inputStream = new PipedInputStream();
    public InputPiped() {
    }
    public PipedInputStream getInputStream() {
        return inputStream;
    }
    @Override
    public void run() {
        try {
            byte[] buf = new byte[1024];
            int len = -1;
            System.out.println("管道读取准备。");
            StringBuffer result = new StringBuffer();
            while ((len = inputStream.read(buf)) > 0) {
                //System.out.println(new String(buf, 0, len));
                result.append(new String(buf, 0, len));
            }
            System.out.println("管道读取结果：" + result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 输出流
 */
class OutputPiped implements Runnable {
    private final PipedOutputStream outputStream = new PipedOutputStream();
    public OutputPiped() {
    }
    public PipedOutputStream getOutputStream() {
        return outputStream;
    }
    @Override
    public void run() {
        try {
            System.out.println("管道写出准备。");
            StringBuilder sb = new StringBuilder();
            // 模拟 通过for循环写入2050个字节
            for (int i = 0; i < 201; i++) {
                sb.append("0123456789");
                if (i > 0 && (i % 10 == 0)) {
                    sb.append("\r\n");
                }
            }
            String str = sb.toString();
            outputStream.write(str.getBytes());
            System.out.println("管道写出完成。");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}