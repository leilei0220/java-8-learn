package comple_table_future.supply.handle;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/22 17:35
 * @desc 预估 supplyAsync 是否发生异常，当发生异常时，仍需要执行第二个任务
 */
public class HandleDemo01 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前supplyAsync 执行线程：" + Thread.currentThread().getName());
            // 模拟异常
            int a = 1 / 0;
            return "hello";
        },threadPool).handleAsync((x, t) -> {
            System.out.println("当前handle 执行线程：" + Thread.currentThread().getName());
            if (t != null) {
                // 出现异常 打印异常信息 或者doSomething
                System.out.println("发现上一个异步任务出异常了" + t.getMessage());
            } else {
                // 未出异常 doSomething
                return x;
            }
            // 设置默认结果
            return "error";
        },threadPool);
        System.out.println(future.join());
    }
}
