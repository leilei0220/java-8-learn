package comple_table_future.async;

import java.util.concurrent.CompletableFuture;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/22 9:31
 * @desc 无返回值的 异步线程调用
 * CompletableFuture.runAsync（Runnable）
 */
public class AsyncDemo01 {
    public static void main(String[] args) {
        // main
        System.out.println("当前调用者线程为:" + Thread.currentThread().getName());
        CompletableFuture.runAsync(() -> {
            // ForkJoinPool.commonPool-worker-x
            System.out.println("异步方法内当前线程为:" + Thread.currentThread().getName());
            System.out.println(111);
        });
    }
}
