package time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/28 21:02
 * @desc 详细时间（年月日 时分秒） 时间操作
 */
public class LocalDateTimeDemo {
    public static void main(String[] args) {
        //获取当前时间  2021-08-28T21:02:38.644
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        //格式化 20210828
        System.out.println(now.format(DateTimeFormatter.BASIC_ISO_DATE));

        //格式化 2021-08-28 21:02:38
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(now.format(formatter));

        // 时间偏移 我这里是减一天加一月 并格式化 2021-09-27 21:02:38
        System.out.println(now.minusDays(1).plusMonths(1).format(formatter));

        // 获取当前年 月 日 当前年:2021 当前月:8 当前日:28
        System.out.println("当前年:" + now.getYear() + " 当前月:" + now.getMonthValue() + " 当前日:" + now.getDayOfMonth());
        //LocalDate + LocalTime 组合为 LocalDateTime
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        // 2021-08-28 21:02:38
        System.out.println(LocalDateTime.of(localDate, localTime).format(formatter));
    }
}
