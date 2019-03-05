package com.sun.java8.stream.prime;

import static com.sun.java8.stream.prime.PrimeNumber.partitionPrimes;
import static com.sun.java8.stream.prime.PrimeNumber.partitionPrimesCollector;

/**
 * 工厂方法Collector.partitioningBy和自定义方法 -- 测试类
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class CollectorHarness {

    public static void main(String... args) {

        /* 使用工厂方法Collector.partitioningBy测试用时 */
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            partitionPrimes(1000000);
            long duration = (System.nanoTime() - start) / 1000000;
            if (duration < fastest) {
                fastest = duration;
            }
            System.out.println("Fastest execution done in " + fastest + " msecs");
        }

        /* 使用自定义方法测试，实现功能跟Collector.partitioningBy一样 */
        long fastest1 = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            partitionPrimesCollector(1000000);
            long duration = (System.nanoTime() - start) / 1000000;
            if (duration < fastest1) {
                fastest1 = duration;
            }
            System.out.println("Fastest execution done in " + fastest1 + " msecs");
        }

    }

}
