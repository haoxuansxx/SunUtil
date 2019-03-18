package date;

import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.*;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

/**
 * Java提供的不同的日历系统
 *
 * @Author Sun
 * @date 2019-03-18
 */
public class CalendarSystemDemo {

    /**
     * LocalDate            -- ISO-8601日历系统是世界文明日历系统的事实标准
     * MinguoDate           -- 中华民国历
     * ThaiBuddhistDate     -- 泰国佛历
     * HijrahDate           -- 伊斯兰教日历
     * JapaneseDate         -- 日本日历
     */
    public static void main(String... args) {
        // 创建一个日期
        LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
        // 创建一个中华民国历
        MinguoDate minguoDate = MinguoDate.from(date);
        // 创建一个泰国佛历
        ThaiBuddhistDate thaiBuddhistDate = ThaiBuddhistDate.from(date);
        // 创建一个伊斯兰教日历
        HijrahDate hijrahDate = HijrahDate.from(date);
        // 创建一个日本日历
        JapaneseDate japaneseDate1 = JapaneseDate.from(date);

        // 为某个Locale(China)显式地创建日历系统，接着创建该Locale对应的日期的实例。
        Chronology chinaChronology = Chronology.ofLocale(Locale.CHINA);
        // Chronology接口建模了一个日历系统，使用它的静态工厂方法ofLocale，可以得到它的一个实例
        ChronoLocalDate now = chinaChronology.dateNow();

        // 取得当前的Hijrah日期，紧接着对其进行修正，得到斋月的第一天，即第9个月
        HijrahDate ramadanDate = HijrahDate.now().with(ChronoField.DAY_OF_MONTH, 1).with(ChronoField.MONTH_OF_YEAR, 9);
        // IsoChronology.INSTANCE是IsoChronology类的一个静态实例     -- 斋月始于2019-05-06，止于2019-06-03
        System.out.println("Ramadan starts on " + IsoChronology.INSTANCE.date(ramadanDate) +
                " and ends on " + IsoChronology.INSTANCE.date(ramadanDate.with(TemporalAdjusters.lastDayOfMonth())));


    }

}
