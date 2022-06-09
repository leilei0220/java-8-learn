package fork_join;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

/**
 * @author lei
 * @create 2021-09-15 15:14
 * @desc forkjoin-demo01
 *
 * 继承 RecursiveAction 代表该任务无返回值
 *
 **/
public class RecursiveTaskDemo extends RecursiveTask<Long> {

  private static final int SEQUENTIAL_THRESHOLD = 50;

  private final List<Long> data;

  public RecursiveTaskDemo(List<Long> data) {
    this.data = data;
  }

  @Override
  protected Long compute() {
    if (data.size() <= SEQUENTIAL_THRESHOLD) {
      long sum = computeSumDirectly();
      System.out.format("Sum of %s: %d\n", data.toString(), sum);
      return sum;
    } else {
      int mid = data.size() / 2;
      RecursiveTaskDemo firstSubtask = new RecursiveTaskDemo(data.subList(0, mid));
      RecursiveTaskDemo secondSubtask = new RecursiveTaskDemo(data.subList(mid, data.size()));
      // 执行子任务
      firstSubtask.fork();
      secondSubtask.fork();
      // 等待子任务执行完成，并获取结果
      long firstSubTaskResult = firstSubtask.invoke();
      long secondSubTaskResult = secondSubtask.invoke();
      return firstSubTaskResult + secondSubTaskResult;
    }
  }

  private long computeSumDirectly() {
    long sum = 0;
    for (Long l : data) {
      sum += l;
    }
    return sum;
  }

  public static void main(String[] args) {
    Random random = new Random();

    List<Long> data = random
        .longs(1_000, 1, 100)
        .boxed()
        .collect(Collectors.toList());
    System.out.println(data.stream().mapToInt(Long::intValue).sum());


    ForkJoinPool pool = new ForkJoinPool();
    RecursiveTaskDemo task = new RecursiveTaskDemo(data);
    pool.invoke(task);
    System.out.println("Sum: " + pool.invoke(task));
  }
}