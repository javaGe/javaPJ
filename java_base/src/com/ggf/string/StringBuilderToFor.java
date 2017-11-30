package com.ggf.string;

/**
 * @description: StringBuilder在for循环中使用，用来拼接字符串，这里不建议使用 “+=”，效率不好
 *                  使用“+=”内部实质是调用了StringBuilder
 * @author: geguangfu
 * @date：2017/11/30
 * @time：19:44
 * @version: v1.0
 */
public class StringBuilderToFor {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 创建StringBuilder对象
        StringBuilder sb = new StringBuilder();
        String s = "";
        for (int i=0; i<10000; i++) {
//            sb.append(i+"");
            s += i+"";
        }

        System.out.println(s); // 506ms
//        System.out.println(sb.toString()); // 59ms
        System.out.println((System.currentTimeMillis()-start));
    }
}
