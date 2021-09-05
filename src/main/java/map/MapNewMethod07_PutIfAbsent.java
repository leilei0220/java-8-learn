package map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lei
 * @version 1.0
 * @date 2021/9/5 13:56
 * @desc putIfAbsent() 方法会先判断指定的键（key）是否存在，不存在则将键/值对插入到Map 中。存在则不做插入操作
 */
public class MapNewMethod07_PutIfAbsent {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>(4);
        map.put("lisa", 1);
        map.put("jone", 2);
        map.put("selina", 3);
        Integer newValue = 222;
        Integer joneValue = map.get("jone");
        if (joneValue == null) {
            joneValue = map.put("lisa", newValue);
        } else {
            joneValue = joneValue;
        }
        // selina 在源映射集中不存在,则返回null 做插入操作
        Integer adasValue = map.putIfAbsent("adas", 1);
        System.out.println(adasValue);
        System.out.println(map);

        System.out.println("----------");

        // selina 在源映射集中存在,则返回原值 不做插入操作
        Integer selinaValue = map.putIfAbsent("selina", 33333);
        System.out.println(selinaValue);
        System.out.println(map);
    }
}
