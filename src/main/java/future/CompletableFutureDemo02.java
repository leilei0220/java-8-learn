package future;

import java.util.concurrent.CompletableFuture;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/13 8:27
 * @desc 使用工厂模式创建 CompletableFuture, 使用有返回值的completableFuture
 * 默认线程池与parallelStream一样 默认采用forkJoin线程池（如果程序 ForkJoinPool.getCommonPoolParallelism();>1）
 */
public class CompletableFutureDemo02 {
    public static void main(String[] args) {
        // supplyAsync 最后可以使用 join 方法拿到返回值
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("CompletableFuture当前线程" + Thread.currentThread().getName());
            return SupportService.writeEnglishHomWorkNoEx();
        });
        String result = future.join();
        System.out.println(result);
        System.out.println("------使用有异常方法");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                return SupportService.writeEnglishHomWork();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
        String exResult = future2.join();
        System.out.println(exResult);
    }
}
