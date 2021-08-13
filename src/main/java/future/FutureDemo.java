package future;

import java.util.concurrent.*;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/12 8:18
 * @desc
 */
public class FutureDemo {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        // 写完数学作业
        Callable<String> ca1 = SupportService::writeMathHomeWork;
        FutureTask<String> ft1 = new FutureTask<>(ca1);
        new Thread(ft1).start();

        // 写完英语作业
        Callable<String> ca2 = SupportService::writeEnglishHomWork;
        FutureTask<String> ft2 = new FutureTask<>(ca2);
        new Thread(ft2).start();
        // .get() 会一直等待下去，其中可以加时间参数,但注意其会抛出超时异常
        System.out.println(ft1.get(3, TimeUnit.SECONDS));
        // 由于我写英语睡眠了四秒秒 所以这里会抛出异常
        //System.out.println(ft2.get(2, TimeUnit.SECONDS));
        System.out.println(ft2.get());

        long end = System.currentTimeMillis();
        System.out.println("异步-写作业一共耗时：" + (end - start));

        //--------------------------------
        long syncStart = System.currentTimeMillis();
        System.out.println(SupportService.writeMathHomeWork());
        System.out.println(SupportService.writeMathHomeWork());
        long syncEnd = System.currentTimeMillis();
        System.out.println("同步写作业一共耗时：" + (syncEnd - syncStart));

    }
}
