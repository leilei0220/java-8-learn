package lambda;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/22 9:27
 * @desc
 */
@FunctionalInterface
public interface MyApplyFunction<T> {
    /**
     * 应用
     * @param t
     */
    void apply(T t);
}
