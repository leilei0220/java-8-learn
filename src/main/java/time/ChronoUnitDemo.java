package time;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/28 21:09
 * @desc 求时间差
 */
public class ChronoUnitDemo {
    public static void main(String[] args) {
        LocalDateTime time1 = LocalDateTime.of(2019, 12, 2, 0, 0);
        LocalDateTime time2 = LocalDateTime.now();

        System.out.println("相差秒:" + ChronoUnit.SECONDS.between(time1, time2));

        System.out.println("相差小时:" + ChronoUnit.HOURS.between(time1, time2));

        System.out.println("相差半天:" + ChronoUnit.HALF_DAYS.between(time1, time2));

        System.out.println("相差天:" + ChronoUnit.DAYS.between(time1, time2));

        System.out.println("相差周:" + ChronoUnit.WEEKS.between(time1, time2));

        System.out.println("相差月:" + ChronoUnit.MONTHS.between(time1, time2));

        System.out.println("相差年" + ChronoUnit.YEARS.between(time1, time2));

        System.out.println("相差年" + ChronoUnit.YEARS.between(time1, time2));
    }
}
