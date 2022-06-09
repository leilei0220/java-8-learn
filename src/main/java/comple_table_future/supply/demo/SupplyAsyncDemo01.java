package comple_table_future.supply.demo;


import org.omg.SendingContext.RunTime;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/20 8:12
 * @desc stream 与 CompletableFuture  比较与使用
 * 本机器 6核
 * 为什么要这么使用？ 为何不直接使用并行流 parallelStream？
 * 因为：整个并行流使用的forkJoin线程池，且整个JVM很多都用到了该线程池
 * 但是 CompletableFuture既可以使用默认的forkJoin 又可以为使用自定义线程池，避免因其他任务使用forkjoin线程池而等待
 */
public class SupplyAsyncDemo01 {

    public static void main(String[] args) {
        System.out.println("当前核心线程数为：" + Runtime.getRuntime().availableProcessors());
        // 模拟商品名列表
        List<String> proNameList = Arrays.asList("pp", "dd", "cc", "ee", "xx", "ff", "max");
        System.out.println("当前商品数为:" + proNameList.size());
        // 普通stream 耗时 测试 串行化 一个一个执行
        long start = System.currentTimeMillis();
        List<String> collect = proNameList.stream().map(SupplyAsyncDemo01::getProduct)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        System.out.println(collect);
        long end = System.currentTimeMillis();
        System.out.println("stream:   " + (end - start));

        /**
         * parallelStream 耗时测试 底层forkJoin  commonPool线程池数量是是cpu核心数-1个（至少为1）
         * 详情则查看 ForkJoinPool类 makeCommonPool方法
         * 本机器为6核 则commonPool有五个线程
         * 上方集合有6个元素 ,假设每次请求getProduct都耗时一秒？那么我们要耗时多少秒? 答案是1s,parallelStream在commonPool全部使用时会使用调用线程
         */
        long startParallel = System.currentTimeMillis();
        List<String> collectParallel = proNameList.parallelStream()
                .map(x -> {
                    String product = getProduct(x);
                    // parallel-ForkJoinPool.commonPool-worker-4
                    // parallel-ForkJoinPool.commonPool-worker-2
                    // parallel-ForkJoinPool.commonPool-worker-5
                    // parallel-ForkJoinPool.commonPool-worker-1
                    // parallel-main
                    // parallel-ForkJoinPool.commonPool-worker-3
                    // System.out.println("parallel-"+Thread.currentThread().getName());
                    return product;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        System.out.println(collectParallel);
        long endParallel = System.currentTimeMillis();
        System.out.println("parallelStream 耗时:" + (endParallel - startParallel));


        /**
         * Default executor -- ForkJoinPool.commonPool() unless it cannot,support parallelism.
         *    CompletableFuture 在机器执行并行情况下也是默认使用 ForkJoinPool.commonPool 则线程数为cpu-1(最少为1)
         *  此时如何和上方parallelStream任务数一样,他们的执行结果一致吗？
         *  答案是不一样,CompletableFuture在使用 fork-jon-commonPool全部使用时会等待线程执行结束后，再用空闲线程执行
         *  耗时为 2s
         */
        // 使用CompletableFuture实现
        long startCompletable = System.currentTimeMillis();
        // 执行异步任务
        List<CompletableFuture<String>> completableFutureList = proNameList.stream()
                .map(e -> CompletableFuture.supplyAsync(() -> {
                    String product = getProduct(e);
                    // System.out.println("CompletableFuture-" + Thread.currentThread().getName());
                    return product;
                })).collect(Collectors.toList());
        // 获取异步任务结果
        List<String> strings = completableFutureList.stream()
                .map(CompletableFuture::join)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        System.out.println(strings);
        long endCompletable = System.currentTimeMillis();
        System.out.println("completableFuture 耗时:" + (endCompletable - startCompletable));


        /**
         * 重点来了，上方两个测试感觉CompletableFuture是鸡肋，实则不然,因为我们可以在IO密集型任务时自定义线程池,此时我们可以定义核心线程的n倍，来使
         * CompletableFuture 火力全开
         *
         * ex:下方示例中 任务数大于CPU core 但我们使用自定义线程池,使CompletableFuture性能反超parallelStream
         */

        System.out.println("=========================");
        System.out.println("=========================");
        System.out.println("=========================");
        List<String> list2 = Arrays.asList("pp", "dd", "cc", "dd", "cc", "ee", "xx");
        System.out.println("当前核心线程数为：" + Runtime.getRuntime().availableProcessors());
        System.out.println("当前商品数为:" + list2.size());

        long startParallel1 = System.currentTimeMillis();
        List<String> collectParallel1 = list2.parallelStream()
                .map(x -> {
                    String product = getProduct(x);
                    System.out.println("parallel-"+Thread.currentThread().getName());
                    return product;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        System.out.println(collectParallel1);
        long endParallel1 = System.currentTimeMillis();
        System.out.println("parallelStream 耗时:" + (endParallel1 - startParallel1));

        // stream + 使用CompletableFuture实现  CompletableFuture与并行相比的优势是我们可以自定义线程池
        long startCompletableAndThreadPool = System.currentTimeMillis();
        // fixme 根据阿里规约 建议真实开发时使用 ThreadPoolExecutor 定义线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(list2.size());
        // 执行异步任务
        List<CompletableFuture<String>> futures = list2.stream()
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
        threadPool.shutdown();
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