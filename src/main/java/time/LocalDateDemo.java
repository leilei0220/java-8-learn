package time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/28 20:49
 * @desc 仅包含年月日 时间操作
 */
public class LocalDateDemo {
    public static void main(String[] args) {
        // 获取现在时间 格式:yyyy-MM-dd ex:2021-08-28
        LocalDate now = LocalDate.now();
        System.out.println(now);

        // 格式化 ex:yyyyMMdd 20210828
        String formatResult = now.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(formatResult);

        // 对时间进行偏移 可以偏移 周 月 年 天等
        // plusXX 对时间做右偏移 （加时间）
        LocalDate afterDate = now.plusYears(1).plusMonths(1).plusWeeks(1).plusDays(1);
        // 我这里是对当前时间+1年+1月+1周+1天  2022-10-06
        System.out.println(afterDate);

        // minusXX 对时间做左偏移 （减时间）
        // 我这里是对当前时间 -1年-1月  2020-07-28
        LocalDate preDate = now.minusYears(1).minusMonths(1);
        System.out.println(preDate);

        // 从指定年月日中获取时间实例
        LocalDate dateInstance = LocalDate.of(2021, 8, 28);
        // LocalDate 实例 2021-08-28
        System.out.println(dateInstance);


        // 获取年  2021
        System.out.println(dateInstance.getYear());

        // 获取月 AUGUST
        System.out.println(dateInstance.getMonth());

        // 获取月份数值 1就是1月 2就是2月.....
        System.out.println(dateInstance.getMonthValue());

        // 今日是当前时间月的第几天
        System.out.println(dateInstance.getDayOfMonth());

        // 一年中的第几天
        System.out.println(dateInstance.getDayOfYear());

        // 一个月中的第几天
        System.out.println(dateInstance.getDayOfMonth());

        // 礼拜几 ex:SATURDAY 周六
        System.out.println(dateInstance.getDayOfWeek());

        // 礼拜几 数字
        System.out.println(dateInstance.getDayOfWeek().getValue());

        // 一个月中的第几天
        System.out.println(dateInstance.get(ChronoField.DAY_OF_MONTH));

        // 一年中的第几天
        System.out.println(dateInstance.get(ChronoField.DAY_OF_YEAR));

        // 获取某天开始于结束时间戳
        LocalDate startDate = LocalDate.now();
        // +8是北京时间
        Long minTime = LocalDateTime.of(startDate, LocalTime.MIN).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long maxTime = LocalDateTime.of(startDate, LocalTime.MAX).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(minTime);
        System.out.println(maxTime);
    }
}
