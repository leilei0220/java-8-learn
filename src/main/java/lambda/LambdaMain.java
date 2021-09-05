package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author lei
 * @version 1.0
 * @date 2021/7/31 17:34
 * @desc
 */
public class LambdaMain {
    public static void main(String[] args) {
        Function a = xx -> 1 + 1;
        Predicate p = Objects::nonNull;
        Consumer consumer = System.out::println;
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // 40 50 60 70 80 90 100
        integers.stream().filter(x -> x > 3).sorted().map(x -> x * 10).forEach(e -> System.out.print(e + " "));
        System.out.println("-----");

        // 40 50 60 70 80 90 100
        myForeach(myMap(myFilter(integers, x -> x > 3), x -> x * 10), e -> System.out.print(e + " "));
        System.out.println("-----");
        myForeachFunction(integers, x -> {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < x; i++) {
                builder.append("*");
            }
            System.out.println(builder);
        });
    }

    /**
     * 自定义数据映射
     *
     * @param list
     * @param function
     * @param <R>
     * @param <T>
     * @return
     */
    public static <R, T> List<R> myMap(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T ele : list) {
            R r = function.apply(ele);
            result.add(r);
        }
        return result;
    }

    /**
     * 自定义数据过滤
     *
     * @param list
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> List<T> myFilter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T ele : list) {
            boolean test = predicate.test(ele);
            if (test) {
                result.add(ele);
            }
        }
        return result;
    }

    /**
     * 自定义循环
     *
     * @param list
     * @param consumer
     * @param <T>
     */
    public static <T> void myForeach(List<T> list, Consumer<T> consumer) {
        for (T ele : list) {
            consumer.accept(ele);
        }
    }

    /**
     * 自定义循环
     *
     * @param list
     * @param function
     * @param <T>
     */
    public static <T> void myForeachFunction(List<T> list, MyApplyFunction<T> function) {
        for (T ele : list) {
            function.apply(ele);
        }
    }
}
