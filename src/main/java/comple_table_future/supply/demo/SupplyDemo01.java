package comple_table_future.supply.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/22 9:59
 * @desc 使用默认线程池（fork join）执行可获取返回值异步代码
 */
public class SupplyDemo01 {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            // 异步方法内当前执行线程为:ForkJoinPool.commonPool-worker-1
            System.out.println("异步方法内当前执行线程为:" + Thread.currentThread().getName());
            // 模拟返回值
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello,world";
        });
        // 获取异步线程执行结果
        System.out.println(supplyAsync.get(1, TimeUnit.SECONDS));
        System.out.println(supplyAsync.join());
    }
}
