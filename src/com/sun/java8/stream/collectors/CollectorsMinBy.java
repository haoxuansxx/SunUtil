package com.sun.java8.stream.collectors;

import com.sun.java8.stream.data.StreamsData;
import com.sun.java8.transaction.Transaction;

import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.minBy;

/**
 * Collectors.minBy  -- 查找最小
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class CollectorsMinBy {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.minBy");
        // 查找最小交易值
        Optional<Transaction> minTransactions = StreamsData.transactions.stream().collect(minBy(comparing(Transaction::getValue)));
        System.out.println("查找最大交易值：" + minTransactions.get().getValue());
        System.out.println();
    }

}
