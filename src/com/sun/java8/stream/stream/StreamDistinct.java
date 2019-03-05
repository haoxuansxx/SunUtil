package com.sun.java8.stream.stream;

import java.util.Arrays;
import java.util.List;

/**
 * Stream.distinct  -- 筛选各异的元素
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class StreamDistinct {

    public static void main(String... args) {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.distinct（筛选各异的元素）");
        // 方法：distinct  -- 返回：一个元素各异（根据流所生成元素的hashCode和equals方法实现）的流
        numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);
        System.out.println();
    }

}
