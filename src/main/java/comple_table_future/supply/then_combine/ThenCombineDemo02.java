package comple_table_future.supply.then_combine;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/22 18:00
 * @desc thenCombineAsync 会在两个CompletableFuture任务都执行完成后，再用一个异步线程把两个任务的结果一块处理。
 */
public class ThenCombineDemo02 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<String> helloAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello 执行线程：" + Thread.currentThread().getName());
            return "hello";
        }, threadPool);
        CompletableFuture<String> worldAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("world 执行线程：" + Thread.currentThread().getName());
            return "world";
        }, threadPool);
        CompletableFuture<String> result = worldAsync.thenCombineAsync(helloAsync, (hello, world) -> {
            System.out.println("result 执行线程：" + Thread.currentThread().getName());
            return (hello + "," + world).toUpperCase();
        }, threadPool);
        System.out.println("获取结果 执行线程：" + Thread.currentThread().getName());
        System.out.println("两个异步任务合并结果:" + result.join());
    }
}
