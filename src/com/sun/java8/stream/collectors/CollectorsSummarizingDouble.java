package com.sun.java8.stream.collectors;

import com.sun.java8.stream.data.StreamsData;
import com.sun.java8.transaction.Transaction;

import java.util.DoubleSummaryStatistics;

import static java.util.stream.Collectors.summarizingDouble;

/**
 * Collectors.summarizingDouble  -- 平均数、求和、数量、最大值和最小值
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class CollectorsSummarizingDouble {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.summarizingDouble");
        // 汇总所有交易数据的平均数、求和、数量、最大值和最小值
        DoubleSummaryStatistics statisticsDouble = StreamsData.transactions.stream().collect(summarizingDouble(Transaction::getValue));
        System.out.println("汇总所有交易数据的平均数、求和、数量、最大值和最小值等：" + statisticsDouble.toString());
        System.out.println();
    }

}
