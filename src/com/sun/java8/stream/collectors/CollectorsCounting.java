package com.sun.java8.stream.collectors;

import com.sun.java8.data.StreamsData;
import static java.util.stream.Collectors.*;

/**
 * Collectors.counting  -- 汇总
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class CollectorsCounting {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.counting");
        // 总共有多少次交易
        Long count = StreamsData.transactions.stream().collect(counting());
        Long count1 = StreamsData.transactions.stream().count();
        System.out.println("总共有多少次交易：" + count);
        System.out.println();
    }

}
