package comple_table_future.supply.demo;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/20 8:12
 * @desc stream 与 CompletableFuture.supplyAsync 集合使用
 * 本机器 8核
 * 为什么要这么使用？ 为何不直接使用并行流 parallelStream？
 * 因为：整个并行流使用的forkJoin线程池，且整个JVM很多都用到了该线程池，我们每次在使用并行流时手动指定线程池大小
 * 但是 CompletableFuture既可以使用磨人的forkJoin 又可以为使用自定义线程池，避免因其他任务使用forkjoin线程池而等待
 */
public class SupplyAsyncDemo01 {

    public static void main(String[] args) {
        // 模拟商品名列表
        List<String> proNameList = Arrays.asList("pp", "dd", "cc", "ee", "xx", "ff", "max", "dd", "qq",
                "pp", "dd", "cc", "ee", "xx", "ff", "max", "dd", "qq");

        // 普通stream 耗时 测试 串行化 一个一个执行
        long start = System.currentTimeMillis();
        List<String> collect = proNameList.stream().map(SupplyAsyncDemo01::getProduct)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        System.out.println(collect);
        long end = System.currentTimeMillis();
        System.out.println("stream:   " + (end - start));

        /**
         * parallelStream 耗时测试 底层forkJoin线程池数量是是cpu核心数个..一旦任务数量超过核心数，多余的仍要等核心线程空出来再执行
         * 本机器为8核
         * 上方集合有9个元素 ,假设每次请求getProduct都耗时一秒
         * 那么我们9个元素，程序至少要耗时2秒 （8个并行耗时一秒)+(剩余的元素等待其中一个线程执行完毕后再用空闲线程执行，耗时一秒)
         */
        long startParallel = System.currentTimeMillis();
        List<String> collectParallel = proNameList.parallelStream()
                .map(SupplyAsyncDemo01::getProduct)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        System.out.println(collectParallel);
        long endParallel = System.currentTimeMillis();
        System.out.println("parallelStream:   " + (endParallel - startParallel));

        // 使用CompletableFuture实现
        long startCompletable = System.currentTimeMillis();
        // 执行异步任务
        List<CompletableFuture<String>> completableFutureList = proNameList.stream()
                .map(e -> CompletableFuture.supplyAsync(() -> getProduct(e))).collect(Collectors.toList());
        // 获取异步任务结果
        List<String> strings = completableFutureList.stream()
                .map(CompletableFuture::join)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        System.out.println(strings);
        long endCompletable = System.currentTimeMillis();
        System.out.println("completableFuture:   " + (endCompletable - startCompletable));

        // stream + 使用CompletableFuture实现  CompletableFuture与并行相比的优势是我们可以自定义线程池
        long startCompletableAndThreadPool = System.currentTimeMillis();
        // fixme 根据阿里规约 建议真实开发时使用 ThreadPoolExecutor 定义线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(proNameList.size());
        // 执行异步任务
        List<CompletableFuture<String>> futures = proNameList.stream()
                .map(e -> CompletableFuture.supplyAsync(() -> getProduct(e), threadPool))
                .collect(Collectors.toList());

        // 获取异步任务结果
        List<String> stringList = futures.stream()
                .map(CompletableFuture::join)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        System.out.println(stringList);
        long endCompletableAndThreadPool = System.currentTimeMillis();
        System.out.println("completableFuture-threadPool:   " + (endCompletableAndThreadPool - startCompletableAndThreadPool));
    }


    public static String getProduct(String name) {
        // 假设此方法 需要远程调用。且有一点耗时
        try {
            Thread.sleep(1000);
            return name.toUpperCase();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}