package future.shop;

import future.shop.service.ShopService;

/**
 * Java CompletableFuture 示例类 -- 最佳价格查询器
 *
 * @Author Sun
 * @date 2019-03-15
 */
public class CompletableFutureFiveDemo {

    /**
     * 以5个商品的示例输出
     */
    public static void main(String... args) {

        // 验证findPrices的正确性和执行性能  -- 顺序流
        System.out.println("--------------------- 验证findPrices的正确性和执行性能  -- 顺序流  -- 5个商品 ------------------------");
        long start = System.nanoTime();
        System.out.println(ShopService.findPriceFive("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        // 验证findPrices1的正确性和执行性能  -- 并行流
        System.out.println("--------------------- 验证findPrices1的正确性和执行性能  -- 并行流  -- 5个商品 ------------------------");
        start = System.nanoTime();
        System.out.println(ShopService.findPriceFive1("myPhone27S"));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        // 验证findPrices2的正确性和执行性能  -- CompletableFuture异步方式
        System.out.println("--------------------- 验证findPrices2的正确性和执行性能  -- CompletableFuture异步方式  -- 5个商品 ------------------------");
        start = System.nanoTime();
        System.out.println(ShopService.findPriceFive2("myPhone27S"));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        // 验证findPrices2的正确性和执行性能  -- CompletableFuture异步方式
        System.out.println("--------------------- 验证findPrices2的正确性和执行性能  -- CompletableFuture异步方式  -- 5个商品  -- 自定义线程池 ------------------------");
        start = System.nanoTime();
        System.out.println(ShopService.findPriceFive3("myPhone27S"));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

    }

}
