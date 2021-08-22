package comple_table_future.supply.then_apply;

import java.util.concurrent.CompletableFuture;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/22 17:04
 * @desc thenApply的使用
 */
public class ThenApplyDemo01 {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName());
                    return "hello";
                }).thenApply(e -> {
                    System.out.println(Thread.currentThread().getName());
                    return e + ",";
                }).thenApply(e -> {
                    System.out.println(Thread.currentThread().getName());
                    return (e + "world").toUpperCase();
                });
        System.out.println(future.join());
    }
}
