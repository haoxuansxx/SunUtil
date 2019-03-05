package com.sun.java8.stream.collectors;

import com.sun.java8.data.StreamsData;
import com.sun.java8.transaction.Transaction;

import static java.util.stream.Collectors.joining;

/**
 * Collectors.joining  -- 数据拼接
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class CollectorsJoining {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.joining");
        // 获取所有交易数据拼接字符串
        String transactionsStr = StreamsData.transactions.stream().map(Transaction::toString).collect(joining("，"));
        System.out.println("所有交易数据拼接字符串：" + transactionsStr);
        System.out.println();
    }

}
