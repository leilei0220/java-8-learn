package comple_table_future.supply.then_compose;

import java.util.concurrent.CompletableFuture;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/22 18:12
 * @desc thenCompose 方法允许你对两个CompletableFuture任务进行流水线操作，
 * 当第一个异步任务操作完成时，会将其结果作为参数传递给第二个任务(第二个任务为串行化操作，由调用者线程执行)
 */
public class ThenComposeDemo01 {
    public static void main(String[] args) {
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello 执行线程：" + Thread.currentThread().getName());
            return "hello";
        }).thenCompose((hello -> {
            System.out.println("thenCompose 执行线程：" + Thread.currentThread().getName());
            return CompletableFuture.supplyAsync((hello + "world")::toUpperCase);
        }));
        System.out.println("获取结果 执行线程：" + Thread.currentThread().getName());
        System.out.println("两个异步任务流水线执行结果:" + result.join());
    }
}
