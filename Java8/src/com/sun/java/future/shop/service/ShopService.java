package future.shop.service;

import future.shop.bean.Quote;
import future.shop.bean.Shop;
import future.util.Delay;
import future.util.Discount;
import future.util.ExecutorThreadPool;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 商店服务类
 *
 * @Author Sun
 * @date 2019-03-15
 */
public class ShopService {

    /**
     * 商品列表
     */
    public static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));

    /**
     * 商品列表
     */
    public static List<Shop> shopsFives = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("myPhone27S"));

    /**
     * 根据商品名称返回商品价格  -- 使用顺序流  -- 4个商品
     *
     * @param product 商品名称
     * @return 商品价格
     */
    public static List<String> findPriceFour(String product) {
        return shops.stream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).collect(toList());
    }

    /**
     * 根据商品名称返回商品价格  -- 使用顺序流  -- 5个商品
     *
     * @param product 商品名称
     * @return 商品价格
     */
    public static List<String> findPriceFive(String product) {
        return shopsFives.stream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).collect(toList());
    }

    /**
     * 根据商品名称返回商品价格  -- 使用并行流  -- 4个商品
     *
     * @param product 商品名称
     * @return 商品价格
     */
    public static List<String> findPriceFour1(String product) {
        return shops.parallelStream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).collect(toList());
    }

    /**
     * 根据商品名称返回商品价格  -- 使用并行流  -- 5个商品
     *
     * @param product 商品名称
     * @return 商品价格
     */
    public static List<String> findPriceFive1(String product) {
        return shopsFives.parallelStream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).collect(toList());
    }

    /**
     * 根据商品名称返回商品价格  -- 使用CompletableFuture以异步方式计算每种商品的价格  -- 4个商品
     *
     * @param product 商品名称
     * @return 商品价格
     */
    public static List<String> findPriceFour2(String product) {
        // 使用CompletableFuture以异步方式计算每种商品的价格
        List<CompletableFuture<String>> priceFutures =
                ShopService.shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> String.format("%s price is %.2f", shop.getName(), shop.getPrice("myPhone27S"))))
                        .collect(toList());

        // 等待所有异步操作结束
        return priceFutures.stream().map(CompletableFuture::join).collect(toList());
    }

    /**
     * 根据商品名称返回商品价格  -- 使用CompletableFuture以异步方式计算每种商品的价格  -- 5个商品
     *
     * @param product 商品名称
     * @return 商品价格
     */
    public static List<String> findPriceFive2(String product) {
        // 使用CompletableFuture以异步方式计算每种商品的价格
        List<CompletableFuture<String>> priceFutures =
                ShopService.shopsFives.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> String.format("%s price is %.2f", shop.getName(), shop.getPrice("myPhone27S"))))
                        .collect(toList());

        // 等待所有异步操作结束
        return priceFutures.stream().map(CompletableFuture::join).collect(toList());
    }

    /**
     * 根据商品名称返回商品价格  -- 使用CompletableFuture以异步方式计算每种商品的价格  -- 5个商品  -- 自定义线程池
     *
     * @param product 商品名称
     * @return 商品价格
     */
    public static List<String> findPriceFive3(String product) {
        // 使用CompletableFuture以异步方式计算每种商品的价格
        List<CompletableFuture<String>> priceFutures =
                ShopService.shopsFives.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> String.format("%s price is %.2f", shop.getName(), shop.getPrice("myPhone27S")), ExecutorThreadPool.executor))
                        .collect(toList());

        // 等待所有异步操作结束
        return priceFutures.stream().map(CompletableFuture::join).collect(toList());
    }

    /**
     * 以最简单的方式实现使用Discount服务的findPrices方法
     */
    public static List<String> findPriceFive4(String product) {
        // 以最简单的方式实现使用Discount服务的findPrices方法
        return shopsFives.stream()
                // 取得每个shop对象中商品的原始价格
                .map(shop -> shop.getPrice1(product))
                // 在Quote对象中对shop返回的字符串进行转换
                .map(Quote::parse)
                // 联系Discount服务，为每个Quote申请折扣
                .map(Discount::applyDiscount)
                .collect(toList());
    }

    /**
     * 根据商品名称返回商品价格  -- 使用CompletableFuture以异步方式计算每种商品的价格  -- 5个商品  -- 自定义线程池
     */
    public static List<String> findPriceFive5(String product) {
        List<CompletableFuture<String>> priceFutures =
                shopsFives.stream()
                        // 以异步方式取得每个shop中指定产品的原始价格
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> shop.getPrice1(product), ExecutorThreadPool.executor))
                        // Quote对象存在时，对其返回的值进行转换
                        .map(future -> future.thenApply(Quote::parse))
                        // 使用另一个异步任务构造期望的Future，申请折扣
                        .map(future -> future.thenCompose(quote ->
                                CompletableFuture.supplyAsync(
                                        () -> Discount.applyDiscount(quote), ExecutorThreadPool.executor)))
                        .collect(toList());

        // 等待流中的所有Future执行完毕，并提取各自的返回值
        return priceFutures.stream().map(CompletableFuture::join).collect(toList());
    }

    /**
     * 根据商品名称返回商品价格  -- 只要有商店返回商品价格就在第一时间显示返回值
     */
    public static Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice1(product), ExecutorThreadPool.executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(quote), ExecutorThreadPool.executor)));
    }

    /**
     * 返回一个随机计算的商品价格值
     *
     * @param product
     * @return
     */
    public static double calculatePrice(String product) {
        // 引用模拟的延迟
        Delay.delay();
        // 使用charAt，依据产品的名称，生成一个随机值作为价格
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 返回一个随机计算的商品价格值
     *
     * @param product
     * @return
     */
    public static double calculatePrice1(String product) {
        // 引用模拟的延迟
        Delay.randomDelay();
        // 使用charAt，依据产品的名称，生成一个随机值作为价格
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 根据商品名称返回商品价格
     *
     * @param product 商品名称
     * @return 商品价格
     */
    public static Future<Double> getPriceAsync(String product) {
        // 创建CompletableFuture对象，它会包含计算的结果
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                // 在另一个线程中以异步方式执行计算
                double price = calculatePrice(product);
                // 如果价格计算正常结束，完成Future操作并设置商品价格(Future的返回值)
                futurePrice.complete(price);
            } catch (Exception ex) {
                // 否则就抛出导致失败的异常，完成这次Future操作
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        // 无需等待还没结束的计算，直接返回Future对象
        return futurePrice;
    }

    /**
     * 根据商品名称返回商品价格  -- 使用工厂方法
     *
     * @param product 商品名称
     * @return 商品价格
     */
    public static Future<Double> getPriceAsync1(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

}
