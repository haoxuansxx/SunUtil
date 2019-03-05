package com.sun.java8.stream.stream;

import com.sun.java8.data.StreamsData;

/**
 * Stream.allMatch  -- 流中是否都能匹配给定的谓词
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class StreamAllMatch {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.allMatch");
        // 方法：allMatch  流中是否都能匹配给定的谓词
        boolean isHealthy = StreamsData.menu.stream().allMatch(d -> d.getCalories() < 1000);
        System.out.println("菜品是否有利于健康：" + isHealthy);
        System.out.println();
    }

}
