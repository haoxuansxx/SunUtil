package future.util;

import future.shop.service.ShopService;

import java.util.concurrent.*;

/**
 * Executor 线程池工具类
 *
 * @Author Sun
 * @date 2019-03-15
 */
public class ExecutorThreadPool {
    /**
     * 创建一个线程池，线程池中线程的数目为100和商店数目二者中较小的一个值
     */
    public static final Executor executor = Executors.newFixedThreadPool(Math.min(ShopService.shopsFives.size(), 100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            // 使用守护线程——这种方式不会阻止程序的关停
            t.setDaemon(true);
            return t;
        }
    });

}
