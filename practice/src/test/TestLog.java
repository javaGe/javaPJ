package test;


import org.apache.log4j.Logger;

/**
 * Created by geguangfu on 2017/10/11.
 */
public class TestLog {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(TestLog.class);
        logger.debug("debug......");
        logger.info("info......");
        logger.warn("warn.....");
        logger.error("error....");
        logger.fatal("fatal.....");
    }
}
