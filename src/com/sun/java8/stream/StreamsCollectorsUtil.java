package com.sun.java8.stream;

import com.sun.java8.stream.bean.CaloricLevel;
import com.sun.java8.stream.bean.Dish;
import com.sun.java8.stream.bean.Type;
import com.sun.java8.transaction.Transaction;
import com.sun.java8.transaction.Currency;

import java.util.*;

import static com.sun.java8.stream.StreamsUtil.menu;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * Streams收集器（Collectors）   -- API类
 *
 * @Author Sun
 * @date 2019-02-28
 */
public class StreamsCollectorsUtil {

    public static List<Transaction> transactions = Arrays.asList(new Transaction(Currency.EUR, 1500.0),
            new Transaction(Currency.USD, 2300.0),
            new Transaction(Currency.GBP, 9900.0),
            new Transaction(Currency.EUR, 1100.0),
            new Transaction(Currency.JPY, 7800.0),
            new Transaction(Currency.CHF, 6700.0),
            new Transaction(Currency.EUR, 5600.0),
            new Transaction(Currency.USD, 4500.0),
            new Transaction(Currency.CHF, 3400.0),
            new Transaction(Currency.GBP, 3200.0),
            new Transaction(Currency.USD, 4600.0),
            new Transaction(Currency.JPY, 5700.0),
            new Transaction(Currency.EUR, 6800.0));

    public static void main(String... args) {

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.groupingBy");
        // 对交易按照货币分组
        Map<Currency, List<Transaction>> transactionByCurrencies = transactions.stream().collect(groupingBy(Transaction::getCurrency));
        transactionByCurrencies.forEach((key, value) -> {
            System.out.println("分组类型：" + key);
            value.stream().forEach(o -> System.out.println(o.toString()));
        });
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.counting");
        // 总共有多少次交易
        Long count = transactions.stream().collect(counting());
        Long count1 = transactions.stream().count();
        System.out.println("总共有多少次交易：" + count);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.maxBy");
        // 查找最大交易值
        Optional<Transaction> maxTransactions = transactions.stream().collect(maxBy(comparing(Transaction::getValue)));
        System.out.println("查找最大交易值：" + maxTransactions.get().getValue());
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.minBy");
        // 查找最小交易值
        Optional<Transaction> minTransactions = transactions.stream().collect(minBy(comparing(Transaction::getValue)));
        System.out.println("查找最大交易值：" + minTransactions.get().getValue());
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.summingDouble");
        // 汇总所有交易额
        Double transactionsSum = transactions.stream().collect(summingDouble(Transaction::getValue));
        System.out.println("汇总所有交易额的和是：" + transactionsSum);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.averagingDouble");
        // 获取所有交易额的平均值
        Double averagingDouble = transactions.stream().collect(averagingDouble(Transaction::getValue));
        System.out.println("汇总所有交易额的平均交易额是：" + averagingDouble);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.summarizingDouble");
        // 汇总所有交易数据的平均数、求和、数量、最大值和最小值
        DoubleSummaryStatistics statisticsDouble = transactions.stream().collect(summarizingDouble(Transaction::getValue));
        System.out.println("汇总所有交易数据的平均数、求和、数量、最大值和最小值等：" + statisticsDouble.toString());
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.joining");
        // 获取所有交易数据拼接字符串
        String transactionsStr = transactions.stream().map(Transaction::toString).collect(joining("，"));
        System.out.println("所有交易数据拼接字符串：" + transactionsStr);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.reducing");
        // 获取所有交易额总和
        Double transactionsSum1 = transactions.stream().collect(reducing(0.0, Transaction::getValue, (i, j) -> i + j));
        System.out.println("所有交易额总和：" + transactionsSum1);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.reducing");
        // 获取所有交易额总和
        Double transactionsSum2 = transactions.stream().collect(reducing(0.0, Transaction::getValue, Double::sum));
        System.out.println("所有交易额总和：" + transactionsSum2);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：DoubleStream.sum");
        // 获取所有交易额总和  -- PS：建议使用这种方案
        Double transactionsSum3 = transactions.stream().mapToDouble(Transaction::getValue).sum();
        System.out.println("所有交易额总和：" + transactionsSum3);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.groupingBy");
        // 获取所有交易额总和  -- PS：建议使用这种方案
        Map<Currency, List<Transaction>> dishesByCurrency = transactions.stream().collect(groupingBy(Transaction::getCurrency));
        System.out.println("所有交易额总和：" + transactionsSum3);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.collectingAndThen");
        // 以类型分组查找每一组里面卡路里最高的菜肴
        Map<Type, Dish> mostCaloricByType = menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println("以类型分组查找每一组里面卡路里最高的菜肴：" + mostCaloricByType);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.mapping");
        // 以类型分组并对每一组里面的菜肴以卡路里进行分类
        Map<Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream().collect(groupingBy(Dish::getType,mapping(
                dish -> {
                    if(dish.getCalories() <= 402){
                        return CaloricLevel.DIET;
                    }else if(dish.getCalories() <= 700){
                        return CaloricLevel.NORMAL;
                    }else{
                        return CaloricLevel.FAT;
                    }
                }
                , toCollection(HashSet::new))));
        System.out.println(caloricLevelsByType);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.partitioningBy");
        // 以素食和非素食分区菜肴
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println("以素食和非素食分区菜肴：" + partitionedMenu);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.partitioningBy");
        // 以素食和非素食分区菜肴并以类型进行分组
        Map<Boolean, Map<Type, List<Dish>>> vegetarianDishesByType = menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        System.out.println("以素食和非素食分区菜肴并以类型进行分组：" + vegetarianDishesByType);
        System.out.println();

    }

}
