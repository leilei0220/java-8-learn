package comple_table_future.supply.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/22 10:26
 * @desc 使用指定线程池执行可获取返回值异步代码
 */
public class SupplyDemo02 {
    public static void main(String[] args)  {
        // fixme 根据阿里规约 建议真实开发时使用 ThreadPoolExecutor 定义线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            // 异步方法内当前执行线程为:pool-1-thread-1
            System.out.println("异步方法内当前执行线程为:" + Thread.currentThread().getName());
            // 模拟耗时与返回结果
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello,world";
        },threadPool);
        // 获取异步线程执行结果
        System.out.println(supplyAsync.join());
    }
}
