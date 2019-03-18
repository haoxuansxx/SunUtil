package date;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

/**
 * 计算明天的日期，如果遇到周六日则返回下周一        -- 自定义TemporalAdjuster接口实现
 *
 * @Author Sun
 * @date 2019-03-18
 */
public class NextWorkingDay implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(Temporal temporal) {
        DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        // 当前日期是周一到周五，增加一天
        int dayToAdd = 1;
        if (dow == DayOfWeek.FRIDAY) {
            // 当前日期是周五，增加三天
            dayToAdd = 3;
        } else if (dow == DayOfWeek.SATURDAY) {
            // 当前日期是周六，增加两天
            dayToAdd = 2;
        }
        return temporal.plus(dayToAdd, ChronoUnit.DAYS);
    }

}
