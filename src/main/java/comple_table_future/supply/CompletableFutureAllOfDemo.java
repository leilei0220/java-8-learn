package comple_table_future.supply;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/22 20:20
 * @desc 等待所有异步线程完成
 */
public class CompletableFutureAllOfDemo {

    public static void main(String[] args) {
        CompletableFuture<String> helloAsync = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("hello 执行线程：" + Thread.currentThread().getName());
                return "hello";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        CompletableFuture<String> worldAsync = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("world 执行线程：" + Thread.currentThread().getName());
                return "world";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        CompletableFuture<String> javaAsync = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("java 执行线程：" + Thread.currentThread().getName());
                return "java";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        List<CompletableFuture<String>> futures = Arrays.asList(helloAsync, worldAsync, javaAsync);
        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[3]));
        CompletableFuture<String> result = completableFuture.thenApply(x -> futures.stream()
                .map(CompletableFuture::join)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(",")));
        System.out.printf("结果：%s", result.join());
    }
}
