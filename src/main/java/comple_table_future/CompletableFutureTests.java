package comple_table_future;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureTests {
    public static void main(String[] args) {
        String[] orders = {"1", "2", "3", "4", "5", "6"};
        ExecutorService executor = Executors.newFixedThreadPool(orders.length);
        Arrays.stream(orders).forEach(id -> CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("抛一个异常" + id);
        }, executor).exceptionally(e -> {
            System.out.println(e.getMessage());
                    return false;
        }));
        executor.shutdown();
    }
}