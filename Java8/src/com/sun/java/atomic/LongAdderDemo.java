package atomic;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * 线程安全的，多线程中使用。        -- 考虑了动态增长的需求，可以有效地减少线程间的竞争。
 *
 * @Author Sun
 * @date 2019-03-18
 */
public class LongAdderDemo {

    public static void main(String... args) {
        // 使用默认构造器，初始的sum值被置为0
        LongAdder adder = new LongAdder();
        // 在多个不同的线程中进行加法运算
        adder.add(10);
        // 到某个时刻得出sum的值
        long sum = adder.sum();

        /* 或者，你也可以像下面这样使用LongAccumulator实现同样的功能。 */
        LongAccumulator acc = new LongAccumulator(Long::sum, 0);
        // 在几个不同的线程中累计计算值
        acc.accumulate(10);
        // 到某个时刻得出sum的值
        long result = acc.get();
    }

}
