package date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * 格式化日期处理示例类
 *
 * @Author Sun
 * @date 2019-03-18
 */
public class DateTimeFormatterDemo {

    public static void main(String... args) {

        // 创建日期
        LocalDate date = LocalDate.of(2014, 3, 18);

        // 格式化日期  -- 以BASIC_ISO_DATE格式：20140318
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);

        // 格式化日期  -- 以ISO_LOCAL_DATE格式：2014-03-18   -- 默认格式
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);

        // 创建日期  -- 以BASIC_ISO_DATE格式：20140318
        LocalDate date1 = LocalDate.parse("20140318", DateTimeFormatter.BASIC_ISO_DATE);

        // 创建日期  -- 以BASIC_ISO_DATE格式：20140318
        LocalDate date2 = LocalDate.parse("2014-03-18", DateTimeFormatter.ISO_LOCAL_DATE);

        // 创建日期  -- 以自定义格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(formatter);
        LocalDate date3 = LocalDate.parse(formattedDate, formatter);

        // 创建一个本地化的DateTimeFormatter
        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        LocalDate date4 = LocalDate.of(2014, 3, 18);
        // 18. marzo 2014
        String formattedDate1 = date.format(italianFormatter);
        LocalDate date5 = LocalDate.parse(formattedDate1, italianFormatter);

        // 构造一个本地化的DateTimeFormatter
        DateTimeFormatter italianFormatter1 = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);

    }

}
