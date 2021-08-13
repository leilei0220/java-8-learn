package future;

import java.util.concurrent.CompletableFuture;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/13 8:10
 * @desc
 */
public class CompletableFutureDemo01 {
    public static void main(String[] args) throws Exception{
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(() -> {
            try {
                // 成功执行,将结果赋值给future
                String result = SupportService.writeEnglishHomWork() + SupportService.writeMathHomeWork();
                future.complete(result);
            } catch (Exception ex) {
                // 否则就抛出导致失败的异常，完成这次Future操作
                future.completeExceptionally(ex);
            }
            System.out.println(Thread.currentThread().getName());
        }).start();

        System.out.println(future.get());
    }


}
