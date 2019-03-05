package com.sun.java8.stream.prime;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static com.sun.java8.stream.prime.PrimeNumber.isPrime;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * 自己手动实现Collectors.toList()
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    /**
     * 创建集合操作的起始点
     * <p>
     * 这里不但创建了用作累加器的Map，还为true和false两个键下面初始化了对应的空列表。
     * 在收集过程中会把质数和非质数分别添加到这里。
     */
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        /* 从一个有两个空List的Map开始收集过程 */
        return () -> new HashMap<Boolean, List<Integer>>() {{
            put(true, new ArrayList<Integer>());
            put(false, new ArrayList<Integer>());
        }};
    }

    /**
     * 累积遍历过的项目，原位修改累加器
     * <p>
     * 在这个方法中，你调用了isPrime方法，将待测试是否为质数的数以及迄今找到的质数列表
     * （也就是累积Map中true键对应的值）传递给它。这次调用的结果随后被用作获取质数或非质数
     * 列表的键，这样就可以把新的被测数添加到恰当的列表中。
     */
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        /* 将已经找到的质数列表传递给isPrime方法，根据isPrime方法的返回值，从Map中取质数或非质数列表，把当前的被测数加进去 */
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            acc.get(isPrime(acc.get(true), candidate)).add(candidate);
        };
    }

    /**
     * 恒等函数
     */
    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        /* 收集过程无需转换，因此用identity函数结尾 */
        return Function.identity();
    }

    /**
     * 修改第一个累加器，将其与第二个累加器合并
     */
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        /* 将第二个Map合并到第一个Map */
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    /**
     * 为收集器添加IDENTITY_FINISH标志
     */
    @Override
    public Set<Collector.Characteristics> characteristics() {
        /* 这个收集器是IDENTITY_FINISH，但既不是UNORDERED也不是CONCURRENT，因为质数是按顺序发现的 */
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
    }

}
