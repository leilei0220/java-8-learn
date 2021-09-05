package map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lei
 * @version 1.0
 * @date 2021/9/5 12:34
 * @desc compute对Map 中指定 key 的值进行重新计算。
 */
public class MapNewMethod03_Compute {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>(4);
        map.put("lisa", 1);
        map.put("jone", 2);
        map.put("selina", 3);

        // java8 之前
        computeBefore("lisa", 123);

        // jone key存在,且 新value不为null,则会返回最新jone对应的value (v + 234),并添加到映射集map中
        Integer joneNewValue = map.compute("jone", (k, v) -> v == null ? 0 : v + 234);
        // joneNewValue:236
        System.out.println("joneNewValue:" + joneNewValue);
        // {lisa=1, jone=236, selina=3}
        System.out.println(map);

        System.out.println("================");
        // jone key存在,且 新value为null,则会返回最新jone对应的value null,并将映射在原集中删除
        Integer joneNewValue2 = map.compute("jone", (k, v) -> null);
        // joneNewValue2:null
        System.out.println("joneNewValue2:" + joneNewValue2);
        // 此时会 移除 KEY  jone
        // {lisa=1, selina=3}
        System.out.println(map);


        System.out.println("================");
        // asd key不存在,且 新value不为null,则会返回最新asd对应的value 111,并保存到映射集map中
        Integer asdNewValue = map.compute("asd", (k, v) -> 111);
        // asdNewValue:111
        System.out.println("asdNewValue:" + asdNewValue);
        // {lisa=1, selina=3, asd=111}
        System.out.println(map);

        System.out.println("================");
        // box key不存在,且 新value为null,则会返回null, 不保存到映射集map中
        Integer boxNewValue = map.compute("box", (k, v) -> null);
        // boxNewValue:null
        System.out.println("boxNewValue:" + boxNewValue);
        // {lisa=1, selina=3, asd=111}
        System.out.println(map);
    }


    private static Integer computeBefore(String key, Integer newValue) {
        Map<String, Integer> map = new LinkedHashMap<>(4);
        map.put("lisa", 1);
        map.put("jone", 2);
        map.put("selina", 3);
        Integer oldValue = map.get(key);
        if (newValue != null) {
            if (map.containsKey(key) && oldValue != null) {
                map.remove(key);
            }
            return null;
        } else {
            map.put(key, newValue);
            return newValue;
        }
    }


}
