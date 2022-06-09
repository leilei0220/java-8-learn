package fork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @author lei
 * @create 2021-09-15 15:14
 * @desc forkjoin-demo01
 * <p>
 * 继承 RecursiveAction 代表该任务无返回值
 **/

public class RecursiveActionDemo extends RecursiveAction {

    public static void main(String[] args) throws Exception {
        System.out.println(Runtime.getRuntime().availableProcessors());
        final long start = System.currentTimeMillis();
        // 创建包含Runtime.getRuntime().availableProcessors()返回值作为个数的并行线程的ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        // 提交可分解的PrintTask任务
        forkJoinPool.submit(new RecursiveActionDemo(0, 1000));
        //阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        // 关闭线程池
        forkJoinPool.shutdown();

    }

    /**
     * 每个"小任务"最多只打印20个数
     */
    private static final int MAX = 20;

    private final int start;
    private final int end;

    public RecursiveActionDemo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        //当end-start的值小于MAX时，开始打印
        if ((end - start) < MAX) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + "-i的值" + i);
            }
        } else {
            // 将大任务分解成两个小任务
            int middle = (start + end) / 2;
            // 根据逻辑 获取出左右侧任务
            RecursiveActionDemo left = new RecursiveActionDemo(start, middle);
            RecursiveActionDemo right = new RecursiveActionDemo(middle, end);
            // 左右侧任务再继续尝试拆分
            left.fork();
            right.fork();
        }
    }
}



