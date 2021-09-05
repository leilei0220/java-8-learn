package map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lei
 * @version 1.0
 * @date 2021/9/5 14:30
 * @desc values() 方法返回映射中所有 value 组成的 Set集合
 */
public class MapNewMethod11_Values {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>(4);
        map.put("lisa", 1);
        map.put("jone", 2);
        map.put("selina", 3);
        map.put("qq", null);
        // [lisa, jone, selina, qq]
        System.out.println(map.keySet());
        // [1, 2, 3, null]
        System.out.println(map.values());
    }
}
