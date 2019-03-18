package future.shop;

import future.shop.service.ShopService;

import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * Java Future 示例类 -- 最佳价格查询器
 *
 * @Author Sun
 * @date 2019-03-15
 */
public class FutureDemo {

    public static void main(String... args) {

        long start = System.nanoTime();
        // 查询商店，试图取得商品的价格
        Future<Double> futurePrice = ShopService.getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        // 执行更多任务，比如查询其他商店
        doSomethingElse();

        // 在计算商品价格的同时
        try {
            // 从Future对象中读取价格，如果价格未知，会发生阻塞
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    /**
     * 执行更多任务输出数字
     */
    public static void doSomethingElse() {
        IntStream.rangeClosed(1, 100).limit(10).forEach(i -> System.out.println("执行更多任务输出数字：" + i));
    }

}
