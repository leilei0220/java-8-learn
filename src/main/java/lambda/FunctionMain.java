package lambda;

import java.util.function.Function;

/**
 * @author lei
 * @create 2021-09-15 16:56
 * @desc 使用函数式接口 Function
 **/
public class FunctionMain {
    public static void main(String[] args) {
        Function<String, String> function = x -> "a".toLowerCase();

        final Integer result1 = myFunction(1, x -> x + 1);
        System.out.println(result1);

        final String result2 = myFunction("abc", String::toUpperCase);
        System.out.println(result2);


    }

    public static <T, R> R myFunction(T t, Function<T, R> function) {
        return function.apply(t);
    }
}
