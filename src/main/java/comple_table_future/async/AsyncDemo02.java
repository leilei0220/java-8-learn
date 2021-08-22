package comple_table_future.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/22 9:34
 * @desc 使用自定义线程池执行 异步任务
 */
public class AsyncDemo02 {
    public static void main(String[] args) {
        //当前调用者线程为:main
        System.out.println("当前调用者线程为:" + Thread.currentThread().getName());

        // fixme 根据阿里规约 建议真实开发时使用 ThreadPoolExecutor 定义线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        CompletableFuture.runAsync(() -> {
            // 异步方法内当前执行线程为:pool-1-thread-1
            System.out.println("异步方法内当前执行线程为:" + Thread.currentThread().getName());
            System.out.println(111);
        }, threadPool);

        // 演示代码，所以选择执行完后关闭线程池
        threadPool.shutdown();
    }
}
