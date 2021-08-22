package comple_table_future.supply.then_compose;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/22 18:12
 * @desc thenComposeAsync当第一个异步任务操作完成时，会将其结果作为参数传递给第二个任务
 * (第二个任务仍为异步线程执行操作，可由默认ForkJoin线程池执行，也可使用自定义线程池)
 */
public class ThenComposeDemo02 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello 执行线程：" + Thread.currentThread().getName());
            return "hello";
        },threadPool).thenComposeAsync((hello -> {
            System.out.println("thenCompose 执行线程：" + Thread.currentThread().getName());
            return CompletableFuture.supplyAsync((hello + "world")::toUpperCase);
        }),threadPool);
        System.out.println("获取结果 执行线程：" + Thread.currentThread().getName());
        System.out.println("两个异步任务流水线执行结果:" + result.join());
    }
}
