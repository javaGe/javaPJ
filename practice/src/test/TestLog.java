package test;


import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by geguangfu on 2017/10/11.
 */
public class TestLog {
    static Logger logger = Logger.getLogger(TestLog.class);

    public static void main(String[] args) throws IOException {
        System.out.println("程序启动");
        Properties pro = new Properties();
        System.out.println("加载配置文件");
        pro.load(TestLog.class.getResourceAsStream("/config.properties"));
        System.out.println("姓名：【"+pro.getProperty("NAME")+"】年龄：【"+pro.getProperty("AGE")+"】");
        logger.debug("debug......");
        logger.info("info......");
        logger.warn("warn.....");
        logger.error("error....");
        logger.fatal("fatal.....");
    }
}
