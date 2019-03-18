package date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.TemporalAdjusters.*;

/**
 * TemporalAdjusters 日期操作工厂示例类
 *
 * @Author Sun
 * @date 2019-03-18
 */
public class TemporalAdjustersDemo {

    /**
     * dayOfWeekInMonth         -- 创建一个新的日期，它的值为同一个月中每一周的第几天
     * firstDayOfMonth          -- 创建一个新的日期，它的值为当月的第一天
     * firstDayOfNextMonth      -- 创建一个新的日期，它的值为下月的第一天
     * firstDayOfNextYear       -- 创建一个新的日期，它的值为明年的第一天
     * firstDayOfYear           -- 创建一个新的日期，它的值为当年的第一天
     * firstInMonth             -- 创建一个新的日期，它的值为同一个月中，第一个符合星期几要求的值
     * lastDayOfMonth           -- 创建一个新的日期，它的值为当月的最后一天
     * lastDayOfNextMonth       -- 创建一个新的日期，它的值为下月的最后一天
     * lastDayOfNextYear        -- 创建一个新的日期，它的值为明年的最后一天
     * lastDayOfYear            -- 创建一个新的日期，它的值为今年的最后一天
     * lastInMonth              -- 创建一个新的日期，它的值为同一个月中，最后一个符合星期几要求的值
     * next/previous            -- 创建一个新的日期，并将其值设定为日期调整后或者调整前，第一个符合指定星期几要求的日期
     * nextOrSame/previousOrSame-- 创建一个新的日期，并将其值设定为日期调整后或者调整前，第一个符合指定星期几要求的日期，如果该日期已经符合要求，直接返回该对象
     */
    public static void main(String... args) {

        // 创建日期
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        // 获取包含当前日期之后的第一个星期日
        LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY));
        // 获取当前日期月份的最后一天
        LocalDate date3 = date2.with(lastDayOfMonth());

        // 使用Lambda自定义TemporalAdjusters接口实现     -- 计算明天的日期，如果遇到周六日则返回下周一
        date1 = date1.with(temporal -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            } else if (dow == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });

        // 使用Lambda自定义TemporalAdjusters接口实现     -- 计算明天的日期，如果遇到周六日则返回下周一    -- 推荐使用本方法
        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
                temporal -> {
                    DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
                    int dayToAdd = 1;
                    if (dow == DayOfWeek.FRIDAY) {
                        dayToAdd = 3;
                    } else if (dow == DayOfWeek.SATURDAY) {
                        dayToAdd = 2;
                    }
                    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
                });

        date1 = date1.with(nextWorkingDay);

    }

}
