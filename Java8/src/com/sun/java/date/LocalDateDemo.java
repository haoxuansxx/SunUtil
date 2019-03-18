package date;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

/**
 * LocalDate 日期组件示例
 *
 * @Author Sun
 * @date 2019-03-18
 */
public class LocalDateDemo {

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

        // 创建日期
        LocalDate date = LocalDate.of(2019, 3, 18);
        LocalDate date1 = LocalDate.parse("2014-03-18");

        // 获取当前日期
        LocalDate dateNow = LocalDate.now();
        // 获取日期年份
        System.out.println("getYear：-------------------------" + date.getYear());
        // 获取日期月份
        System.out.println("getMonth：------------------------" + date.getMonth());
        // 获取日期月份对象
        System.out.println("getDayOfMonth：-------------------" + date.getDayOfMonth());
        // 获取当前日期的星期日期  -- 例：TUESDAY(星期二)
        System.out.println("getDayOfWeek：--------------------" + date.getDayOfWeek().getValue());
        // 获取当前日期月份总天数
        System.out.println("lengthOfMonth：-------------------" + date.lengthOfMonth());
        // 判断当前日期是否是闰年
        System.out.println("isLeapYear：----------------------" + date.isLeapYear());

        // 使用TemporalField读取LocalDate的值
        System.out.println("year：-------------------------" + date.get(ChronoField.YEAR));
        System.out.println("month：-------------------------" + date.get(ChronoField.MONTH_OF_YEAR));
        System.out.println("day：-------------------------" + date.get(ChronoField.DAY_OF_MONTH));

        //创建对象的一个副本，并按照需要修改它的属性。
        // 创建一个日期对象 -- 2014-03-18
        LocalDate date2 = LocalDate.of(2014, 3, 18);
        // 修改年份后结果：2011-03-18
        LocalDate date3 = date2.withYear(2011);
        // 修改天后结果：2011-03-25
        LocalDate date4 = date2.withDayOfMonth(25);
        // 修改月份后结果：2011-09-25
        LocalDate date5 = date3.with(ChronoField.MONTH_OF_YEAR, 9);

    }

}
