package date;

import java.time.*;

/**
 * 时区处理示例类
 *
 * @Author Sun
 * @date 2019-03-18
 */
public class ZoneIdDemo {

    /**
     * 时区是按照一定的规则将区域划分成的标准时间相同的区间。
     * 在ZoneRules这个类中包含了40个这样的实例。你可以简单地通过调用ZoneId的getRules()得到指定时区的规则。
     * <p>
     * 一旦得到一个ZoneId对象，你就可以将它与LocalDate、LocalDateTime或者是Instant对象整合起来，
     * 构造为一个ZonedDateTime实例，它代表了相对于指定时区的时间点，代码清单如下所示。
     */
    public static void main(String... args) {
        // 地区ID都为“{区域}/{城市}”的格式，这些地区集合的设定都由英特网编号分配机构（IANA）的时区数据库提供。
        ZoneId romeZone = ZoneId.of("Europe/Rome");

        // 通过LocalDate构造一个ZonedDateTime实例
        LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);

        // 通过LocalDateTime构造一个ZonedDateTime实例
        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone);

        // 通过Instant构造一个ZonedDateTime实例
        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone);

        // 通过ZoneId，将LocalDateTime转换为Instant
        dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        // ZoneOffset.ofHours()     -- 以小时为单位从-18到+18的时区偏移。
        Instant instantFromDateTime = dateTime.toInstant(ZoneOffset.ofHours(8));

        // 通过反向的方式得到LocalDateTime对象
        instant = Instant.now();
        LocalDateTime timeFromInstant = LocalDateTime.ofInstant(instant, romeZone);

        // 通用的表达时区的方式是利用当前时区和UTC/格林尼治的固定偏差          -- 未考虑日光时，不推荐使用
        // “-05:00”的偏差实际上对应的是美国东部标准时间
        ZoneOffset newYorkOffset = ZoneOffset.of("-05:00");

        //使用ISO-8601的历法系统，以相对于UTC/格林尼治时间的偏差方式表示日期时间
        dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        OffsetDateTime dateTimeInNewYork = OffsetDateTime.of(dateTime, newYorkOffset);

    }

}
