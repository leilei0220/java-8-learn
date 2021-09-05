package map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lei
 * @version 1.0
 * @date 2021/9/5 12:31
 * @desc foreach  可以让我们以Lambda的方式快速遍历映射集
 */
public class MapNewMethod02_Foreach {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>(4);
        map.put("lisa", 1);
        map.put("jone", 2);
        map.put("selina", 3);

        // java8 之前最高效写法
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "==" + entry.getValue());
        }
        System.out.println("-----------");
        // java8 foreach写法

        //实际上Java8 map的foreach底层仍是用的`entrySet`,只是上层用了BiConsumer 函数式接口，让我们使用Lambda表达式简化了我们遍历的代码。
        map.forEach((k, v) -> System.out.println(k + "=" + v));
    }
}
