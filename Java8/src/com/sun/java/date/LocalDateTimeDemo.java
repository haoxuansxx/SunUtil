package date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 * LocalDateTime 日期+时间组合类示例类
 *
 * @Author Sun
 * @date 2019-03-18
 */
public class LocalDateTimeDemo {

    /**
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

        LocalDate date = LocalDate.of(2019, 3, 18);
        LocalTime time = LocalTime.of(13, 45, 20);

        // 创建时间
        LocalDateTime date1 = LocalDateTime.of(2019, Month.MARCH, 18, 13, 45, 20);
        LocalDateTime date2 = LocalDateTime.of(date, time);
        LocalDateTime date3 = date.atTime(13, 45, 20);
        LocalDateTime date4 = date.atTime(time);
        LocalDateTime date5 = time.atDate(date);
        // 获取当前时间
        LocalDateTime localDateTimeNow = LocalDateTime.now();

        // 获取时间小时
        System.out.println("getHour：-------------------------" + time.getHour());

    }

}
