package date;

import java.time.LocalDate;
import java.time.Period;

/**
 * Duration 两个日期对象之间
 *
 * @Author Sun
 * @date 2019-03-18
 */
public class PeriodDemo {


    /**
     * LocalDateTime和Instant是为不同的目的而设计的，一个是为了便于人阅读使用，另一个是为了便于机器处理，所以你不能将二者混用。
     * Duration类主要用于以秒和纳秒衡量时间的长短，你不能仅向between方法传递一个LocalDate对象做参数。
     *
     * between      -- 是否静态方法：是         -- 创建两个时间点之间的 interval
     * from         -- 是否静态方法：是         -- 由一个临时时间点创建 interval
     * of           -- 是否静态方法：是         -- 由它的组成部分创建 interval 的实例
     * parse        -- 是否静态方法：是         -- 由字符串创建 interval 的实例
     * addTo        -- 是否静态方法：否         -- 创建该 interval 的副本，并将其叠加到某个指定的 temporal 对象
     * get          -- 是否静态方法：否         -- 读取该 interval 的状态
     * isNegative   -- 是否静态方法：否         -- 检查该 interval 是否为负值，不包含零
     * isZero       -- 是否静态方法：否         -- 检查该 interval 的时长是否为零
     * minus        -- 是否静态方法：否         -- 通过减去一定的时间创建该 interval 的副本
     * multipliedBy -- 是否静态方法：否         -- 将 interval 的值乘以某个标量创建该 interval 的副本
     * negated      -- 是否静态方法：否         -- 以忽略某个时长的方式创建该 interval 的副本
     * plus         -- 是否静态方法：否         -- 以增加某个指定的时长的方式创建该 interval 的副本
     * subtractFrom -- 是否静态方法：否         -- 从指定的 temporal 对象中减去该 interval
     */
    public static void main(String... args) {

        // 创建Duration对象
        Period tenDays = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);

        // 获取两个日期之间
        Period tenDays1 = Period.between(LocalDate.of(2014, 3, 8), LocalDate.of(2014, 3, 18));

    }
}
