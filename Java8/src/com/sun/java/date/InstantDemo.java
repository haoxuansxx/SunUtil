package date;

import java.time.Instant;

/**
 * Instant 机器的日期和时间示例类
 *
 * @Author Sun
 * @date 2019-03-18
 */
public class InstantDemo {

    /**
     * 建模时间最自然的格式是表示一个持续时间段上某个点的单一大整型数。
     * 这也是新的java.time.Instant类对时间建模的方式，
     * 基本上它是以Unix元年时间（传统的设定为UTC时区1970年1月1日午夜时分）开始所经历的秒数进行计算。
     * <p>
     * from         -- 是否静态方法：是         -- 依据传入的 Temporal 对象创建对象实例
     * now          -- 是否静态方法：是         -- 依据系统时钟创建 Temporal 对象
     * of           -- 是否静态方法：是         -- 由 Temporal 对象的某个部分创建该对象的实例
     * parse        -- 是否静态方法：是         -- 由字符串创建 Temporal 对象的实例
     * atOffset     -- 是否静态方法：否         -- 将 Temporal 对象和某个时区偏移相结合
     * atZone       -- 是否静态方法：否         -- 将 Temporal 对象和某个时区相结合
     * format       -- 是否静态方法：否         -- 使用某个指定的格式器将 Temporal对象转换为字符串（Instant类不提供该方法）
     * get          -- 是否静态方法：否         -- 读取 Temporal 对象的某一部分的值
     * minus        -- 是否静态方法：否         -- 创建 Temporal 对象的一个副本，通过将当前 Temporal 对象的值减去一定的时长创建该副本
     * plus         -- 是否静态方法：否         -- 创建 Temporal 对象的一个副本，通过将当前 Temporal 对象的值加上一定的时长创建该副本
     * with         -- 是否静态方法：否         -- 以该 Temporal 对象为模板，对某些状态进行修改创建该对象的副本
     */
    public static void main(String... args) {

        // 3秒之后
        Instant.ofEpochSecond(3);
        // 3秒之后再加上0纳秒
        Instant.ofEpochSecond(3, 0);
        // 2秒之后再加上100万纳秒（1秒）
        Instant.ofEpochSecond(2, 1_000_000_000);
        // 4秒之后再减去100万纳秒（1秒）
        Instant.ofEpochSecond(4, -1_000_000_000);

    }

}
