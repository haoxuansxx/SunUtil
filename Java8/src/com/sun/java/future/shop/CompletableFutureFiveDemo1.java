package future.shop;

import future.shop.service.ShopService;

import java.util.concurrent.CompletableFuture;

/**
 * Java CompletableFuture 示例类 -- 最佳价格查询器
 *
 * @Author Sun
 * @date 2019-03-15
 */
public class CompletableFutureFiveDemo1 {

    /**
     * 以5个商品的示例输出
     */
    public static void main(String... args) {

        // 验证findPriceFive4的正确性和执行性能  -- 以最简单的方式实现使用Discount服务的findPriceFive4方法
        System.out.println("--------------------- 验证findPriceFive4的正确性和执行性能  -- 顺序流  -- 5个商品 ------------------------");
        long start = System.nanoTime();
        System.out.println(ShopService.findPriceFive4("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        // 验证findPriceFive5的正确性和执行性能  -- 以最简单的方式实现使用Discount服务的findPriceFive5方法
        System.out.println("--------------------- 验证findPriceFive5的正确性和执行性能  -- CompletableFuture异步方式  -- 5个商品 ------------------------");
        start = System.nanoTime();
        System.out.println(ShopService.findPriceFive5("myPhone27S"));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");


        // 验证findPricesStream的正确性和执行性能  -- 要有商店返回商品价格就在第一时间显示返回值
        System.out.println("--------------------- 验证findPricesStream的正确性和执行性能  -- 要有商店返回商品价格就在第一时间显示返回值  -- 5个商品 ------------------------");
        start = System.nanoTime();
        CompletableFuture[] futures = ShopService.findPricesStream("myPhone")
                .map(f -> f.thenAccept(System.out::println)).toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        // 验证findPricesStream的正确性和执行性能  -- 要有商店返回商品价格就在第一时间显示返回值
        System.out.println("--------------------- 验证findPricesStream的正确性和执行性能  -- 要有商店返回商品价格就在第一时间显示返回值  -- 5个商品 ------------------------");
        long start1 = System.nanoTime();
        CompletableFuture[] futures1 = ShopService.findPricesStream("myPhone27S")
                .map(f -> f.thenAccept(
                        s -> System.out.println(s + " (done in " +
                                ((System.nanoTime() - start1) / 1_000_000) + " msecs)")))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures1).join();
        System.out.println("All shops have now responded in " + ((System.nanoTime() - start1) / 1_000_000) + " msecs");

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
