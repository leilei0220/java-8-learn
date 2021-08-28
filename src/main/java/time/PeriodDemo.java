package time;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/28 21:12
 * @desc 求时间范围 基于日期值 LocalDate
 *
 * Period 比较的是两个日期之间相差数据
 * 比如 2020-08-28 与 2021年 08-11 相差多久 答案是相差 0年10个月零13天
 */
public class PeriodDemo {
    public static void main(String[] args) {
        Period period = Period.between(LocalDate.of(2020, 8, 28),
                LocalDate.of(2021, 7, 11));
        // 0
        System.out.println(period.getYears());
        // 11
        System.out.println(period.getMonths());
        // 13
        System.out.println(period.getDays());
    }
}
