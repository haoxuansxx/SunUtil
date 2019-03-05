package com.sun.java8.stream.collectors;

import com.sun.java8.stream.data.StreamsData;
import com.sun.java8.transaction.Transaction;

import static java.util.stream.Collectors.averagingDouble;

/**
 * Collectors.averagingDouble  -- 平均值
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class CollectorsAveragingDouble {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.averagingDouble");
        // 获取所有交易额的平均值
        Double averagingDouble = StreamsData.transactions.stream().collect(averagingDouble(Transaction::getValue));
        System.out.println("汇总所有交易额的平均交易额是：" + averagingDouble);
        System.out.println();

    }

}
