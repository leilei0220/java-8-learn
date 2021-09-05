package map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lei
 * @version 1.0
 * @date 2021/9/5 12:27
 * @desc getOrDefault 获取指定KEY的Value,如果KEY不存在,则返回指定的默认值
 */
public class MapNewMethod01_GetOrDefault {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>(4);
        map.put("lisa", 1);
        map.put("jone", 2);
        map.put("selina", 3);

        // java8 之前写法
        int lisaValue = 0;
        if (map.containsKey("lisa")) {
            lisaValue = map.get("lisa");
        }
        System.out.println(lisaValue);

        // java8 后新写法

        // key为 lisa 的映射存在则返回对应value,否则返回默认值-1
        Integer java8lisaValue = map.getOrDefault("lisa", -1);
        // 1
        System.out.println(java8lisaValue);
        // key为 adas 的映射存在则返回对应value,否则返回默认值-999
        Integer adasValue = map.getOrDefault("adas", -999);
        // adas
        System.out.println(adasValue);
    }
}
