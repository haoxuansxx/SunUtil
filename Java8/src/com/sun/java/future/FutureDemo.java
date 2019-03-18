package future;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * Java Future 异步执行示例类
 *
 * @Author Sun
 * @date 2019-03-15
 */
public class FutureDemo {

    public static void main(String... args) {
        // 创建ExecutorService，通过它你可以向线程池提交任务
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 向ExecutorService提交一个Callable对象
        Future<Double> future = executorService.submit(new Callable<Double>() {
            @Override
            public Double call() {
                // 以异步方式在新的线程中执行耗时的操作
                return doSomeLongComputation();
            }
        });
        // 异步操作进行的同时，你可以做其他事情
        doSomethingElse();
        try {
            // 获取异步操作的结果，如果最终被阻塞，无法得到结果，那么在最多等待1秒钟之后退出
            Double result = future.get(1, TimeUnit.SECONDS);

            return;
        } catch (ExecutionException ee) {
            // 计算抛出一个异常
        } catch (InterruptedException ie) {
            // 当前线程在等待过程中被中断
        } catch (TimeoutException te) {
            // 在Future对象完成之前超过已过期
        }


    }

    /**
     * 以异步方式在新的线程中执行耗时的操作
     */
    public static Double doSomeLongComputation() {

        IntStream.rangeClosed(0, 1000000).forEach(i -> System.out.println("异步操作输出数字：" + i));

        return 0.0;
    }

    /**
     * 异步操作进行的同时，你可以做其他事情
     */
    public static void doSomethingElse() {
        IntStream.rangeClosed(0, 10000).forEach(i -> System.out.println("异步操作的同时输出数字：" + i));
    }

}
