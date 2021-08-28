package time;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/28 21:04
 * @desc 求时间差
 */
public class DurationDemo {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        // 2021-08-28T21:04:39.961
        System.out.println(now);
        LocalDateTime of = LocalDateTime.of(2020, 9, 28, 9, 5, 25, 720);
        // 2020-09-28T09:05:25.000000720
        System.out.println(of);

        // between(p1,p1) p1为开始时间 p2为结束时间 求值结果是是p2-p1 所以要注意前后顺序，否则结果为负数
        Duration duration = Duration.between(of, now);
        System.out.println(duration);

        //两个时间相差天数: 334天
        System.out.println(duration.toDays());

        //两个时间相差小时: 8028小时
        System.out.println(duration.toHours());

        //两个时间相差分钟: 481680 分钟
        System.out.println(duration.toMinutes());

        //两个时间相差毫秒: 28900809381 毫秒
        System.out.println(duration.toMillis());
    }
}
