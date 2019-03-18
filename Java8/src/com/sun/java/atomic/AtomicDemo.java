package atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子操作类示例      -- 线程安全的，多线程中使用
 *
 * @Author Sun
 * @date 2019-03-18
 */
public class AtomicDemo {

    /**
     * getAndUpdate：        -- 以原子方式用给定的方法更新当前值，并返回变更之前的值。
     * updateAndGet：        -- 以原子方式用给定的方法更新当前值，并返回变更之后的值。
     * getAndAccumulate：    -- 以原子方式用给定的方法对当前及给定的值进行更新，并返回变更之前的值。
     * accumulateAndGet：    -- 以原子方式用给定的方法对当前及给定的值进行更新，并返回变更之后的值。
     */
    public static void main(String... args) {
        // 下面的例子向我们展示了如何以原子方式比较一个现存的原子整型值和一个给定的观测值（比如10），并将变量设定为二者中较小的一个。
        int min = new AtomicInteger().accumulateAndGet(55454254, Integer::min);
        System.out.println(min);
    }

}
