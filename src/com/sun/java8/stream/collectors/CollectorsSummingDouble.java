package com.sun.java8.stream.collectors;

import com.sun.java8.stream.data.StreamsData;
import com.sun.java8.transaction.Transaction;

import static java.util.stream.Collectors.summingDouble;

/**
 * Collectors.summingDouble  -- 汇总
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class CollectorsSummingDouble {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.summingDouble");
        // 汇总所有交易额
        Double transactionsSum = StreamsData.transactions.stream().collect(summingDouble(Transaction::getValue));
        System.out.println("汇总所有交易额的和是：" + transactionsSum);
        System.out.println();
    }

}
