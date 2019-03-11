package stream.parallel.task;

import stream.parallel.ParallelStreamDemo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * Stream.parallel  -- 分支/合并框架
 *
 * @Author Sun
 * @date 2019-03-07
 */
public class ParallelStreamMain {

    public static void main(String... args) {
        /* 使用分支/合并框架汇总1到10_100_100之和的响应时间 */
        System.out.println("Sequential sum done in:" + ParallelStreamDemo.measureSumPerf(ParallelStreamMain::forkJoinSum, 10_000_000) + " msecs");
    }

    /**
     * 使用分支/合并框架，接收数字n作为参数，返回从1到给定参数的所有数字之和
     */
    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

}
