package future.util;

import future.shop.bean.Shop;
import future.shop.service.ShopService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * CompletableFuture.supplyAsync 工厂类
 *
 * @Author Sun
 * @date 2019-03-15
 */
public class CompletableFutureSupplyAsync {

    /**
     * supplyAsync方法接受一个生产者（Supplier）作为参数，返回一个CompletableFuture对象。
     * 该对象完成异步执行后会读取调用生产者方法的返回值。
     * 生产者方法会交由ForkJoinPool池中的某个执行线程（Executor）运行。
     */
    public Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> ShopService.calculatePrice(product));
    }

}
