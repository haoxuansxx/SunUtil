package stream.parallel;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Stream.parallel  -- 并行流演示1
 *
 * @Author Sun
 * @date 2019-03-07
 */
public class ParallelStreamDemo {

    /**
     * 并行流内部使用了默认的ForkJoinPool，它默认的线程数量就是你的处理器数量 ，
     * 这个值是由 Runtime.getRuntime().availableProcessors()得到的。
     * 可以通过系统属性 java.util.concurrent.ForkJoinPool.common.parallelism来改变线程池大小。
     * 示例： System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","12");
     * 这是一个全局设置，因此它将影响代码中所有的并行流。
     * 一般而言，让ForkJoinPool的大小等于处理器数量是个不错的默认值，除非你有很好的理由，否则我们强烈建议你不要修改它。
     */
    public static void main(String... args) {

        /* 使用传统for循环的迭代版本的流汇总1到10_100_100之和的响应时间 */
        System.out.println("Sequential sum done in:" + ParallelStreamDemo.measureSumPerf(ParallelStreamDemo::sequentialSum, 10_000_000) + " msecs");

        /* 使用并行流汇总1到10_100_100之和的响应时间 */
        System.out.println("Parallel sum done in:" + ParallelStreamDemo.measureSumPerf(ParallelStreamDemo::parallelSum, 10_000_000) + " msecs");

        /* 使用传统for循环的迭代版本的流汇总1到10_100_100之和的响应时间 */
        System.out.println("LongStreamSequential sum done in:" + ParallelStreamDemo.measureSumPerf(ParallelStreamDemo::longStreamSequentialSum, 10_000_000) + " msecs");

        /* 使用数值并行流汇总1到10_100_100之和的响应时间 */
        System.out.println("LongStreamParallel sum done in:" + ParallelStreamDemo.measureSumPerf(ParallelStreamDemo::longStreamParallelSum, 10_000_000) + " msecs");

    }

    /**
     * 使用传统for循环的迭代版本的流，接收数字n作为参数，返回从1到给定参数的所有数字之和
     */
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
    }

    /**
     * 使用传统for循环的迭代版本的流，，接收数字n作为参数，返回从1到给定参数的所有数字之和
     */
    public static long longStreamSequentialSum(long n) {
        return LongStream.rangeClosed(0L, n).reduce(0L, Long::sum);
    }

    /**
     * 使用并行流，接收数字n作为参数，返回从1到给定参数的所有数字之和
     */
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
    }

    /**
     * 使用数值并行流，接收数字n作为参数，返回从1到给定参数的所有数字之和
     */
    public static long longStreamParallelSum(long n) {
        return LongStream.rangeClosed(0L, n).parallel().reduce(0L, Long::sum);
    }

    /**
     * 这个方法接受一个函数和一个long作为参数。它会对传给方法的long应用函数10次，记录每次执行的时间（以毫秒为单位），并返回最短的一次执行时间。
     */
    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result sum:" + sum);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }

}
