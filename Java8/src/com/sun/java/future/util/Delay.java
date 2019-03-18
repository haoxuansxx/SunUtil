package future.util;

import java.util.Random;

/**
 * 模拟延迟工具类
 *
 * @Author Sun
 * @date 2019-03-15
 */
public class Delay {

    protected static final Random random = new Random();

    /**
     * 模拟延迟1秒钟的方法
     */
    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 一个模拟生成0.5秒至2.5秒随机延迟的方法
     */
    public static void randomDelay() {
        int delay = 500 + random.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
