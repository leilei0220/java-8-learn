package comple_table_future.supply.handle;

import java.util.concurrent.CompletableFuture;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/22 17:35
 * @desc
 */
public class HandleDemo02 {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前supplyAsync 执行线程：" + Thread.currentThread().getName());
            // 模拟异常
            int a = 1 / 0;
            return "hello";
        }).handle((x, t) -> {
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
        });
        System.out.println(future.join());
    }
}
