package lambda;

import java.util.function.Consumer;

/**
 * @author lei
 * @create 2021-09-15 16:39
 * @desc
 **/
public class ConsumerMain {
    public static void main(String[] args) {
        // 普通消费 accept
        myConsumer(123, x->{
            System.out.println(x);
            System.out.println("-------");
        });


        // 测试 andThen
        myConsumerAndThen("aa", c1 -> System.out.println(c1.toUpperCase()), c2 -> System.out.println(c2.toLowerCase() + c2.toLowerCase()));
    }


    public static <T> void myConsumer(T t, Consumer<T> consumer) {
        consumer.accept(t);

    }
    /**
     * andThen 两个consumer 一个接一个操作例如我上边是先转大写输出，然后转小写+转小写除数
     * @param a
     * @param consumer1
     * @param consumer2
     * @param <String>
     */
    public static  <String>  void myConsumerAndThen(String a, Consumer<String> consumer1, Consumer<String> consumer2) {
        // 随调用 andThen 谁最先被消费
        consumer1.andThen(consumer2).accept(a);
    }
}
