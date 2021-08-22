package comple_table_future.supply.then_combine;

import java.util.concurrent.CompletableFuture;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/22 18:00
 * @desc thenCombine 会在两个CompletableFuture任务都执行完成后，调用者线程会把两个异步任务的结果一块处理。
 */
public class ThenCombineDemo01 {
    public static void main(String[] args) {
        CompletableFuture<String> helloAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello 执行线程：" + Thread.currentThread().getName());
            return "hello";
        });
        CompletableFuture<String> worldAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("world 执行线程：" + Thread.currentThread().getName());
            return "world";
        });
        CompletableFuture<String> result = worldAsync.thenCombine(helloAsync, (hello, world) -> {
            System.out.println("result 执行线程：" + Thread.currentThread().getName());
            return (hello + "," + world).toUpperCase();
        });
        System.out.println("获取结果 执行线程：" + Thread.currentThread().getName());
        System.out.println("两个异步任务合并结果:" + result.join());
    }
}
