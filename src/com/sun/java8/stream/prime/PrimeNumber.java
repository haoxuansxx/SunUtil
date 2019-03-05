package com.sun.java8.stream.prime;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * 将数字按质数和非质数区分
 *
 * @Author Sun
 * @date 2019-03-04
 */
public class PrimeNumber {

    public static void main(String... args) {

        // 输出1到100之间所有的质数和非质数
        Map<Boolean, List<Integer>> listBool = partitionPrimes(100);
        listBool.forEach((key, value) -> System.out.println(key + "----:" + value));

    }

    /**
     * 获取所有从2到输入数字n的质数和非质数列表  -- 工厂方法，Collector.partitioningBy分区函数
     * @param n 一个数字
     * @return 质数和非质数列表集合
     */
    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(partitioningBy(candidate -> isPrime(candidate)));
    }

    /**
     * 获取所有从2到输入数字n的质数和非质数列表  -- 自定义函数，实现功能跟Collector.partitioningBy一样
     * @param n 一个数字
     * @return 质数和非质数列表集合
     */
    public static Map<Boolean, List<Integer>> partitionPrimesCollector(int n){
        return IntStream.rangeClosed(2, n).boxed().collect(new  PrimeNumbersCollector());
    }

    /**
     * 判断一个数字是不是质数，一个简单的优化是仅测试小于等于待测数平方根的因子
     *
     * @param candidate 一个数字
     * @return
     */
    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    /**
     * 判断一个数字是不是质数，一个简单的优化是仅测试小于等于待测数平方根的因子
     *
     * @param candidate 一个数字
     * @return
     */
    public static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return takeWhile(primes, i -> i <= candidateRoot).stream().noneMatch(p -> candidate % p == 0);
    }

    /**
     * @param list
     * @param p
     * @param <A>
     * @return
     */
    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            // 检查列表中的当前项目是否满足谓词
            if (!p.test(item)) {
                // 如果不满足，返回该项目之前的前缀子列表
                return list.subList(0, i);
            }
            i++;
        }
        // 列表中的所有项目都满足谓词，因此返回列表本身
        return list;
    }

}
