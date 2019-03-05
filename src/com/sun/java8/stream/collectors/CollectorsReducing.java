package com.sun.java8.stream.collectors;

import com.sun.java8.data.StreamsData;
import com.sun.java8.transaction.Transaction;

import static java.util.stream.Collectors.reducing;

/**
 * Collectors.reducing
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class CollectorsReducing {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.reducing");
        // 获取所有交易额总和
        Double transactionsSum1 = StreamsData.transactions.stream().collect(reducing(0.0, Transaction::getValue, (i, j) -> i + j));
        System.out.println("所有交易额总和：" + transactionsSum1);
        System.out.println();
    }

}
