package stream.parallel;

import java.util.stream.Stream;

/**
 * Stream.parallel  -- 并行流
 *
 * @Author Sun
 * @date 2019-03-07
 */
public class StreamParallel {

    /**
     * 并行流内部使用了默认的ForkJoinPool，它默认的线程数量就是你的处理器数量 ，
     * 这个值是由 Runtime.getRuntime().availableProcessors()得到的。
     * 可以通过系统属性 java.util.concurrent.ForkJoinPool.common.parallelism来改变线程池大小。
     * 示例： System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","12");
     * 这是一个全局设置，因此它将影响代码中所有的并行流。
     * 一般而言，让ForkJoinPool的大小等于处理器数量是个不错的默认值，除非你有很好的理由，否则我们强烈建议你不要修改它。
     * <p>
     * 使用并行流的建议：
     * ArrayList 极佳
     * LinkedList 差
     * IntStream.range 极佳
     * Stream.iterate 差
     * HashSet 好
     * TreeSet 好
     */
    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.parallel");
        // 方法：parallel  将流转换为并行流
        long sum = Stream.iterate(1L, i -> i + 1).limit(1000).parallel().reduce(0L, Long::sum);
        System.out.println("1到1000的数字之和是：" + sum);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.sequential");
        // 方法：sequential  将并行流转换为流
        long numberSum = Stream.iterate(1L, i -> i + 1).limit(1000).sequential().reduce(0L, Long::sum);
        System.out.println("1到1000的数字之和是：" + numberSum);
        System.out.println();
    }

}
