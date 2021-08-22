package comple_table_future.supply.then_apply;

import java.util.concurrent.CompletableFuture;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/22 17:23
 * @desc
 */
public class ThenApplyDemo02 {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName());
                    return "hello";
                }).thenApplyAsync(e -> {
                    System.out.println(Thread.currentThread().getName());
                    return e + ",";
                }).thenApplyAsync(e -> {
                    System.out.println(Thread.currentThread().getName());
                    return (e + "world").toUpperCase();
                });
        System.out.println(future.join());
    }
}
