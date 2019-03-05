package com.sun.java8.stream.collectors;

import com.sun.java8.stream.data.StreamsData;
import com.sun.java8.transaction.Transaction;

import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.maxBy;

/**
 * Collectors.maxBy  -- 查找最大
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class CollectorsMaxBy {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.maxBy");
        // 查找最大交易值
        Optional<Transaction> maxTransactions = StreamsData.transactions.stream().collect(maxBy(comparing(Transaction::getValue)));
        System.out.println("查找最大交易值：" + maxTransactions.get().getValue());
        System.out.println();
    }

}
